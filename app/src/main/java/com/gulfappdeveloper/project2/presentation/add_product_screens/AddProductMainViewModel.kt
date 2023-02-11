package com.gulfappdeveloper.project2.presentation.add_product_screens

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gulfappdeveloper.project2.data.comon_memmory.CommonMemory
import com.gulfappdeveloper.project2.data.remote.HttpRoutes
import com.gulfappdeveloper.project2.domain.models.remote.get.GetDataFromRemote
import com.gulfappdeveloper.project2.domain.models.remote.get.for_add_product.ProductGroup
import com.gulfappdeveloper.project2.domain.models.remote.get.for_add_product.TaxCategory
import com.gulfappdeveloper.project2.domain.models.remote.get.for_add_product.Units
import com.gulfappdeveloper.project2.domain.models.remote.post.add_product.AddProduct
import com.gulfappdeveloper.project2.domain.models.remote.post.add_product.ProductUnit
import com.gulfappdeveloper.project2.presentation.add_product_screens.presentation.add_product_home_screen.util.AddProductEvent
import com.gulfappdeveloper.project2.presentation.add_product_screens.presentation.select_product_group.util.SelectedProductGroupScreenEvent
import com.gulfappdeveloper.project2.presentation.ui_util.UiEvent
import com.gulfappdeveloper.project2.usecases.UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.system.measureTimeMillis

//private const val TAG = "AddProductMainViewModel"

