package com.example.onceshare.data.repository

import com.example.onceshare.data.model.Booking
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class BookingRepository @Inject constructor(
    private val firestore: FirebaseFirestore
) {
    private val bookingCollection = firestore.collection("bookings")

    suspend fun addBooking(booking: Booking): Result<Unit> {
        return try {
            bookingCollection.add(booking).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getBookingsByUser(userId: String): Result<List<Booking>> {
        return try {
            val snapshot = bookingCollection.whereEqualTo("userId", userId).get().await()
            val bookings = snapshot.toObjects(Booking::class.java)
            Result.success(bookings)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getBookingsByAppliance(applianceId: String): Result<List<Booking>> {
        return try {
            val snapshot = bookingCollection.whereEqualTo("applianceId", applianceId).get().await()
            val bookings = snapshot.toObjects(Booking::class.java)
            Result.success(bookings)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
