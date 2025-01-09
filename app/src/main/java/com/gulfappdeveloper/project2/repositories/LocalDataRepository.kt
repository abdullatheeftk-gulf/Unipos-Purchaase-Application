package com.gulfappdeveloper.project2.repositories

import com.gulfappdeveloper.project2.data.room.dao.LocalPurchaseDetailDao
import com.gulfappdeveloper.project2.data.room.dao.LocalPurchaseMasterDao
import com.gulfappdeveloper.project2.domain.models.room.LocalPurchaseDetail
import com.gulfappdeveloper.project2.domain.models.room.LocalPurchaseMaster
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataRepository @Inject constructor(
    private val localPurchaseDetailDao: LocalPurchaseDetailDao,
    private val localPurchaseMasterDao: LocalPurchaseMasterDao
) {

    suspend fun insertAllLocalPurchaseDetails(localPurchaseDetails: List<LocalPurchaseDetail>) {
        localPurchaseDetailDao.insertAllLocalPurchaseDetails(localPurchaseDetails = localPurchaseDetails)
    }

    fun getAllLocalPurchaseDetails(): Flow<List<LocalPurchaseDetail>> {
        return localPurchaseDetailDao.getAllLocalPurchaseDetails()
    }

    suspend fun deleteAllLocalPurchaseDetails() =
        localPurchaseDetailDao.deleteAllLocalPurchaseDetails()


    suspend fun insertLocalPurchaseMaster(localPurchaseMaster: LocalPurchaseMaster) =
        localPurchaseMasterDao.insertLocalPurchaseMaster(
            localPurchaseMaster = localPurchaseMaster
        )

    fun getLocalPurchaseMaster(userId: Int): Flow<LocalPurchaseMaster> =
        localPurchaseMasterDao.getLocalPurchaseMaster(
            userId = userId
        )

    suspend fun deleteLocalPurchaseMaster() =
        localPurchaseMasterDao.deleteLocalPurchaseMaster()


}