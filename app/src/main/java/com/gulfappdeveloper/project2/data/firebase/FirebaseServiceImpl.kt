package com.gulfappdeveloper.project2.data.firebase

import com.google.firebase.firestore.FirebaseFirestore
import com.gulfappdeveloper.project2.BuildConfig
import com.gulfappdeveloper.project2.domain.firebase.FirebaseError
import com.gulfappdeveloper.project2.domain.firebase.FirebaseGeneralData
import com.gulfappdeveloper.project2.domain.services.FirebaseService
import java.util.*

class FirebaseServiceImpl(
    private val fdb: FirebaseFirestore
) : FirebaseService {
    override suspend fun insertErrorDataToFireStore(
        collectionName: String,
        documentName: String,
        error: FirebaseError
    ) {
        try {
            if (!BuildConfig.DEBUG) {
                fdb.collection(collectionName)
                    .document(documentName)
                    .set(error)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    override suspend fun insertGeneralData(
        collectionName: String,
        firebaseGeneralData: FirebaseGeneralData
    ) {
        try {
            if (!BuildConfig.DEBUG) {
                fdb.collection(collectionName).document(firebaseGeneralData.device + ",${Date()}")
                    .set(firebaseGeneralData)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}