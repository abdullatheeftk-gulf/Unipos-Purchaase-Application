package com.gulfappdeveloper.project2.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.gulfappdeveloper.project2.data.room.dao.LocalPurchaseDetailDao
import com.gulfappdeveloper.project2.data.room.dao.LocalPurchaseMasterDao
import com.gulfappdeveloper.project2.domain.models.room.DateConverter
import com.gulfappdeveloper.project2.domain.models.room.LocalPurchaseDetail
import com.gulfappdeveloper.project2.domain.models.room.LocalPurchaseMaster

@Database(entities = [LocalPurchaseMaster::class,LocalPurchaseDetail::class], version = 1)
@TypeConverters(DateConverter::class)
abstract class LocalDatabase :RoomDatabase(){
    abstract fun getLocalPurchaseDetailDao():LocalPurchaseDetailDao
    abstract fun getLocalPurchaseMasterDao():LocalPurchaseMasterDao
}