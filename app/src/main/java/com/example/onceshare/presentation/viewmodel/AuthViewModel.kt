package com.example.onceshare.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onceshare.domain.usecases.LoginUseCase
import com.example.onceshare.domain.usecases.SignUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val signUpUseCase: SignUpUseCase
) : ViewModel() {

    fun login(email: String, password: String, onResult: (Boolean, String) -> Unit) {
        viewModelScope.launch {
            val result = loginUseCase(email, password)
            if (result.isSuccess) {
                onResult(true, "Login Successful")
            } else {
                onResult(false, result.exceptionOrNull()?.message ?: "Login Failed")
            }
        }
    }

    fun signUp(email: String, password: String, onResult: (Boolean, String) -> Unit) {
        viewModelScope.launch {
            val result = signUpUseCase(email, password)
            if (result.isSuccess) {
                onResult(true, "Sign Up Successful")
            } else {
                onResult(false, result.exceptionOrNull()?.message ?: "Sign Up Failed")
            }
        }
    }
}
