package com.gulfappdeveloper.project2.data.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gulfappdeveloper.project2.domain.models.room.LocalPurchaseMaster
import kotlinx.coroutines.flow.Flow

@Dao
interface LocalPurchaseMasterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLocalPurchaseMaster(localPurchaseMaster: LocalPurchaseMaster)

    @Query("SELECT * FROM LocalPurchaseMaster WHERE userId = :userId")
    fun getLocalPurchaseMaster(userId:Int):Flow<LocalPurchaseMaster>

    @Query("DELETE FROM LocalPurchaseMaster")
    suspend fun deleteLocalPurchaseMaster()
}