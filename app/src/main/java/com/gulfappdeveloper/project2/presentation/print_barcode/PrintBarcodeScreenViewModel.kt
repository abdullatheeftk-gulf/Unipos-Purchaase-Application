package com.gulfappdeveloper.project2.presentation.print_barcode

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gulfappdeveloper.project2.data.comon_memmory.CommonMemory
import com.gulfappdeveloper.project2.data.firebase.FirebaseConst
import com.gulfappdeveloper.project2.data.remote.HttpRoutes
import com.gulfappdeveloper.project2.domain.firebase.FirebaseError
import com.gulfappdeveloper.project2.domain.models.barcode_print.BarcodeDesign
import com.gulfappdeveloper.project2.domain.models.barcode_print.BarcodePrintDTL
import com.gulfappdeveloper.project2.domain.models.barcode_print.BarcodePrintSubmit
import com.gulfappdeveloper.project2.domain.models.remote.get.GetDataFromRemote
import com.gulfappdeveloper.project2.domain.models.remote.get.Product
import com.gulfappdeveloper.project2.navigation.root.RootViewModel
import com.gulfappdeveloper.project2.presentation.print_barcode.util.PrintBarcodeScreenEvent
import com.gulfappdeveloper.project2.presentation.ui_util.UiEvent
import com.gulfappdeveloper.project2.presentation.ui_util.localDateToStringConverter
import com.gulfappdeveloper.project2.usecases.UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.util.Date
import javax.inject.Inject

private const val TAG = "PrintBarcodeScreenViewM"

