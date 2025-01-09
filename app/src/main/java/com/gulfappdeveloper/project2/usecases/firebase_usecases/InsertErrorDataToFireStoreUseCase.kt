package com.gulfappdeveloper.project2.usecases.firebase_usecases

import com.gulfappdeveloper.project2.domain.firebase.FirebaseError
import com.gulfappdeveloper.project2.repositories.FirebaseRepository

class InsertErrorDataToFireStoreUseCase(
    private val firebaseRepository: FirebaseRepository
) {

    suspend operator fun invoke(
        collectionName: String,
        documentName: String,
        errorData: FirebaseError
    ) {
        firebaseRepository.insertErrorDataToFireStore(
            collectionName = collectionName,
            documentName = documentName,
            error = errorData
        )
    }
}