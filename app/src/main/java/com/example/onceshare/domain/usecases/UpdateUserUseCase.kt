package com.example.onceshare.domain.usecases

import com.example.onceshare.data.model.User
import com.example.onceshare.data.model.UserRepository
import javax.inject.Inject

class UpdateUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(user: User): Result<Unit> {
        return userRepository.updateUser(user)
    }
}
