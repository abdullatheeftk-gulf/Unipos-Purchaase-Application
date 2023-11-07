package com.gulfappdeveloper.project2.repositories

import com.gulfappdeveloper.project2.data.comon_memmory.CommonMemory
import com.gulfappdeveloper.project2.domain.models.remote.Error
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
import com.gulfappdeveloper.project2.domain.models.remote.get.price_adjustment.ProductForPriceAdjustment
import com.gulfappdeveloper.project2.domain.models.remote.get.see_ip.SeeIp
import com.gulfappdeveloper.project2.domain.models.remote.get.stock_adjustment.ProductStock
import com.gulfappdeveloper.project2.domain.models.remote.post.AddClient
import com.gulfappdeveloper.project2.domain.models.remote.post.add_product.AddProduct
import com.gulfappdeveloper.project2.domain.models.remote.post.PurchaseClass
import com.gulfappdeveloper.project2.domain.models.remote.post.price_adjustment.PriceAdjustment
import com.gulfappdeveloper.project2.domain.models.remote.post.stoke_adjustment.StockAdjustment
import com.gulfappdeveloper.project2.domain.services.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteRepository @Inject constructor(
    private val apiService: ApiService,
    private val commonMemory:CommonMemory
) {

    suspend fun getWelcomeMessage(url: String): Flow<GetDataFromRemote<WelcomeMessage>> {
        return apiService.getWelcomeMessage(url = url)
    }

    suspend fun loginUser(url: String): Flow<GetDataFromRemote<User>> {
        return apiService.loginUser(url = url)
    }


    suspend fun getIp4Address(url: String): Flow<GetDataFromRemote<String>> {
        return apiService.getIp4Address(url = url)
    }

    suspend fun getClientDetails(url: String): Flow<GetDataFromRemote<List<ClientDetails>>> {
        return apiService.getClientDetails(url = url)
    }

    suspend fun searchClientDetails(url: String): Flow<GetDataFromRemote<List<ClientDetails>>> {
        return apiService.searchClientDetails(url = url)
    }

    suspend fun getProductDetailsByName(url: String): Flow<GetDataFromRemote<List<Product>>> {
        return apiService.getProductDetailsByName(url = url)
    }

    suspend fun getProductDetailByBarcode(url: String): Flow<GetDataFromRemote<Product?>> {
        return apiService.getProductDetailByBarcode(url = url)
    }


    // Post remote repositories
    suspend fun addClientDetails(
        url: String,
        addClient: AddClient
    ): Flow<GetDataFromRemote<AddClient>> {
        return apiService.addClientDetails(url = url, addClient = addClient)
    }

    suspend fun addProduct(
        url: String,
        addProduct: AddProduct
    ): Flow<GetDataFromRemote<Product>> {
        return apiService.addProduct(url = url, addProduct = addProduct)
    }

    suspend fun getProductGroups(url: String): Flow<GetDataFromRemote<List<ProductGroup>>> {
        return apiService.getProductGroups(url = url)
    }

    suspend fun searchProductGroups(url: String): Flow<GetDataFromRemote<List<ProductGroup>>> {
        return apiService.searchProductGroups(url = url)
    }

    suspend fun getAllUnits(url: String): Flow<GetDataFromRemote<List<Units>>> {
        return apiService.getAllUnits(url = url)
    }

    suspend fun getAllTaxCategories(url: String): Flow<GetDataFromRemote<List<TaxCategory>>> {
        return apiService.getAllTaxCategories(url = url)
    }

    suspend fun purchaseFunction(
        url: String,
        purchaseClass: PurchaseClass
    ): Flow<GetDataFromRemote<PurchaseClass>> {
        return if (commonMemory.firebaseLicense) {
            apiService.purchaseFunction(
                url = url,
                purchaseClass = purchaseClass
            )
        }else{
            flow {
                GetDataFromRemote.Failed(
                    error = Error(
                        code = 450,
                        message = "App License Error"
                    )
                )
            }
        }
    }

    // Stock adjustments

    suspend fun getStockOfAProduct(url: String): Flow<GetDataFromRemote<ProductStock?>> {
        return apiService.getStockOfAProduct(url = url)
    }

    suspend fun adjustStocksOfProductList(
        url: String,
        stockAdjustment: StockAdjustment
    ): Flow<GetDataFromRemote<StockAdjustment>> {
        return apiService.adjustStocksOfProductList(url = url, stockAdjustment = stockAdjustment)
    }

    suspend fun uniLicenseActivation(
        rioLabKey: String,
        url: String,
        licenseRequestBody: LicenseRequestBody
    ): Flow<GetDataFromRemote<LicenseResponse>> {
        return apiService.uniLicenseActivation(
            rioLabKey = rioLabKey,
            url = url,
            licenseRequestBody = licenseRequestBody
        )
    }

    suspend fun getProductForPriceAdjustment(url: String): Flow<GetDataFromRemote<ProductForPriceAdjustment>> {
        return apiService.getProductForPriceAdjustment(url = url)
    }

    suspend fun adjustProductPrice(
        url: String,
        priceAdjustment: PriceAdjustment
    ): Flow<GetDataFromRemote<PriceAdjustment>> {
        return apiService.adjustProductPrice(url = url, priceAdjustment = priceAdjustment)
    }


}