package com.gulfappdeveloper.project2.di

import com.gulfappdeveloper.project2.data.comon_memmory.CommonMemory
import com.gulfappdeveloper.project2.repositories.DataStoreRepository
import com.gulfappdeveloper.project2.repositories.FirebaseRepository
import com.gulfappdeveloper.project2.repositories.LocalDataRepository
import com.gulfappdeveloper.project2.repositories.RemoteRepository
import com.gulfappdeveloper.project2.usecases.UseCase
import com.gulfappdeveloper.project2.usecases.data_store_use_cases.base_url_use_cases.ReadBaseUrlUseCase
import com.gulfappdeveloper.project2.usecases.data_store_use_cases.base_url_use_cases.SaveBaseUrlUseCase
import com.gulfappdeveloper.project2.usecases.data_store_use_cases.device_id_use_case.ReadDeviceIdUseCase
import com.gulfappdeveloper.project2.usecases.data_store_use_cases.device_id_use_case.SaveDeviceIdUseCase
import com.gulfappdeveloper.project2.usecases.data_store_use_cases.ip_address_usecases.ReadIpAddressUseCase
import com.gulfappdeveloper.project2.usecases.data_store_use_cases.ip_address_usecases.SaveIpAddressUseCase
import com.gulfappdeveloper.project2.usecases.data_store_use_cases.operation_counter_use_cases.ReadOperationCountUseCase
import com.gulfappdeveloper.project2.usecases.data_store_use_cases.operation_counter_use_cases.UpdateOperationCountUseCase
import com.gulfappdeveloper.project2.usecases.data_store_use_cases.serial_counter_usecases.ReadSerialNoCountUseCase
import com.gulfappdeveloper.project2.usecases.data_store_use_cases.serial_counter_usecases.UpdateSerialNoUseCase
import com.gulfappdeveloper.project2.usecases.data_store_use_cases.uni_license_save_use_cases.UniLicenseReadUseCase
import com.gulfappdeveloper.project2.usecases.data_store_use_cases.uni_license_save_use_cases.UniLicenseSaveUseCase
import com.gulfappdeveloper.project2.usecases.firebase_usecases.GetFirebaseLicenseUseCase
import com.gulfappdeveloper.project2.usecases.firebase_usecases.InsertErrorDataToFireStoreUseCase
import com.gulfappdeveloper.project2.usecases.firebase_usecases.InsertGeneralDataToFirebaseUseCase
import com.gulfappdeveloper.project2.usecases.local_database_use_case.purchase_detail_use_cases.DeleteAllLocalPurchaseDetailsUseCase
import com.gulfappdeveloper.project2.usecases.local_database_use_case.purchase_detail_use_cases.GetAllLocalPurchaseDetailsUseCase
import com.gulfappdeveloper.project2.usecases.local_database_use_case.purchase_detail_use_cases.InsertLocalPurchaseDetailUseCase
import com.gulfappdeveloper.project2.usecases.local_database_use_case.purchase_master_use_cases.DeleteLocalPurchaseMasterUseCase
import com.gulfappdeveloper.project2.usecases.local_database_use_case.purchase_master_use_cases.GetLocalPurchaseMasterUseCase
import com.gulfappdeveloper.project2.usecases.local_database_use_case.purchase_master_use_cases.InsertLocalPurchaseMasterUseCase
import com.gulfappdeveloper.project2.usecases.remote_usecase.get.add_product.GetAllTaxCategoriesUseCase
import com.gulfappdeveloper.project2.usecases.remote_usecase.get.add_product.GetAllUnitsUseCase
import com.gulfappdeveloper.project2.usecases.remote_usecase.get.add_product.GetProductGroupsUseCase
import com.gulfappdeveloper.project2.usecases.remote_usecase.get.add_product.SearchProductGroupsUseCase
import com.gulfappdeveloper.project2.usecases.remote_usecase.get.client.GetClientDetailsUseCase
import com.gulfappdeveloper.project2.usecases.remote_usecase.get.client.SearchClientDetailsUseCase
import com.gulfappdeveloper.project2.usecases.remote_usecase.get.ip.GetIp4AddressUseCase
import com.gulfappdeveloper.project2.usecases.remote_usecase.get.license.UniLicenseActivationUseCase
import com.gulfappdeveloper.project2.usecases.remote_usecase.get.login.LoginUseCase
import com.gulfappdeveloper.project2.usecases.remote_usecase.get.price_adjustment.GetProductForPriceAdjustmentUseCase
import com.gulfappdeveloper.project2.usecases.remote_usecase.get.product.GetProductDetailByBarcodeUseCase
import com.gulfappdeveloper.project2.usecases.remote_usecase.get.product.GetProductDetailsByNameUseCase
import com.gulfappdeveloper.project2.usecases.remote_usecase.get.welcome.GetWelcomeMessageUseCase
import com.gulfappdeveloper.project2.usecases.remote_usecase.post.add_client.AddClientUseCase
import com.gulfappdeveloper.project2.usecases.remote_usecase.post.add_product.AddProductUseCase
import com.gulfappdeveloper.project2.usecases.remote_usecase.post.price_adjustment.PriceAdjustmentUseCase
import com.gulfappdeveloper.project2.usecases.remote_usecase.post.purchase.PurchaseUseCase
import com.gulfappdeveloper.project2.usecases.remote_usecase.stock_adjustment.AdjustStocksOfProductListUseCase
import com.gulfappdeveloper.project2.usecases.remote_usecase.stock_adjustment.GetStockOfAProductUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideCommonMemory(): CommonMemory {
        return CommonMemory
    }


    @Provides
    @Singleton
    fun provideUseCase(
        remoteRepository: RemoteRepository,
        dataStoreRepository: DataStoreRepository,
        firebaseRepository: FirebaseRepository,
        localDataRepository: LocalDataRepository
    ): UseCase {
        return UseCase(

            getWelcomeMessageUseCase = GetWelcomeMessageUseCase(remoteRepository = remoteRepository),
            loginUseCase = LoginUseCase(remoteRepository = remoteRepository),
            uniLicenseActivationUseCase = UniLicenseActivationUseCase(remoteRepository = remoteRepository),
            getIp4AddressUseCase = GetIp4AddressUseCase(remoteRepository = remoteRepository),

            getClientDetailsUseCase = GetClientDetailsUseCase(remoteRepository = remoteRepository),
            searchClientDetailsUseCase = SearchClientDetailsUseCase(repository = remoteRepository),

            getProductDetailsUseCase = GetProductDetailsByNameUseCase(remoteRepository = remoteRepository),
            getProductDetailByBarcodeUseCase = GetProductDetailByBarcodeUseCase(remoteRepository = remoteRepository),

            // Add product
            getProductGroupsUseCase = GetProductGroupsUseCase(remoteRepository = remoteRepository),
            searchProductGroupsUseCase = SearchProductGroupsUseCase(remoteRepository = remoteRepository),
            getAllTaxCategoriesUseCase = GetAllTaxCategoriesUseCase(remoteRepository = remoteRepository),
            getAllUnitsUseCase = GetAllUnitsUseCase(remoteRepository = remoteRepository),


            // post
            addClientUseCase = AddClientUseCase(remoteRepository = remoteRepository),
            addProductUseCase = AddProductUseCase(remoteRepository = remoteRepository),

            // purchase
            purchaseUseCase = PurchaseUseCase(remoteRepository = remoteRepository),

            // write on datastore
            updateOperationCountUseCase = UpdateOperationCountUseCase(dataStoreRepository = dataStoreRepository),
            saveBaseUrlUseCase = SaveBaseUrlUseCase(dataStoreRepository = dataStoreRepository),
            updateSerialNoUseCase = UpdateSerialNoUseCase(dataStoreRepository = dataStoreRepository),
            saveIpAddressUseCase = SaveIpAddressUseCase(dataStoreRepository = dataStoreRepository),
            uniLicenseSaveUseCase = UniLicenseSaveUseCase(dataStoreRepository = dataStoreRepository),
            saveDeviceIdUseCase = SaveDeviceIdUseCase(dataStoreRepository = dataStoreRepository),

            // read from datastore
            readOperationCountUseCase = ReadOperationCountUseCase(dataStoreRepository = dataStoreRepository),
            readBaseUrlUseCase = ReadBaseUrlUseCase(dataStoreRepository = dataStoreRepository),
            readSerialNoCountUseCase = ReadSerialNoCountUseCase(dataStoreRepository = dataStoreRepository),
            readIpAddressUseCase = ReadIpAddressUseCase(dataStoreRepository = dataStoreRepository),
            uniLicenseReadUseCase = UniLicenseReadUseCase(dataStoreRepository = dataStoreRepository),
            readDeviceIdUseCase = ReadDeviceIdUseCase(dataStoreRepository = dataStoreRepository),

            // Stock adjustment
            getStockOfAProductUseCase = GetStockOfAProductUseCase(remoteRepository = remoteRepository),
            adjustStocksOfProductListUseCase = AdjustStocksOfProductListUseCase(remoteRepository = remoteRepository),

            // Price adjustment
            priceAdjustmentUseCase = PriceAdjustmentUseCase(remoteRepository = remoteRepository),
            getProductForPriceAdjustment = GetProductForPriceAdjustmentUseCase(remoteRepository = remoteRepository),

            insertErrorDataToFireStoreUseCase = InsertErrorDataToFireStoreUseCase(firebaseRepository = firebaseRepository),
            insertGeneralDataToFirebaseUseCase = InsertGeneralDataToFirebaseUseCase(
                firebaseRepository = firebaseRepository
            ),
            getFirebaseLicenseUseCase = GetFirebaseLicenseUseCase(firebaseRepository = firebaseRepository),


            // local database
            insertLocalPurchaseDetailUseCase = InsertLocalPurchaseDetailUseCase(localDataRepository = localDataRepository),
            getAllLocalPurchaseDetailsUseCase = GetAllLocalPurchaseDetailsUseCase(localDataRepository = localDataRepository),
            deleteAllLocalPurchaseDetailsUseCase = DeleteAllLocalPurchaseDetailsUseCase(localDataRepository = localDataRepository),

            insertLocalPurchaseMasterUseCase = InsertLocalPurchaseMasterUseCase(localDataRepository = localDataRepository),
            getLocalPurchaseMasterUseCase = GetLocalPurchaseMasterUseCase(localDataRepository = localDataRepository),
            deleteLocalPurchaseMasterUseCase = DeleteLocalPurchaseMasterUseCase(localDataRepository)

        )
    }
}