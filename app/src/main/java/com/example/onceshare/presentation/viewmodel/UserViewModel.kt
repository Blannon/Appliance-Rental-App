package com.example.onceshare.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.onceshare.data.model.User
import com.example.onceshare.domain.usecases.GetUserByIdUseCase
import com.example.onceshare.domain.usecases.UpdateUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val getUserByIdUseCase: GetUserByIdUseCase,
    private val updateUserUseCase: UpdateUserUseCase
) : ViewModel() {

    fun getUserById(userId: String) = liveData {
        val result = getUserByIdUseCase(userId)
        emit(result.getOrNull())
    }

    fun updateUser(user: User, onResult: (Boolean, String) -> Unit) {
        viewModelScope.launch {
            val result = updateUserUseCase(user)
            if (result.isSuccess) {
                onResult(true, "Profile updated successfully")
            } else {
                onResult(false, result.exceptionOrNull()?.message ?: "Failed to update profile")
            }
        }
    }
}
