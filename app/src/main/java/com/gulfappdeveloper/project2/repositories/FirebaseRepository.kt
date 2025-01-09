package com.gulfappdeveloper.project2.repositories

import com.gulfappdeveloper.project2.domain.firebase.FirebaseError
import com.gulfappdeveloper.project2.domain.firebase.FirebaseGeneralData
import com.gulfappdeveloper.project2.domain.firebase.FirebaseLicense
import com.gulfappdeveloper.project2.domain.services.FirebaseService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirebaseRepository @Inject constructor(
    private val firebaseService: FirebaseService
) {

    suspend fun insertErrorDataToFireStore(
        collectionName: String,
        documentName: String,
        error: FirebaseError
    ) {
        firebaseService.insertErrorDataToFireStore(
            collectionName = collectionName,
            documentName = documentName,
            error = error
        )
    }

    suspend fun insertGeneralData(
        collectionName: String,
        firebaseGeneralData: FirebaseGeneralData
    ) {
        firebaseService.insertGeneralData(
            collectionName = collectionName,
            firebaseGeneralData = firebaseGeneralData
        )
    }

    suspend fun getFirebaseLicense(onGetFirebaseLicense: (FirebaseLicense) -> Unit) {
        firebaseService.getFirebaseLicense(onGetFirebaseLicense)
    }
}