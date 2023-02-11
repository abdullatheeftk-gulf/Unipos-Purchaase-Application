package com.gulfappdeveloper.project2.domain.services

import com.gulfappdeveloper.project2.domain.firebase.FirebaseError
import com.gulfappdeveloper.project2.domain.firebase.FirebaseGeneralData
import com.gulfappdeveloper.project2.domain.firebase.FirebaseLicense


interface FirebaseService {
    suspend fun insertErrorDataToFireStore(
        collectionName: String,
        documentName: String,
        error: FirebaseError
    )

    suspend fun insertGeneralData(
        collectionName: String,
        firebaseGeneralData: FirebaseGeneralData
    )

    suspend fun getFirebaseLicense(onGetFirebaseLicense:(FirebaseLicense)->Unit)
}