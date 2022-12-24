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
import com.gulfappdeveloper.project2.domain.models.remote.get.ProductDetails
import com.gulfappdeveloper.project2.domain.models.util.PayMode
import com.gulfappdeveloper.project2.presentation.client_screen.util.ClientScreenUIEvent
import com.gulfappdeveloper.project2.presentation.home_screen.util.ProductUnit
import com.gulfappdeveloper.project2.presentation.splash_screen.util.SplashScreenEvent
import com.gulfappdeveloper.project2.presentation.ui_util.UiEvent
import com.gulfappdeveloper.project2.usecases.UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject
import kotlin.math.roundToInt

private const val TAG = "RootViewModel"

@HiltViewModel
class RootViewModel @Inject constructor(
    private val useCase: UseCase
) : ViewModel() {

    private var isInitialLoadingFinished = false

    private val _splashScreenEvent = Channel<SplashScreenEvent>()
    val splashScreenEvent = _splashScreenEvent.receiveAsFlow()

    private val _clientScreenEvent = Channel<ClientScreenUIEvent>()
    val clientScreenEvent = _clientScreenEvent.receiveAsFlow()

    private val _operationCount = mutableStateOf(0)
    private val operationCount: State<Int> = _operationCount


    private val _baseUrl = mutableStateOf(HttpRoutes.BASE_URL)
    val baseUrl: State<String> = _baseUrl

    private val _message = mutableStateOf("")
    val message: State<String> = _message

    private val _billNo = mutableStateOf("")
    val billNo: State<String> = _billNo

    private val _selectedDate = mutableStateOf(Date())
    val selectedDate: State<Date> = _selectedDate

    private val _selectedClient: MutableState<ClientDetails?> = mutableStateOf(null)
    val selectedClient: State<ClientDetails?> = _selectedClient

    private val _poNo = mutableStateOf("")
    val poNo: State<String> = _poNo

    private val _payMode = mutableStateOf(PayMode.Cash)
    val payMode: State<PayMode> = _payMode

    val productDetailsList = mutableStateListOf<ProductDetails>()

    private val _selectedProduct: MutableState<ProductDetails?> = mutableStateOf(null)
    val selectedProduct: State<ProductDetails?> = _selectedProduct

    private val _productId = mutableStateOf(0)
    private val productId: State<Int> = _productId

    private val _productName = mutableStateOf("")
    val productName: State<String> = _productName

    private val _qrCode = mutableStateOf("")
    val qrCode: State<String> = _qrCode

    private val _qty = mutableStateOf("")
    val qty: State<String> = _qty

    private val _unit = mutableStateOf(ProductUnit.Kg)
    val unit: State<ProductUnit> = _unit

    private val _rate = mutableStateOf("")
    val rate: State<String> = _rate

    private val _disc = mutableStateOf("")
    val disc: State<String> = _disc

    private val _tax = mutableStateOf("")
    val tax: State<String> = _tax

    private val _net = mutableStateOf(0f)
    val net:State<Float> = _net

    private val _clientSearchText = mutableStateOf("")
    val clientSearchText:State<String> = _clientSearchText

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
                Log.d(TAG, "readOperationCount: $it")
                _operationCount.value = it
            }
        }
    }

    private fun readBaseUrl() {
        viewModelScope.launch {
            useCase.readBaseUrlUseCase().collectLatest {
                Log.i(TAG, "readBaseUrl: $it")
                _baseUrl.value = it
                if (!isInitialLoadingFinished) {
                    getWelcomeMessage()
                   // getClientDetails()
                   // getProductDetails()
                    isInitialLoadingFinished = true
                }

            }
        }
    }

    private fun getWelcomeMessage() {
        viewModelScope.launch {
            val url = baseUrl.value + HttpRoutes.WELCOME_MESSAGE
            useCase.getWelcomeMessageUseCase(url = url).collectLatest { result ->
                if (result is GetDataFromRemote.Success) {
                    _message.value = result.data.message
                    Log.d(TAG, "getWelcomeMessage: ${message.value}")
                }
                if (result is GetDataFromRemote.Failed) {
                    isInitialLoadingFinished = false
                    sendSplashScreenEvent(SplashScreenEvent.CloseProgressBar)
                    sendSplashScreenEvent(SplashScreenEvent.ShowToast(message = "Server with ${baseUrl.value} down, Change server"))
                    sendSplashScreenEvent(SplashScreenEvent.ShowSetBaseUrlButton)
                    Log.e(TAG, "getWelcomeMessage: ${result.error.code} ${result.error.message}")
                }
            }
        }
    }

    private fun getProductDetails() {
        try {
            productDetailsList.removeAll {
                true
            }
        } catch (e: Exception) {
            Log.e(TAG, "getProductDetails: ${e.message}")
        }
        viewModelScope.launch {
            useCase.getProductDetailsUseCase(url = baseUrl.value + HttpRoutes.GET_PRODUCT_DETAILS)
                .collectLatest { result ->
                    Log.w(TAG, "getProductDetails: $result")
                    if (result is GetDataFromRemote.Success) {
                        productDetailsList.addAll(result.data)

                    }
                    if (result is GetDataFromRemote.Failed) {
                        Log.e(
                            TAG,
                            "getProductDetails: ${result.error.code}, ${result.error.message} "
                        )
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

    fun setPoNo(value: String) {
        _poNo.value = value
    }

    fun setPayMode(value: PayMode) {
        _payMode.value = value
    }

    fun setSelectedProduct(productDetails: ProductDetails) {
        _selectedProduct.value = productDetails
        _productName.value = productDetails.productName
        _unit.value = ProductUnit.valueOf(productDetails.unit)
        _rate.value = productDetails.productRate.toString()
        _tax.value = productDetails.vat.toString()
        _productId.value = productDetails.productId
    }

    fun setProductName(value: String) {
        _productName.value = value
    }

    fun setQrCode(value: String) {
        _qrCode.value = value
    }

    fun setQty(value: String) {
        _qty.value = value
        if (qty.value.isNotEmpty() || qty.value.isNotBlank()) {
            calculateNet()
        }
    }

    fun setUnit(value: ProductUnit) {
        _unit.value = value
    }

    fun setRate(value: String) {
        _rate.value = value
    }

    fun setDisc(value: String) {
        _disc.value = value
        if (disc.value.isNotEmpty() || disc.value.isNotBlank()) {
            calculateNet()
        }
    }

    fun setTax(value: String) {
        _tax.value = value
    }



    fun calculateNet() {
        try {
            val qty = qty.value.toFloat()
            val rate = rate.value.toFloat()
            var total = qty * rate
            val tax = tax.value.toFloat()
            total += total * (tax / 100)
            val discount = if (disc.value.isEmpty() || disc.value.isBlank()) {
                0f
            } else {
                disc.value.toFloat()
            }
            total -= discount
            total = (total.roundToInt() * 100) / 100f
            _net.value = total
        } catch (e: Exception) {
            Log.e(TAG, "calculateNet: ${e.message}")
        }

    }


    private fun sendSplashScreenEvent(splashScreenEvent: SplashScreenEvent) {
        viewModelScope.launch {
            _splashScreenEvent.send(splashScreenEvent)
        }
    }

    fun addToProductList() {
        if (net.value > 0f) {
            val productSelected = ProductSelected(
                productId = productId.value,
                productName = productName.value,
                qty = if (qty.value.isNotBlank() || rate.value.isNotEmpty()) rate.value.toFloat() else 0f,
                productRate = if (rate.value.isNotBlank() || rate.value.isNotEmpty()) rate.value.toFloat() else 0f,
                vat = if (tax.value.isNotBlank() || tax.value.isNotEmpty()) tax.value.toFloat() else 0f,
                barcode = qrCode.value,
                unit = unit.value.name,
                disc = if (tax.value.isNotBlank() || tax.value.isNotEmpty()) tax.value.toFloat() else 0f,
                net = net.value
            )
            selectedProductList.add(productSelected)
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
            clientDetailsList.removeAll {
                true
            }
        } catch (e: Exception) {
            Log.e(TAG, "getClientDetails: ${e.message}")
        }
        viewModelScope.launch {
            useCase.getClientDetailsUseCase(url = url)
                .collectLatest { result ->
                    Log.i(TAG, "getClientDetails: $result")
                    sendClientScreenEvent(UiEvent.CloseProgressBar)
                    if (result is GetDataFromRemote.Success) {
                        if (result.data.isEmpty()){
                            sendClientScreenEvent(UiEvent.ShowEmptyList(value = true))
                        }else {
                            clientDetailsList.addAll(result.data)
                            sendClientScreenEvent(UiEvent.ShowEmptyList(value = false))
                        }
                    }
                    if (result is GetDataFromRemote.Failed) {
                        sendClientScreenEvent(UiEvent.ShowSnackBar(message = "url:- $url, code:- ${result.error.code}, error: ${result.error.message}"))
                        sendClientScreenEvent(UiEvent.ShowEmptyList(value = false))
                        isInitialLoadingFinished = false
                        Log.e(TAG, "getClientDetails: ${result.error.code}, ${result.error.message}")
                    }
                }
        }
    }

    // Client search
    fun clientSearch() {
        sendClientScreenEvent(UiEvent.ShowProgressBar)
        val url = HttpRoutes.BASE_URL+HttpRoutes.SEARCH_CLIENT_DETAILS+_clientSearchText.value
        try {
            clientDetailsList.removeAll{
                true
            }
        }catch (e:Exception){
            e.printStackTrace()
        }

        viewModelScope.launch {
            useCase.searchClentDetailsUseCase(url = url).collectLatest { result->
                Log.w(TAG, "clientSearch: $result")
                sendClientScreenEvent(UiEvent.CloseProgressBar)
                if (result is GetDataFromRemote.Success) {
                    if (result.data.isEmpty()){
                        sendClientScreenEvent(UiEvent.ShowEmptyList(value = true))
                    }else {
                        clientDetailsList.addAll(result.data)
                        sendClientScreenEvent(UiEvent.ShowEmptyList(value = false))
                    }
                }
                if (result is GetDataFromRemote.Failed){
                    sendClientScreenEvent(UiEvent.ShowSnackBar(message = "url:- $url, code:- ${result.error.code}, error: ${result.error.message}"))
                    sendClientScreenEvent(UiEvent.ShowEmptyList(value = true))
                    Log.e(TAG, "clientSearch: $url, code:- ${result.error.code}, error: ${result.error.message}", )
                }
            }
        }
    }


    private fun sendClientScreenEvent(uiEvent: UiEvent) {
        viewModelScope.launch {
            _clientScreenEvent.send(ClientScreenUIEvent(uiEvent = uiEvent))
        }
    }


}