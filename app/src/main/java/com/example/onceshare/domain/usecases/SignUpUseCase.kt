package com.example.onceshare.domain.usecases

import com.example.onceshare.data.model.User
import com.example.onceshare.data.repository.AuthRepository
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String): Result<User> {
        return authRepository.signUp(email, password)
    }
}