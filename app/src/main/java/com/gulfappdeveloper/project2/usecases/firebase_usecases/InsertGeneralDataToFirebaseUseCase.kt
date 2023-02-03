package com.gulfappdeveloper.project2.usecases.firebase_usecases

import com.gulfappdeveloper.project2.domain.firebase.FirebaseGeneralData
import com.gulfappdeveloper.project2.repositories.FirebaseRepository

class InsertGeneralDataToFirebaseUseCase(
    private val firebaseRepository: FirebaseRepository
) {
    suspend operator fun invoke(collectionName: String, firebaseGeneralData: FirebaseGeneralData) {
        firebaseRepository.insertGeneralData(
            collectionName = collectionName,
            firebaseGeneralData = firebaseGeneralData
        )
    }
}