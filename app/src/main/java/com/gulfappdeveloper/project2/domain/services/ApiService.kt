package com.gulfappdeveloper.project2.domain.services

import com.gulfappdeveloper.project2.domain.models.remote.get.ClientDetails
import com.gulfappdeveloper.project2.domain.models.remote.get.GetDataFromRemote
import com.gulfappdeveloper.project2.domain.models.remote.get.Product
import com.gulfappdeveloper.project2.domain.models.remote.get.WelcomeMessage
import com.gulfappdeveloper.project2.domain.models.remote.get.for_add_product.ProductGroup
import com.gulfappdeveloper.project2.domain.models.remote.get.for_add_product.TaxCategory
import com.gulfappdeveloper.project2.domain.models.remote.get.for_add_product.Units
import com.gulfappdeveloper.project2.domain.models.remote.post.AddClient
import com.gulfappdeveloper.project2.domain.models.remote.post.AddProduct
import com.gulfappdeveloper.project2.domain.models.remote.post.PurchaseClass
import kotlinx.coroutines.flow.Flow

interface ApiService {
    suspend fun getWelcomeMessage(url: String):Flow<GetDataFromRemote<WelcomeMessage>>

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
    suspend fun addProduct(url: String,addProduct:AddProduct):Flow<GetDataFromRemote<Product>>

    // Purchase
    suspend fun purchaseFunction(url: String,purchaseClass: PurchaseClass):Flow<GetDataFromRemote<PurchaseClass>>

}