@HiltViewModel
class PrintBarcodeScreenViewModel @Inject constructor(
    private val useCase: UseCase,
    private val commonMemory: CommonMemory
) : ViewModel() {




    private val _printBarcodeScreenEvent = Channel<PrintBarcodeScreenEvent>()
    val printBarcodeScreenEvent = _printBarcodeScreenEvent.receiveAsFlow()

    private val _selectedBarcodeDesignId: MutableState<String?> = mutableStateOf(null)
    //val selectedBarcodeDesignId: State<String?> = _selectedBarcodeDesignId

    private val _selectedBarcodeDesign: MutableState<BarcodeDesign?> = mutableStateOf(null)
    val selectedBarcodeDesign: State<BarcodeDesign?> = _selectedBarcodeDesign

    private val _startingPosition: MutableState<String> = mutableStateOf("0")
    val startingPosition: State<String> = _startingPosition


    @RequiresApi(Build.VERSION_CODES.O)
    private val _expiryDate: MutableState<LocalDate?> = mutableStateOf(LocalDate.now())
    @RequiresApi(Build.VERSION_CODES.O)
    val expiryDate: State<LocalDate?> = _expiryDate

    @RequiresApi(Build.VERSION_CODES.O)
    private val _manufactureDate: MutableState<LocalDate?> = mutableStateOf(LocalDate.now())
    @RequiresApi(Build.VERSION_CODES.O)
    val manufactureDate: State<LocalDate?> = _manufactureDate

    private val selectedBarcodePrintDetailsList = mutableStateListOf<BarcodePrintDTL>()

    val selectedProductList = mutableStateListOf<Pair<Product, Int>>()

    fun editSelectedProduct(count: Int){
        selectedProductList.forEachIndexed {index,pair->
            if(index==count){
                _product.value = pair.first
                _productQty.value = pair.second.toString()
                _enteredBarcode.value = pair.first.barcode

            }
        }
        selectedProductList.removeAt(count)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun deleteOneItemFromTheSelectedProductList(count:Int){

        selectedProductList.removeAt(count)
    }

    fun onSelectedProductClickedForEdit(count:Int,pair: Pair<Product, Int>){
        sendBarcodeScreenEvent(UiEvent.ShowBarcodeDesignShowSelectedProductEditOrDeleteAlertDialog(count = count, pair = pair))
    }



    private fun addBarcodePrintDetailsList(barcodePrintDTL: BarcodePrintDTL) {
        selectedBarcodePrintDetailsList.add(barcodePrintDTL)
    }


    private val _productQty: MutableState<String> = mutableStateOf("0")
    val productQty: State<String> = _productQty

    fun setProductQty(value: String) {
        _productQty.value = value
    }

    private val _product: MutableState<Product?> = mutableStateOf(null)
    val product: State<Product?> = _product

    private val _enteredBarcode: MutableState<String> = mutableStateOf("")
    val enteredBarcode: State<String> = _enteredBarcode

    fun setEnteredBarcode(value: String) {
        if (value == "\n") {
            searchProductByQrCode(_enteredBarcode.value)
        }
        _enteredBarcode.value = value
    }

    private var _showSubmittedAlertDialog:MutableState<Boolean> = mutableStateOf(false)
    val showSubmittedAlertDialog:State<Boolean> = _showSubmittedAlertDialog

    fun setShowSubmitAlertDialog(value:Boolean){
        _showSubmittedAlertDialog.value = value
    }




    init {
        getBarcodeDesigns()
    }

    private fun getBarcodeDesigns() {
        viewModelScope.launch {
            val url = commonMemory.baseUrl + HttpRoutes.GET_BARCODE_DESIGNS
            useCase.getBarcodeDesignsUseCase(url = url).collectLatest { value ->
                when (value) {
                    is GetDataFromRemote.Loading -> {
                        sendBarcodeScreenEvent(UiEvent.ShowProgressBar)
                    }

                    is GetDataFromRemote.Success -> {
                        sendBarcodeScreenEvent(UiEvent.CloseProgressBar)
                        val result = value.data
                        sendBarcodeScreenEvent(UiEvent.GetBarcodeDesigns(result))
                    }

                    is GetDataFromRemote.Failed -> {
                        val error = value.error
                        val errorMessage = "${error.message},code:- ${error.code}"
                        Log.e(TAG, "getBarcodeDesigns: $error, \n $url")
                        sendBarcodeScreenEvent(UiEvent.CloseProgressBar)
                        sendBarcodeScreenEvent(UiEvent.ShowErrorValue(errorMessage))
                        sendBarcodeScreenEvent(UiEvent.ShowSnackBar(errorMessage))
                    }
                }

            }
        }
    }

    private fun sendBarcodeScreenEvent(uiEvent: UiEvent) {
        viewModelScope.launch {
            _printBarcodeScreenEvent.send(PrintBarcodeScreenEvent(uiEvent))
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun setExpiryDate(date: LocalDate?) {
        _expiryDate.value = date
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun setManuFactureDate(date: LocalDate?) {
        _manufactureDate.value = date
    }

    fun setSelectedBarcodeDesign(barcodeDesign: BarcodeDesign) {
        _selectedBarcodeDesign.value = barcodeDesign
        _selectedBarcodeDesignId.value = barcodeDesign.designId.toString()
    }

    fun setStartingPosition(startingPosition: String) {
        _startingPosition.value = startingPosition
    }


    fun searchProductByQrCode(value: String) {

        sendBarcodeScreenEvent(UiEvent.ShowProgressBar)

        val url = commonMemory.baseUrl + HttpRoutes.PRODUCT_SEARCH_BY_BARCODE + value

        viewModelScope.launch(Dispatchers.IO) {

            useCase.getProductDetailByBarcodeUseCase(url = url).collectLatest { result ->
                sendBarcodeScreenEvent(UiEvent.CloseProgressBar)
                if (result is GetDataFromRemote.Success) {
                    result.data?.let { product ->

                        _product.value = product
                        _productQty.value = "1"

                        return@collectLatest
                    }
                    sendBarcodeScreenEvent(UiEvent.ShowSnackBar("No productItem with barcode $value"))
                }
                if (result is GetDataFromRemote.Failed) {
                    sendBarcodeScreenEvent(UiEvent.ShowToastMessage("There have error when scanning ${result.error.message}"))
                    val error = result.error
                    useCase.insertErrorDataToFireStoreUseCase(
                        collectionName = FirebaseConst.COLLECTION_NAME_FOR_ERROR,
                        documentName = "searchProductByQrCode,${Date()}",
                        errorData = FirebaseError(
                            errorMessage = error.message ?: "",
                            errorCode = error.code,
                            ipAddress = "",
                            url = url
                        )
                    )
                }
            }

        }
    }


    fun addSelectedProductList(product: Product?, qty: Int) {
        if (product == null) {
            sendBarcodeScreenEvent(UiEvent.ShowSnackBar("No product is selected"))
            return
        }
        if (qty == 0) {
            sendBarcodeScreenEvent(UiEvent.ShowSnackBar("Product qty is zero"))
            return
        }
        selectedProductList.add(Pair(first = product, second = qty))
        _product.value = null
        _productQty.value = "0"
        setEnteredBarcode("")
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun onPrintBarcode() {
        if (_selectedBarcodeDesignId.value == null) {
            sendBarcodeScreenEvent(UiEvent.ShowBarcodeDesignNotSelectedError)
            return
        }
        if (_expiryDate.value == null) {
            sendBarcodeScreenEvent(UiEvent.ShowBarcodeDesignExpiryDateNotSelectedError)
            return
        }
        if (_manufactureDate.value == null) {
            sendBarcodeScreenEvent(UiEvent.ShowBarcodeDesignManufactureDateNotSelectedError)
            return
        }
        selectedProductList.forEach { pair ->
            addBarcodePrintDetailsList(
                barcodePrintDTL = BarcodePrintDTL(
                    barcode = pair.first.barcode,
                    quantity = pair.second.toString()
                )
            )
        }

        if (selectedBarcodePrintDetailsList.isEmpty()) {
            sendBarcodeScreenEvent(UiEvent.ShowBarcodeDesignSelectedBarcodeDesignsEmptyError)
            return
        }


        sendBarcodeScreenEvent(UiEvent.ShowProgressBar)


        val url = commonMemory.baseUrl + HttpRoutes.GET_BARCODE_DESIGNS
        Log.d(TAG, "onPrintBarcode: $url")
        viewModelScope.launch(Dispatchers.IO) {
            useCase.submitBarcodesToPrintUseCase(
                url = url,
                barcodePrintSubmit = BarcodePrintSubmit(
                    barcodeDesignId = _selectedBarcodeDesignId.value!!,
                    startingPosition = _startingPosition.value,
                    expairyDate = _expiryDate.value?.localDateToStringConverter()!!,
                    manuFactDate = _manufactureDate.value?.localDateToStringConverter()!!,
                    barcodePrintDTLs = selectedBarcodePrintDetailsList.toList()
                )
            ).collectLatest {value ->
                when(value){
                    is GetDataFromRemote.Loading->{

                    }
                    is GetDataFromRemote.Success->{
                        Log.d(TAG, "onPrintBarcode: ${value.data}")
                        sendBarcodeScreenEvent(UiEvent.CloseProgressBar)
                        _showSubmittedAlertDialog.value = true
                    }
                    is GetDataFromRemote.Failed->{
                        val errorMessage = "${value.error.message}, ${value.error.code}"
                        Log.e(TAG, "onPrintBarcode: $errorMessage", )
                        sendBarcodeScreenEvent(UiEvent.CloseProgressBar)
                        sendBarcodeScreenEvent(UiEvent.ShowSnackBar(message = errorMessage))
                    }
                }

            }
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun resetAllValues() {
        _enteredBarcode.value = ""
        _product.value = null
        _productQty.value = "0"
        _startingPosition.value = "0"
        _selectedBarcodeDesignId.value = null
        _expiryDate.value = null
        _manufactureDate.value = null
        selectedBarcodePrintDetailsList.clear()
        selectedProductList.clear()
    }


    fun collectCameraScannedValue(rootViewModel: RootViewModel){
        viewModelScope.launch {
            rootViewModel.productSearchByBarcodeTransferData.collectLatest {productBarcodeSearchTransfer->
                val product = productBarcodeSearchTransfer?.product
                val error = productBarcodeSearchTransfer?.error

                Log.w(TAG, "observeCameraScannedValue: $productBarcodeSearchTransfer")

                product?.let {prod ->
                    _product.value = prod
                    _productQty.value = "1"
                    _enteredBarcode.value = prod.barcode

                }

                error?.let {e->
                    val errorMessage = e.message+","+e.code
                    sendBarcodeScreenEvent(UiEvent.ShowSnackBar(errorMessage))
                }
            }
        }

    }




   /* private val _testFlow = MutableStateFlow(0)
    val testFlow:StateFlow<Int> = _testFlow
    fun testObserver(rootViewModel: RootViewModel){
        viewModelScope.launch {
            rootViewModel.testFlow.collectLatest {
                _testFlow.value = it
            }
        }

    }*/
}