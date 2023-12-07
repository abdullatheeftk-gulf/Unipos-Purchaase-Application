package com.gulfappdeveloper.project2.data.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gulfappdeveloper.project2.domain.models.room.LocalPurchaseDetail
import kotlinx.coroutines.flow.Flow

@Dao
interface LocalPurchaseDetailDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllLocalPurchaseDetails(localPurchaseDetails:List<LocalPurchaseDetail>)

    @Query("SELECT * FROM LocalPurchaseDetail")
    fun getAllLocalPurchaseDetails():Flow<List<LocalPurchaseDetail>>

    @Query("DELETE FROM LocalPurchaseDetail")
    suspend fun deleteAllLocalPurchaseDetails()
}