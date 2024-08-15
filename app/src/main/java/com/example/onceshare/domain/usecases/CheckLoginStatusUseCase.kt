package com.example.onceshare.domain.usecases



import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class CheckLoginStatusUseCase @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) {
    operator fun invoke(): Boolean {
        return firebaseAuth.currentUser != null
    }
}
