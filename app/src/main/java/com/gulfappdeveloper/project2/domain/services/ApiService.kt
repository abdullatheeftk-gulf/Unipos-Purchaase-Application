package com.gulfappdeveloper.project2.domain.services

import com.gulfappdeveloper.project2.domain.models.remote.get.ClientDetails
import com.gulfappdeveloper.project2.domain.models.remote.get.GetDataFromRemote
import com.gulfappdeveloper.project2.domain.models.remote.get.Product
import com.gulfappdeveloper.project2.domain.models.remote.get.WelcomeMessage
import com.gulfappdeveloper.project2.domain.models.remote.get.for_add_product.ProductGroup
import com.gulfappdeveloper.project2.domain.models.remote.get.for_add_product.TaxCategory
import com.gulfappdeveloper.project2.domain.models.remote.get.for_add_product.Units
import com.gulfappdeveloper.project2.domain.models.remote.get.license.LicenseRequestBody
import com.gulfappdeveloper.project2.domain.models.remote.get.license.LicenseResponse
import com.gulfappdeveloper.project2.domain.models.remote.get.login.User
import com.gulfappdeveloper.project2.domain.models.remote.get.see_ip.SeeIp
import com.gulfappdeveloper.project2.domain.models.remote.get.stock_adjustment.ProductStock
import com.gulfappdeveloper.project2.domain.models.remote.post.AddClient
import com.gulfappdeveloper.project2.domain.models.remote.post.add_product.AddProduct
import com.gulfappdeveloper.project2.domain.models.remote.post.PurchaseClass
import com.gulfappdeveloper.project2.domain.models.remote.post.stoke_adjustment.StockAdjustment
import kotlinx.coroutines.flow.Flow

interface ApiService {
    suspend fun getWelcomeMessage(url: String):Flow<GetDataFromRemote<WelcomeMessage>>

    suspend fun loginUser(url: String): Flow<GetDataFromRemote<User>>
    suspend fun uniLicenseActivation(
        rioLabKey: String,
        url: String,
        licenseRequestBody: LicenseRequestBody
    ): Flow<GetDataFromRemote<LicenseResponse>>

    suspend fun getClientDetails(url:String):Flow<GetDataFromRemote<List<ClientDetails>>>
    suspend fun searchClientDetails(url:String):Flow<GetDataFromRemote<List<ClientDetails>>>
    suspend fun addClientDetails(url:String,addClient: AddClient):Flow<GetDataFromRemote<AddClient>>

    suspend fun getProductDetailsByName(url: String):Flow<GetDataFromRemote<List<Product>>>
    suspend fun getProductDetailByBarcode(url: String):Flow<GetDataFromRemote<Product?>>

    // Add Product
    suspend fun getProductGroups(url:String):Flow<GetDataFromRemote<List<ProductGroup>>>
    suspend fun searchProductGroups(url: String):Flow<GetDataFromRemote<List<ProductGroup>>>
    suspend fun getAllUnits(url: String):Flow<GetDataFromRemote<List<Units>>>
    suspend fun getAllTaxCategories(url: String):Flow<GetDataFromRemote<List<TaxCategory>>>
    suspend fun addProduct(url: String,addProduct: AddProduct):Flow<GetDataFromRemote<Product>>

    // Purchase
    suspend fun purchaseFunction(url: String,purchaseClass: PurchaseClass):Flow<GetDataFromRemote<PurchaseClass>>

    // Stock adjustment
    suspend fun getStockOfAProduct(url: String):Flow<GetDataFromRemote<ProductStock?>>
    suspend fun adjustStocksOfProductList(url: String,stockAdjustment:StockAdjustment):Flow<GetDataFromRemote<StockAdjustment>>

    // get ip address
    suspend fun getIp4Address(url: String):Flow<GetDataFromRemote<SeeIp>>



}