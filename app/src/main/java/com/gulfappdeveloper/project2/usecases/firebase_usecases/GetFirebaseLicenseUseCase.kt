package com.gulfappdeveloper.project2.usecases.firebase_usecases

import com.gulfappdeveloper.project2.domain.firebase.FirebaseLicense
import com.gulfappdeveloper.project2.repositories.FirebaseRepository

class GetFirebaseLicenseUseCase(
    private val firebaseRepository: FirebaseRepository
) {
    suspend operator fun invoke(onGetFirebaseLicense: (FirebaseLicense) -> Unit){
        firebaseRepository.getFirebaseLicense(onGetFirebaseLicense)
    }
}