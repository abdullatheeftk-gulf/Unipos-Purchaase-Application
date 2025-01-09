package com.gulfappdeveloper.project2.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.gulfappdeveloper.project2.data.data_store.DataStoreServiceImpl
import com.gulfappdeveloper.project2.data.firebase.FirebaseServiceImpl
import com.gulfappdeveloper.project2.data.room.LocalDatabase
import com.gulfappdeveloper.project2.domain.services.DataStoreService
import com.gulfappdeveloper.project2.domain.services.FirebaseService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideDataSoreService(@ApplicationContext context: Context):DataStoreService{
        return DataStoreServiceImpl(context = context)
    }

    @Provides
    @Singleton
    fun provideFirebaseFireStoreDb() = FirebaseFirestore.getInstance()

    @Provides
    @Singleton
    fun provideFirebaseService(fdb: FirebaseFirestore): FirebaseService {
        return FirebaseServiceImpl(fdb)
    }

    @Provides
    @Singleton
    fun provideRoomDatabase(@ApplicationContext context: Context)= Room.databaseBuilder(
        context = context,
        LocalDatabase::class.java,
        "inventory"
    ).build()

    @Provides
    @Singleton
    fun provideLocalPurchaseDetailDao(db:LocalDatabase) = db.getLocalPurchaseDetailDao()

    @Provides
    @Singleton
    fun provideLocalPurchaseMasterDao(db:LocalDatabase) = db.getLocalPurchaseMasterDao()
}