@HiltViewModel
class AddProductMainViewModel @Inject constructor(
    private val useCase: UseCase,
    commonMemory: CommonMemory
) : ViewModel() {

    private val _navFrom  = mutableStateOf(true)

    private val _addProductEvent = Channel<AddProductEvent>()
    val addProductEvent = _addProductEvent.receiveAsFlow()

    private val _selectedProductGroupEvent = Channel<SelectedProductGroupScreenEvent>()
    val selectedProductGroupEvent = _selectedProductGroupEvent.receiveAsFlow()


    private val _productName = mutableStateOf("")
    val productName: State<String> = _productName

    fun setProductName(value: String) {
        _productName.value = value
    }


    private val _localName = mutableStateOf("")
    val localName: State<String> = _localName

    fun setLocalName(value: String) {
        _localName.value = value
    }


    private val _selectedProductGroup: MutableState<ProductGroup?> = mutableStateOf(null)
    val selectedProductGroup: State<ProductGroup?> = _selectedProductGroup

    /*fun setSelectedProductGroup(value: ProductGroup) {
        _selectedProductGroup.value = value
    }*/

    private val _specification = mutableStateOf("")
    val specification: State<String> = _specification

    fun setSpecification(value: String) {
        _specification.value = value
    }

    private val _barcode = mutableStateOf("")
    val barcode: State<String> = _barcode

    fun setBarcode(value: String) {
        _barcode.value = value
    }

    private val _openingStock = mutableStateOf("0")
    val openingStock: State<String> = _openingStock

    fun setOpeningStock(value: String) {
        _openingStock.value = value
    }

    private val _purchasePrice = mutableStateOf("")
    val purchasePrice: State<String> = _purchasePrice

    fun setPurchasePrice(value: String) {
        _purchasePrice.value = value
    }

    private val _sellingPrice = mutableStateOf("")
    val sellingPrice: State<String> = _sellingPrice

    fun setSellingPrice(value: String) {
        _sellingPrice.value = value
    }

    private val _mrp = mutableStateOf("")
    val mrp: State<String> = _mrp

    fun setMrp(value: String) {
        _mrp.value = value
    }

    private val _purchaseDisc = mutableStateOf("0")
    val purchaseDisc: State<String> = _purchaseDisc

    fun setPurchaseDisc(value: String) {
        _purchaseDisc.value = value
    }

    private val _salesDisc = mutableStateOf("0")
    val salesDisc: State<String> = _salesDisc

    fun setSalesDisc(value: String) {
        _salesDisc.value = value
    }

    private val _taxCategory: MutableState<TaxCategory?> = mutableStateOf(null)
    val taxCategory: State<TaxCategory?> = _taxCategory

    fun setTaxCategory(value: TaxCategory) {
        _taxCategory.value = value
    }

    private val _productUnit: MutableState<Units?> = mutableStateOf(null)
    val productUnit: State<Units?> = _productUnit

    fun setProductUnit(value: Units) {
        _productUnit.value = value
    }

    private val _isInclusive = mutableStateOf(true)
    val isInclusive: State<Boolean> = _isInclusive

    fun setIsInclusive(value: Boolean) {
        _isInclusive.value = value
    }

    private val _isScale = mutableStateOf(false)
    val isScale: State<Boolean> = _isScale

    fun setIsScale(value: Boolean) {
        _isScale.value = value
    }


    val taxCategoryList = mutableStateListOf<TaxCategory>()
    val unitsList = mutableStateListOf<Units>()
    val productGroupsList = mutableStateListOf<ProductGroup>()



    private val _searchText = mutableStateOf("")
    val searchText: State<String> = _searchText

    fun setSearchText(value: String) {
        _searchText.value = value
    }

    private val _baseUrl = mutableStateOf("")
    //val baseUrl: State<String> = _baseUrl

    private var _userId: Short = -1

    init {

        _userId = commonMemory.userId
        _baseUrl.value = commonMemory.baseUrl
        _navFrom.value = commonMemory.addProductNavFrom
        getAllTaxCategories()
        getProductGroups()
        getAllUnits()
    }


    fun setSelectedProductGroups(productGroup: ProductGroup) {
        _selectedProductGroup.value = productGroup
    }

    fun clearSearchText() {
        _searchText.value = ""
    }


    fun getProductGroups() {
        try {
            val elapsedTime  = measureTimeMillis {
                productGroupsList.clear()
            }
            Log.e("Time", "getProductGroups: $elapsedTime", )

        } catch (e: Exception) {
            e.printStackTrace()
        }
        sendSelectedProductGroupEvent(UiEvent.ShowProgressBar)
        val url = _baseUrl.value + HttpRoutes.GET_PRODUCT_GROUPS
        viewModelScope.launch(Dispatchers.IO) {
            useCase.getProductGroupsUseCase(url = url).collectLatest { result ->
                sendSelectedProductGroupEvent(UiEvent.CloseProgressBar)
                when (result) {
                    is GetDataFromRemote.Success -> {

                        productGroupsList.addAll(result.data)

                        //Log.w(TAG, "getProductGroups: ${result.data}")
                        if (result.data.isEmpty()) {
                            sendSelectedProductGroupEvent(UiEvent.ShowEmptyList(true))
                        } else {
                            sendSelectedProductGroupEvent(UiEvent.ShowEmptyList(false))
                            _selectedProductGroup.value = productGroupsList[0]
                        }
                    }
                    is GetDataFromRemote.Failed -> {
                        val error = "Error:- ${result.error.code}, ${result.error.message}, $url"
                        sendSelectedProductGroupEvent(UiEvent.ShowSnackBar(error))
                        sendSelectedProductGroupEvent(UiEvent.ShowEmptyList(true))
                    }
                    else -> Unit
                }

            }

        }
    }

    fun searchProductGroups() {
        productGroupsList.clear()
        sendSelectedProductGroupEvent(UiEvent.ShowProgressBar)
        val url = _baseUrl.value + HttpRoutes.GET_PRODUCT_GROUPS + _searchText.value
        viewModelScope.launch(Dispatchers.IO) {
            useCase.searchProductGroupsUseCase(url = url).collectLatest { result ->
                sendSelectedProductGroupEvent(UiEvent.CloseProgressBar)
                when (result) {
                    is GetDataFromRemote.Success -> {
                        productGroupsList.clear()
                        productGroupsList.addAll(result.data)
                        if (result.data.isEmpty()) {
                            sendSelectedProductGroupEvent(UiEvent.ShowEmptyList(true))
                        }
                    }
                    is GetDataFromRemote.Failed -> {
                        val error = "Error:- ${result.error.code}, ${result.error.message}, $url"
                        sendSelectedProductGroupEvent(UiEvent.ShowSnackBar(error))
                        sendSelectedProductGroupEvent(UiEvent.ShowEmptyList(true))
                    }
                    else -> Unit
                }


            }

        }
    }

    private fun getAllUnits() {
        val url = _baseUrl.value + HttpRoutes.GET_ALL_UNITS
        viewModelScope.launch {
            useCase.getAllUnitsUseCase(url = url).collectLatest { result ->
                when (result) {
                    is GetDataFromRemote.Success -> {
                        unitsList.addAll(result.data)
                        if (unitsList.isNotEmpty()){
                            _productUnit.value = unitsList[0]
                        }
                    }
                    is GetDataFromRemote.Failed -> {
                        val error = "Error:- ${result.error.code}, ${result.error.message}, $url"
                        sendAddProductEvent(UiEvent.ShowSnackBar(error))
                    }
                    else -> Unit
                }
            }
        }

    }

    private fun getAllTaxCategories() {
        val url = _baseUrl.value + HttpRoutes.GET_ALL_TAX_CATEGORIES
        viewModelScope.launch(Dispatchers.IO) {
            useCase.getAllTaxCategoriesUseCase(url = url).collectLatest { result ->
                when (result) {
                    is GetDataFromRemote.Success -> {
                        taxCategoryList.addAll(result.data)
                        if (taxCategoryList.isNotEmpty()) {
                            _taxCategory.value = taxCategoryList[0]
                        }
                    }
                    is GetDataFromRemote.Failed -> {
                        val error = "Error:- ${result.error.code}, ${result.error.message}, $url"
                        sendAddProductEvent(UiEvent.ShowSnackBar(error))
                    }
                    else -> Unit
                }

            }

        }
    }

    fun addProduct() {
        val url = _baseUrl.value + HttpRoutes.ADD_PRODUCT
        sendAddProductEvent(UiEvent.ShowProgressBar)
        val addProduct = AddProduct(
            barcode = _barcode.value,
            isInclusive = _isInclusive.value,
            isScale = _isScale.value,
            localName = _localName.value,
            mrp = if (_mrp.value.isNotEmpty()) _mrp.value.toFloat() else 0f,
            openingStock = if (_openingStock.value.isNotEmpty()) _openingStock.value.toFloat() else 0f,
            pGroupId = _selectedProductGroup.value?.pGroupId ?: 0,
            productCode = 1,
            productId = 1,
            productName = _productName.value,
            purchaseDis = if (_purchaseDisc.value.isNotEmpty()) _purchaseDisc.value.toFloat() else 0f,
            purchasePrice = if (_purchasePrice.value.isNotEmpty()) _purchasePrice.value.toFloat() else 0f,
            saleDis = if (_salesDisc.value.isNotEmpty()) _salesDisc.value.toFloat() else 0f,
            sellingPrice = if (_sellingPrice.value.isNotEmpty()) _sellingPrice.value.toFloat() else 0f,
            specification = _specification.value,
            tCategoryId = _taxCategory.value?.tCategoryId ?: 1,
            unitId = _productUnit.value?.unitId ?: 1,
            userId = _userId.toInt(),
            productUnits = multiUnitProductList
        )
        viewModelScope.launch(Dispatchers.IO) {
            useCase.addProductUseCase(url = url, addProduct = addProduct).collectLatest { result ->
                sendAddProductEvent(UiEvent.CloseProgressBar)
                when (result) {
                    is GetDataFromRemote.Success -> {
                        val addedProduct = result.data
                        if (!_navFrom.value) {
                            sendAddProductEvent(UiEvent.AddedProduct(addedProduct))
                            sendAddProductEvent(UiEvent.Navigate(""))
                        }else{
                            sendAddProductEvent(UiEvent.ShowAlertDialog(""))
                            clearAllAddProductProperties()
                        }

                    }
                    is GetDataFromRemote.Failed -> {
                        val error =
                            "code:- ${result.error.code} message:- ${result.error.message} url:-$url"
                        sendAddProductEvent(UiEvent.ShowSnackBar(message = error))
                    }
                    else -> Unit
                }
            }
        }
    }


    private fun sendAddProductEvent(uiEvent: UiEvent) {
        viewModelScope.launch {
            _addProductEvent.send(AddProductEvent(uiEvent))
        }
    }

    private fun sendSelectedProductGroupEvent(uiEvent: UiEvent) {
        viewModelScope.launch {
            _selectedProductGroupEvent.send(SelectedProductGroupScreenEvent(uiEvent))
        }
    }

    private fun clearAllAddProductProperties(){
        _productName.value = ""
        _localName.value = ""
        _selectedProductGroup.value = null
        _specification.value = ""
        _barcode.value = ""
        _openingStock.value = "0"
        _purchasePrice.value = ""
        _sellingPrice.value = ""
        _mrp.value = ""
        _purchaseDisc.value = "0"
        _salesDisc.value ="0"
        _taxCategory.value = taxCategoryList[0]
        _productUnit.value = unitsList[0]
        _isInclusive.value = true
        _isScale.value = false
        multiUnitProductList.clear()
        clearMultiUnitDataEntryArea()
    }

    // multi unit work

    private val _selectedMultiUnit: MutableState<Units?> = mutableStateOf(null)
    val selectedMultiUnit: State<Units?> = _selectedMultiUnit

    fun setSelectedMultiUnit(value: Units) {
        _selectedMultiUnit.value = value
        _multiUnitProductName.value = _productName.value + "_" + value.unitName
    }

    val multiUnitsList = mutableStateListOf<Units>()

    fun setUnitsForMultiUnitScreen(){
        multiUnitsList.clear()
        multiUnitsList.addAll(unitsList)
        _productUnit.value?.let {pUnit->
            multiUnitsList.removeAll {unit->
                unit.unitId == pUnit.unitId
            }
        }
        // initial multi unit setting
        setSelectedMultiUnit(multiUnitsList[0])

    }




    private val _multiUnitBarcode = mutableStateOf("")
    val multiUnitBarcode: State<String> = _multiUnitBarcode

    fun setMultiUnitBarcode(value: String) {
        _multiUnitBarcode.value = value
    }

    private val _multiUnitProductName = mutableStateOf("")
    val multiUnitProductName: State<String> = _multiUnitProductName

    fun setMultiUnitProductName(value: String) {
        _multiUnitProductName.value = value
    }

    private val _multiUnitQty = mutableStateOf("")
    val multiUnitQty: State<String> = _multiUnitQty

    fun setMultiUnitQty(value: String) {
        _multiUnitQty.value = value

        val qtyInDecimal = if (value.isNotEmpty() || value.isNotBlank()) value.toFloat() else 0f
        val sellingPrice =
            if (_sellingPrice.value.isNotEmpty() || _sellingPrice.value.isNotBlank()) _sellingPrice.value.toFloat() else 0f
        _multiUnitPrice.value = (qtyInDecimal * sellingPrice).toString()
        _multiUnitBarcode.value = _barcode.value+qtyInDecimal.toInt()
    }


    private val _multiUnitPrice = mutableStateOf("")
    val multiUnitPrice: State<String> = _multiUnitPrice

    fun setMultiUnitPrice(value: String) {
        _multiUnitPrice.value = value
    }

    private val _multiUnitOpeningStock = mutableStateOf("")
    val multiUnitOpeningStock: State<String> = _multiUnitOpeningStock

    fun setMultiUnitOpeningStock(value: String) {
        _multiUnitOpeningStock.value = value
    }

    private val _multiUnitIsInclusive = mutableStateOf(false)
    val multiUnitIsInclusive: State<Boolean> = _multiUnitIsInclusive

    fun setMultiUnitIsInclusive(value: Boolean) {
        _multiUnitIsInclusive.value = value
    }

    private val _multiUnitIndexValue = mutableStateOf(1)



    val multiUnitProductList = mutableStateListOf<ProductUnit>()

    fun onAddToMultiUnitListClicked(){
        _multiUnitIndexValue.value++
        val productUnit = ProductUnit(
            barcode = _multiUnitBarcode.value,
            isInclusive = _multiUnitIsInclusive.value,
            openingStock = if(_multiUnitOpeningStock.value.isNotEmpty() || _multiUnitOpeningStock.value.isNotBlank()) _multiUnitOpeningStock.value.toFloat() else 0f,
            proUnitId = 1,
            productId = 1,
            productUnitName = _multiUnitProductName.value,
            salesPrice = _multiUnitPrice.value.toFloat(),
            unitId = _selectedMultiUnit.value?.unitId!!,
            unitQty = _multiUnitQty.value.toFloat(),
            // may be change
            unitType = "${_multiUnitIndexValue.value} Unit"
        )

        multiUnitProductList.add(productUnit)
        clearMultiUnitDataEntryArea()

    }

    fun deleteOneItemFromMultiUnitList(index:Int){
        multiUnitProductList.removeAt(index)
    }

    fun clearMultiUnitDataEntryArea(){
        _selectedMultiUnit.value = null
        _multiUnitQty.value = ""
        _multiUnitProductName.value = ""
        _multiUnitBarcode.value = ""
        _multiUnitPrice.value = ""
        _multiUnitOpeningStock.value = ""
        _multiUnitIsInclusive.value = false
        _multiUnitIndexValue.value = 1
    }

    fun clearMultiUnitProductList(){
        multiUnitProductList.clear()
    }






}