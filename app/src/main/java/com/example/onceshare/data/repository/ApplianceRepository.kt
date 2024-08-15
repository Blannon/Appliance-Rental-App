package com.example.onceshare.data.repository

import com.example.onceshare.data.model.Appliance
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ApplianceRepository @Inject constructor(
   private val firestore: FirebaseFirestore,
    private val storage: FirebaseStorage
) {
    private val applianceCollection = firestore.collection("appliances")

    suspend fun addAppliance(appliance: Appliance): Result<Unit> {
        return try {
            applianceCollection.add(appliance).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getAppliances(): Result<List<Appliance>> {
        return try {
            val snapshot = applianceCollection.get().await()
            val appliances = snapshot.toObjects(Appliance::class.java)
            Result.success(appliances)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getImageUrl(imagePath: String): Result<String> {
        return try {
            val storageRef = storage.reference.child(imagePath)
            val url = storageRef.downloadUrl.await()
            Result.success(url.toString())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
