package com.example.onceshare.domain.usecases

import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) {
    suspend operator fun invoke() {
        firebaseAuth.signOut()
    }
}
