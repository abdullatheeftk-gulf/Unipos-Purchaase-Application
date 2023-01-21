package com.gulfappdeveloper.project2.usecases

import com.gulfappdeveloper.project2.usecases.data_store_use_cases.base_url_use_cases.ReadBaseUrlUseCase
import com.gulfappdeveloper.project2.usecases.data_store_use_cases.base_url_use_cases.SaveBaseUrlUseCase
import com.gulfappdeveloper.project2.usecases.data_store_use_cases.device_id_use_case.ReadDeviceIdUseCase
import com.gulfappdeveloper.project2.usecases.data_store_use_cases.device_id_use_case.SaveDeviceIdUseCase
import com.gulfappdeveloper.project2.usecases.data_store_use_cases.ip_address_usecases.ReadIpAddressUseCase
import com.gulfappdeveloper.project2.usecases.data_store_use_cases.ip_address_usecases.SaveIpAddressUseCase
import com.gulfappdeveloper.project2.usecases.data_store_use_cases.operation_counter_use_cases.ReadOperationCountUseCase
import com.gulfappdeveloper.project2.usecases.data_store_use_cases.operation_counter_use_cases.UpdateOperationCountUseCase
import com.gulfappdeveloper.project2.usecases.data_store_use_cases.serial_counter_usecases.ReadSerialNoCountUseCase
import com.gulfappdeveloper.project2.usecases.remote_usecase.get.add_product.GetAllTaxCategoriesUseCase
import com.gulfappdeveloper.project2.usecases.remote_usecase.get.add_product.GetAllUnitsUseCase
import com.gulfappdeveloper.project2.usecases.remote_usecase.get.add_product.GetProductGroupsUseCase
import com.gulfappdeveloper.project2.usecases.remote_usecase.get.add_product.SearchProductGroupsUseCase
import com.gulfappdeveloper.project2.usecases.remote_usecase.get.client.GetClientDetailsUseCase
import com.gulfappdeveloper.project2.usecases.remote_usecase.get.client.SearchClientDetailsUseCase
import com.gulfappdeveloper.project2.usecases.remote_usecase.get.product.GetProductDetailByBarcodeUseCase
import com.gulfappdeveloper.project2.usecases.remote_usecase.get.product.GetProductDetailsByNameUseCase
import com.gulfappdeveloper.project2.usecases.remote_usecase.get.welcome.GetWelcomeMessageUseCase
import com.gulfappdeveloper.project2.usecases.remote_usecase.post.add_client.AddClientUseCase
import com.gulfappdeveloper.project2.usecases.remote_usecase.post.add_product.AddProductUseCase
import com.gulfappdeveloper.project2.usecases.remote_usecase.post.purchase.PurchaseUseCase
import com.gulfappdeveloper.project2.usecases.remote_usecase.stock_adjustment.AdjustStocksOfProductListUseCase
import com.gulfappdeveloper.project2.usecases.remote_usecase.stock_adjustment.GetStockOfAProductUseCase
import com.gulfappdeveloper.project2.usecases.data_store_use_cases.serial_counter_usecases.UpdateSerialNoUseCase
import com.gulfappdeveloper.project2.usecases.data_store_use_cases.uni_license_save_use_cases.UniLicenseReadUseCase
import com.gulfappdeveloper.project2.usecases.data_store_use_cases.uni_license_save_use_cases.UniLicenseSaveUseCase
import com.gulfappdeveloper.project2.usecases.remote_usecase.get.ip.GetIp4AddressUseCase
import com.gulfappdeveloper.project2.usecases.remote_usecase.get.license.UniLicenseActivationUseCase
import com.gulfappdeveloper.project2.usecases.remote_usecase.get.login.LoginUseCase

data class UseCase(

    val updateOperationCountUseCase: UpdateOperationCountUseCase,
    val saveBaseUrlUseCase: SaveBaseUrlUseCase,
    val updateSerialNoUseCase: UpdateSerialNoUseCase,
    val saveIpAddressUseCase: SaveIpAddressUseCase,
    val uniLicenseSaveUseCase: UniLicenseSaveUseCase,
    val saveDeviceIdUseCase: SaveDeviceIdUseCase,


    val readOperationCountUseCase: ReadOperationCountUseCase,
    val readBaseUrlUseCase: ReadBaseUrlUseCase,
    val readSerialNoCountUseCase: ReadSerialNoCountUseCase,
    val readIpAddressUseCase: ReadIpAddressUseCase,
    val uniLicenseReadUseCase: UniLicenseReadUseCase,
    val readDeviceIdUseCase: ReadDeviceIdUseCase,


    val getWelcomeMessageUseCase: GetWelcomeMessageUseCase,

    val loginUseCase: LoginUseCase,

    val uniLicenseActivationUseCase: UniLicenseActivationUseCase,

    val getIp4AddressUseCase: GetIp4AddressUseCase,

    val getClientDetailsUseCase: GetClientDetailsUseCase,
    val searchClientDetailsUseCase: SearchClientDetailsUseCase,

    val getProductDetailsUseCase: GetProductDetailsByNameUseCase,
    val getProductDetailByBarcodeUseCase: GetProductDetailByBarcodeUseCase,

    // add product group
    val getProductGroupsUseCase: GetProductGroupsUseCase,
    val searchProductGroupsUseCase: SearchProductGroupsUseCase,
    val getAllUnitsUseCase: GetAllUnitsUseCase,
    val getAllTaxCategoriesUseCase: GetAllTaxCategoriesUseCase,

    // post
    val addClientUseCase: AddClientUseCase,
    val addProductUseCase: AddProductUseCase,


    // purchase
    val purchaseUseCase: PurchaseUseCase,

    // Stock adjustment
    val getStockOfAProductUseCase: GetStockOfAProductUseCase,
    val adjustStocksOfProductListUseCase: AdjustStocksOfProductListUseCase
)