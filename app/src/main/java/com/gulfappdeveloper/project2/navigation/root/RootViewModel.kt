package com.gulfappdeveloper.project2.navigation.root

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gulfappdeveloper.project2.data.remote.HttpRoutes
import com.gulfappdeveloper.project2.domain.models.product_selected.ProductSelected
import com.gulfappdeveloper.project2.domain.models.remote.get.ClientDetails
import com.gulfappdeveloper.project2.domain.models.remote.get.GetDataFromRemote
import com.gulfappdeveloper.project2.domain.models.remote.get.Product
import com.gulfappdeveloper.project2.domain.models.remote.get.for_add_product.Units
import com.gulfappdeveloper.project2.domain.models.util.PayMode
import com.gulfappdeveloper.project2.presentation.client_screen.util.ClientScreenEvent
import com.gulfappdeveloper.project2.presentation.home_screen.util.HomeScreenEvent
import com.gulfappdeveloper.project2.presentation.product_list_screen.util.ProductListScreenEvent
import com.gulfappdeveloper.project2.presentation.splash_screen.util.SplashScreenEvent
import com.gulfappdeveloper.project2.presentation.ui_util.UiEvent
import com.gulfappdeveloper.project2.usecases.UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

private const val TAG = "RootViewModel"

@HiltViewModel
open class RootViewModel @Inject constructor(
    private val useCase: UseCase
) : ViewModel() {

    private var isInitialLoadingFinished = false

    private val _splashScreenEvent = Channel<SplashScreenEvent>()
    val splashScreenEvent = _splashScreenEvent.receiveAsFlow()

    private val _clientScreenEvent = Channel<ClientScreenEvent>()
    val clientScreenEvent = _clientScreenEvent.receiveAsFlow()

    private val _homeScreenEvent = Channel<HomeScreenEvent>()
    val homeScreenEvent = _homeScreenEvent.receiveAsFlow()

    private val _productListScreenEvent = Channel<ProductListScreenEvent>()
    val productListScreenEvent = _productListScreenEvent.receiveAsFlow()

    private val _operationCount = mutableStateOf(0)
    protected val operationCount: State<Int> = _operationCount


    private val _baseUrl = mutableStateOf(HttpRoutes.BASE_URL)
    val baseUrl: State<String> = _baseUrl

    // Welcome message
    private val _message = mutableStateOf("")
    val message: State<String> = _message

    /*
       First Three rows items
    */
    // Bill no
    private val _billNo = mutableStateOf("")
    val billNo: State<String> = _billNo

    // Selected date
    private val _selectedDate = mutableStateOf(Date())
    val selectedDate: State<Date> = _selectedDate

    // selected Client
    private val _selectedClient: MutableState<ClientDetails?> = mutableStateOf(null)
    val selectedClient: State<ClientDetails?> = _selectedClient

    // selected Po no
    private val _poNo = mutableStateOf("")
    val poNo: State<String> = _poNo

    // Selected Pay mode
    private val _payMode = mutableStateOf(PayMode.Cash)
    val payMode: State<PayMode> = _payMode

    /* Product selection addition rows*/

    // product search text to search products
    private val _productName = mutableStateOf("")
    val productName: State<String> = _productName

    // Product search mode. which used in product name box
    private val _productSearchMode = mutableStateOf(true)
    val productSearchMode: State<Boolean> = _productSearchMode

    // Product  list
    val productList = mutableStateListOf<Product>()

    /*  private val _selectedProduct: MutableState<Product?> = mutableStateOf(null)
      val selectedProduct: State<Product?> = _selectedProduct*/

    private val _productId = mutableStateOf(0)
    private val productId: State<Int> = _productId

    private val _barCode = mutableStateOf("")
    val barCode: State<String> = _barCode

    private val _qty = mutableStateOf("")
    val qty: State<String> = _qty

    private val _unit = mutableStateOf("")
    val unit: State<String> = _unit

    private val _unitId = mutableStateOf(0)

    private val _rate = mutableStateOf("")
    val rate: State<String> = _rate

    private val _disc = mutableStateOf("")
    val disc: State<String> = _disc

    private val _tax = mutableStateOf("")
    val tax: State<String> = _tax

    private val _net = mutableStateOf(0f)
    val net: State<Float> = _net

    private val _clientSearchText = mutableStateOf("")
    val clientSearchText: State<String> = _clientSearchText

    val clientDetailsList = mutableStateListOf<ClientDetails>()

    val selectedProductList = mutableStateListOf<ProductSelected>()


    init {
        sendSplashScreenEvent(SplashScreenEvent.ShowProgressBar)
        saveOperationCount()
        readBaseUrl()
    }

    private fun saveOperationCount() {
        viewModelScope.launch {
            useCase.updateOperationCountUseCase()
            readOperationCount()
        }
    }

    private fun readOperationCount() {
        viewModelScope.launch {
            useCase.readOperationCountUseCase().collectLatest {
                // Log.d(TAG, "readOperationCount: $it")
                _operationCount.value = it
            }
        }
    }

    private fun readBaseUrl() {
        viewModelScope.launch {
            useCase.readBaseUrlUseCase().collectLatest {
                // Log.i(TAG, "readBaseUrl: $it")
                _baseUrl.value = it
                if (!isInitialLoadingFinished) {
                    getWelcomeMessage()
                    getAllUnits()
                    isInitialLoadingFinished = true
                }
            }
        }
    }

    // Get the welcome message in splash screen
    private fun getWelcomeMessage() {
        viewModelScope.launch {
            val url = baseUrl.value + HttpRoutes.WELCOME_MESSAGE
            useCase.getWelcomeMessageUseCase(url = url).collectLatest { result ->
                if (result is GetDataFromRemote.Success) {
                    _message.value = result.data.message
                    //  Log.d(TAG, "getWelcomeMessage: ${message.value}")
                }
                if (result is GetDataFromRemote.Failed) {
                    isInitialLoadingFinished = false
                    sendSplashScreenEvent(SplashScreenEvent.CloseProgressBar)
                    sendSplashScreenEvent(SplashScreenEvent.ShowToast(message = "Server with ${baseUrl.value} down, Change server"))
                    sendSplashScreenEvent(SplashScreenEvent.ShowSetBaseUrlButton)
                    // Log.e(TAG, "getWelcomeMessage: ${result.error.code} ${result.error.message}")
                }
            }
        }
    }


    fun setDate(date: Date) {
        _selectedDate.value = date
    }

    fun setBillNo(value: String) {
        _billNo.value = value
    }

    fun setClientDetails(value: ClientDetails) {
        _selectedClient.value = value
    }

    /* fun setPoNo(value: String) {
         _poNo.value = value
     }

     fun setPayMode(value: PayMode) {
         _payMode.value = value
     }*/


    /*fun setProductName(value: String) {
        _productName.value = value
    }*/

    /*fun setQrCode(value: String) {
        _barCode.value = value
    }*/

    fun setQty(value: String) {
        _qty.value = value
        if (qty.value.isNotEmpty() || qty.value.isNotBlank()) {
            calculateNet()
        }
    }


    /*fun setRate(value: String) {
        _rate.value = value
    }*/

    fun setDisc(value: String) {
        _disc.value = value
        if (_disc.value.isNotEmpty() || _disc.value.isNotBlank()) {
            calculateNet()
        }
    }

    /* fun setTax(value: String) {
         _tax.value = value
     }*/


    private fun sendSplashScreenEvent(splashScreenEvent: SplashScreenEvent) {
        viewModelScope.launch {
            _splashScreenEvent.send(splashScreenEvent)
        }
    }


    fun setClientSearchText(value: String) {
        _clientSearchText.value = value
    }

    // get client details
    fun getClientDetails() {
        sendClientScreenEvent(UiEvent.ShowProgressBar)
        val url = baseUrl.value + HttpRoutes.GET_CLIENT_DETAILS
        try {
            clientDetailsList.clear()
        } catch (e: Exception) {
            // Log.e(TAG, "getClientDetails: ${e.message}")
        }
        viewModelScope.launch {
            useCase.getClientDetailsUseCase(url = url)
                .collectLatest { result ->
                    //Log.i(TAG, "getClientDetails: $result")
                    sendClientScreenEvent(UiEvent.CloseProgressBar)
                    if (result is GetDataFromRemote.Success) {
                        if (result.data.isEmpty()) {
                            sendClientScreenEvent(UiEvent.ShowEmptyList(value = true))
                        } else {
                            clientDetailsList.addAll(result.data)
                            sendClientScreenEvent(UiEvent.ShowEmptyList(value = false))
                        }
                    }
                    if (result is GetDataFromRemote.Failed) {
                        sendClientScreenEvent(UiEvent.ShowSnackBar(message = "url:- $url, code:- ${result.error.code}, error: ${result.error.message}"))
                        sendClientScreenEvent(UiEvent.ShowEmptyList(value = false))
                        isInitialLoadingFinished = false
                        /*Log.e(
                            TAG,
                            "getClientDetails: ${result.error.code}, ${result.error.message}"
                        )*/
                    }
                }
        }
    }

    // Client search
    fun clientSearch() {
        sendClientScreenEvent(UiEvent.ShowProgressBar)
        val url = HttpRoutes.BASE_URL + HttpRoutes.SEARCH_CLIENT_DETAILS + _clientSearchText.value
        try {
            clientDetailsList.clear()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        viewModelScope.launch {
            useCase.searchClientDetailsUseCase(url = url).collectLatest { result ->
                // Log.w(TAG, "clientSearch: $result")
                sendClientScreenEvent(UiEvent.CloseProgressBar)
                if (result is GetDataFromRemote.Success) {
                    if (result.data.isEmpty()) {
                        sendClientScreenEvent(UiEvent.ShowEmptyList(value = true))
                    } else {
                        clientDetailsList.addAll(result.data)
                        sendClientScreenEvent(UiEvent.ShowEmptyList(value = false))
                    }
                }
                if (result is GetDataFromRemote.Failed) {
                    sendClientScreenEvent(UiEvent.ShowSnackBar(message = "url:- $url, code:- ${result.error.code}, error: ${result.error.message}"))
                    sendClientScreenEvent(UiEvent.ShowEmptyList(value = true))
                    /*Log.e(
                        TAG,
                        "clientSearch: $url, code:- ${result.error.code}, error: ${result.error.message}",
                    )*/
                }
            }
        }
    }

    /*
     Product List codes
    */
    fun setProductSearchMode(value: Boolean) {
        _productSearchMode.value = value
    }

    // Get all units
    val unitsList = mutableStateListOf<Units>()

    private fun getAllUnits() {
        val url = _baseUrl.value + HttpRoutes.GET_ALL_UNITS
        viewModelScope.launch {
            useCase.getAllUnitsUseCase(url = url).collectLatest { result ->
                when (result) {
                    is GetDataFromRemote.Success -> {
                        unitsList.addAll(result.data)
                    }
                    is GetDataFromRemote.Failed -> {
                        val error = "Error:- ${result.error.code}, ${result.error.message}, $url"
                        // Log.e(TAG, "getAllUnits: $error")
                        sendHomeScreenEvent(UiEvent.ShowSnackBar(error))
                    }
                    else -> Unit
                }
            }
        }
    }

    fun setUnit(value: Units) {
        _unit.value = value.unitName
        _unitId.value = value.unitId
    }

    // Set product name and search
    private var navCounter = 0
    fun setProductName(value: String, isItFromHomeScreen: Boolean) {
        _productName.value = value
        if (_productName.value.isEmpty()) {
            productList.clear()
            sendProductListScreenEvent(UiEvent.ShowEmptyList(true))
        }
        if (_productName.value.length >= 3 && _productSearchMode.value) {
            Log.i(TAG, "setProductName: ${_productName.value} ")
            searchProductListByName()
            if (isItFromHomeScreen && navCounter<1) {
                sendHomeScreenEvent(UiEvent.Navigate(route = RootNavScreens.ProductListScreen.route))
                navCounter++
            }
        }
    }

    fun resetNavCounter(){
        navCounter = 0
    }

    private fun searchProductListByName() {
        sendProductListScreenEvent(UiEvent.ShowProgressBar)
        val url = baseUrl.value + HttpRoutes.GET_PRODUCT_DETAILS + _productName.value
        try {
            productList.clear()
        } catch (e: Exception) {
            // Log.e(TAG, "getProductDetails: ${e.message}")
        }
        viewModelScope.launch {
            useCase.getProductDetailsUseCase(url = url)
                .collectLatest { result ->
                    sendProductListScreenEvent(UiEvent.CloseProgressBar)
                    // Log.w(TAG, "getProductDetails: $result")
                    if (result is GetDataFromRemote.Success) {
                        if (result.data.isEmpty()) {
                            sendProductListScreenEvent(UiEvent.ShowEmptyList(value = true))
                        } else {
                            try {
                                productList.clear()
                            } catch (e: Exception) {
                                //  Log.e(TAG, "getProductDetails: ${e.message}")
                            }
                            productList.addAll(result.data)
                            sendProductListScreenEvent(UiEvent.ShowEmptyList(value = false))
                        }
                    }
                    if (result is GetDataFromRemote.Failed) {
                        sendProductListScreenEvent(UiEvent.ShowEmptyList(value = true))
                        sendProductListScreenEvent(UiEvent.ShowSnackBar(message = "url:- $url, code:- ${result.error.code}, error: ${result.error.message}"))
                        /* Log.e(
                             TAG,
                             "getProductDetails: ${result.error.code}, ${result.error.message} "
                         )*/
                    }
                }
        }
    }

    fun searchProductByQrCode(value: String) {
        sendHomeScreenEvent(UiEvent.ShowProgressBar)
        val url = baseUrl.value + HttpRoutes.PRODUCT_SEARCH_BY_BARCODE + value
        viewModelScope.launch(Dispatchers.IO) {
            useCase.getProductDetailByBarcodeUseCase(url = url).collectLatest { result ->
                sendHomeScreenEvent(UiEvent.CloseProgressBar)
                if (result is GetDataFromRemote.Success) {
                    Log.e(TAG, "searchProductByQrCode: ${result.data}")
                    result.data?.let { product ->
                        _productName.value = product.productName
                        setSelectedProduct(product)
                        setProductSearchMode(false)
                        return@collectLatest
                    }
                    sendHomeScreenEvent(UiEvent.ShowSnackBar("No productItem with barcode $value"))
                }
                if (result is GetDataFromRemote.Failed) {
                    sendHomeScreenEvent(UiEvent.ShowToastMessage("There have error when scanning ${result.error.message}"))
                    Log.e(TAG, "searchProductByQrCode: ${result.error}")
                }
            }

        }
    }


    fun onProductListItemClicked(product: Product) {
        viewModelScope.launch {
            sendProductListScreenEvent(UiEvent.Navigate(""))
            setSelectedProduct(product = product)
            setProductSearchMode(false)
            resetNavCounter()
        }
    }


    // Calculate net value of a product
    private fun calculateNet() {
        try {
            val qty = _qty.value.toFloat()
            val rate = _rate.value.toFloat()
            var total = qty * rate
            val tax = tax.value.toFloat()
            total += total * (tax / 100)
            val discount = if (_disc.value.isEmpty() || _disc.value.isBlank()) {
                0f
            } else {
                _disc.value.toFloat()
            }
            total -= discount
            total = (total * 100) / 100f
            _net.value = total
        } catch (e: Exception) {
            //Log.e(TAG, "calculateNet: ${e.message}")
        }

    }

    // Add Product to the product list
    fun addToProductList() {
        if (qty.value.isNotEmpty()) {
            val productSelected = ProductSelected(
                productId = _productId.value,
                productName = _productName.value,
                qty = _qty.value.toFloat(),
                productRate = if (_rate.value.isNotBlank() || _rate.value.isNotEmpty()) _rate.value.toFloat() else 0f,
                vat = if (_tax.value.isNotBlank() || _tax.value.isNotEmpty()) _tax.value.toFloat() else 0f,
                barcode = _barCode.value,
                unit = _unit.value,
                unitId = _unitId.value,
                disc = if (_disc.value.isNotBlank() || _disc.value.isNotEmpty()) _disc.value.toFloat() else 0f,
                net = _net.value,
            )
            if (_selectedProductListIndex.value<0) {
                selectedProductList.add(productSelected)
            }else{
                selectedProductList[_selectedProductListIndex.value] = productSelected
            }
            resetSelectedProduct()
        } else {
            sendHomeScreenEvent(UiEvent.ShowSnackBar("Qty is not Added"))
        }
    }


    private val _selectedProductListIndex = mutableStateOf(-1)

    fun setAProductForEditFromList(
        count: Int,
        productSelected: ProductSelected
    ) {
        _selectedProductListIndex.value = count

        _barCode.value = productSelected.barcode
        _productId.value = productSelected.productId
        _productName.value = productSelected.productName
        _disc.value = productSelected.disc.toString()
        _rate.value = productSelected.productRate.toString()
        _unitId.value = productSelected.unitId
        _unit.value = productSelected.unit
        _tax.value = productSelected.vat.toString()

        _qty.value = productSelected.qty.toString()
        _net.value = productSelected.net

        setProductSearchMode(false)
    }

    fun deleteAProductFromSelectedProductList(index:Int){
        selectedProductList.removeAt(index)
    }

    fun setSelectedProduct(product: Product) {
        //_selectedProduct.value = product
        _productName.value = product.productName
        _unit.value = product.unitName
        _unitId.value = product.unitId
        _barCode.value = product.barcode
        _rate.value = product.rate.toString()
        _tax.value = product.vatPercentage.toString()
        _productId.value = product.productId
        _disc.value = product.purchaseDiscount.toString()
        calculateNet()
    }

    fun resetSelectedProduct() {
        setProductSearchMode(true)
        //_selectedProduct.value = null
        _productName.value = ""
        _unit.value = ""
        _unitId.value = 0
        _barCode.value = ""
        _rate.value = ""
        _tax.value = ""
        _productId.value = 0
        _disc.value = ""
        _qty.value = ""
        _net.value = 0f
    }


    private fun sendHomeScreenEvent(uiEvent: UiEvent) {
        viewModelScope.launch {
            _homeScreenEvent.send(HomeScreenEvent(uiEvent = uiEvent))
        }
    }


    private fun sendClientScreenEvent(uiEvent: UiEvent) {
        viewModelScope.launch {
            _clientScreenEvent.send(ClientScreenEvent(uiEvent = uiEvent))
        }
    }

    private fun sendProductListScreenEvent(uiEvent: UiEvent) {
        viewModelScope.launch {
            _productListScreenEvent.send(ProductListScreenEvent(uiEvent = uiEvent))
        }
    }


}