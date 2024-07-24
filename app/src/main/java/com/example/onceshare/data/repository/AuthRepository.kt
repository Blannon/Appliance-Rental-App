package com.example.onceshare.data.repository

import com.example.onceshare.data.model.User
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


class AuthRepository @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) {
    suspend fun login(email: String, password: String): Result<User> {
        return try {
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            Result.success(User(result.user?.uid ?: "", result.user?.email ?: ""))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun signUp(email: String, password: String): Result<User> {
        return try {
            val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            Result.success(User(result.user?.uid ?: "", result.user?.email ?: ""))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
