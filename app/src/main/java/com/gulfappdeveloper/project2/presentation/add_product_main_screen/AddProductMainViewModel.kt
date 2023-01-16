package com.gulfappdeveloper.project2.presentation.add_product_main_screen

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gulfappdeveloper.project2.data.remote.HttpRoutes
import com.gulfappdeveloper.project2.domain.models.remote.get.GetDataFromRemote
import com.gulfappdeveloper.project2.domain.models.remote.get.for_add_product.ProductGroup
import com.gulfappdeveloper.project2.domain.models.remote.get.for_add_product.TaxCategory
import com.gulfappdeveloper.project2.domain.models.remote.get.for_add_product.Units
import com.gulfappdeveloper.project2.domain.models.remote.post.AddProduct
import com.gulfappdeveloper.project2.presentation.add_product_main_screen.presentation.add_product_home_screen.util.AddProductEvent
import com.gulfappdeveloper.project2.presentation.add_product_main_screen.presentation.select_product_group.util.SelectedProductGroupScreenEvent
import com.gulfappdeveloper.project2.presentation.ui_util.UiEvent
import com.gulfappdeveloper.project2.usecases.UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "AddProductMainViewModel"

@HiltViewModel
class AddProductMainViewModel @Inject constructor(
    private val useCase: UseCase
) : ViewModel() {
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

    fun setSelectedProductGroup(value: ProductGroup) {
        _selectedProductGroup.value = value
    }

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

    private val _openingStock = mutableStateOf("")
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

    private val _purchaseDisc = mutableStateOf("")
    val purchaseDisc: State<String> = _purchaseDisc

    fun setPurchaseDisc(value: String) {
        _purchaseDisc.value = value
    }

    private val _salesDisc = mutableStateOf("")
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
    val baseUrl: State<String> = _baseUrl


    fun setBaseUrl(value: String) {
        _baseUrl.value = value
        getAllTaxCategories()
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
            productGroupsList.removeAll {
                true
            }
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
                        try {
                            productGroupsList.removeAll {
                                true
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                        productGroupsList.addAll(result.data)
                        Log.w(TAG, "getProductGroups: ${result.data}")
                        if (result.data.isEmpty()) {
                            sendSelectedProductGroupEvent(UiEvent.ShowEmptyList(true))
                        } else {
                            sendSelectedProductGroupEvent(UiEvent.ShowEmptyList(false))
                        }
                    }
                    is GetDataFromRemote.Failed -> {
                        val error = "Error:- ${result.error.code}, ${result.error.message}, $url"
                        Log.e(TAG, "getProductGroups: $error ")
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
                        Log.e(TAG, "search products: $error ")
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
                    }
                    is GetDataFromRemote.Failed -> {
                        val error = "Error:- ${result.error.code}, ${result.error.message}, $url"
                        //Log.e(TAG, "getAllUnits: $error ")
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
                        Log.e(TAG, "getAllTaxCategories: $error ")
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
            userId = 1
        )
        viewModelScope.launch(Dispatchers.IO) {
            useCase.addProductUseCase(url = url, addProduct = addProduct).collectLatest { result ->
                sendAddProductEvent(UiEvent.CloseProgressBar)
                when (result) {
                    is GetDataFromRemote.Success -> {
                        val addedProduct = result.data
                        Log.d(TAG, "addProduct: $addedProduct")
                        sendAddProductEvent(UiEvent.AddedProduct(addedProduct))
                        sendAddProductEvent(UiEvent.Navigate(""))

                    }
                    is GetDataFromRemote.Failed -> {
                        val error =
                            "code:- ${result.error.code} message:- ${result.error.message} url:-$url"
                        Log.e(TAG, "addProduct: $error")
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
}