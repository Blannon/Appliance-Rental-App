package com.example.onceshare.domain.usecases

import com.example.onceshare.data.model.User
import com.example.onceshare.data.model.UserRepository
import javax.inject.Inject

class GetUserByIdUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(userId: String): Result<User?> {
        return userRepository.getUserById(userId)
    }
}
