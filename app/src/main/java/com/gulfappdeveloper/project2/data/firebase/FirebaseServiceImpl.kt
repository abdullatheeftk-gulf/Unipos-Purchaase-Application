package com.gulfappdeveloper.project2.data.firebase

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.gulfappdeveloper.project2.BuildConfig
import com.gulfappdeveloper.project2.domain.firebase.FirebaseError
import com.gulfappdeveloper.project2.domain.firebase.FirebaseGeneralData
import com.gulfappdeveloper.project2.domain.firebase.FirebaseLicense
import com.gulfappdeveloper.project2.domain.services.FirebaseService
import java.util.*

private const val TAG = "FirebaseServiceImpl"
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
        Log.d(TAG, "insertGeneralData: $firebaseGeneralData")
        try {

                fdb.collection(collectionName).document(firebaseGeneralData.device + ",${Date()}")
                    .set(firebaseGeneralData).addOnSuccessListener {
                        Log.i(TAG, "insertGeneralData: success", )
                    }
                    .addOnFailureListener {
                        Log.e(TAG, "insertGeneralData: $it", )
                    }


        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override suspend fun getFirebaseLicense(onGetFirebaseLicense: (FirebaseLicense) -> Unit) {

        try {
            fdb.collection("appLicense").document("unipos").get()
                .addOnSuccessListener {
                    val firebaseLicense =
                        it.toObject(FirebaseLicense::class.java) ?: FirebaseLicense()
                    onGetFirebaseLicense(firebaseLicense)
                }
                .addOnFailureListener {
                    onGetFirebaseLicense(FirebaseLicense())
                }
                .addOnCanceledListener { }
        } catch (e: Exception) {
            e.message
        }

    }
}