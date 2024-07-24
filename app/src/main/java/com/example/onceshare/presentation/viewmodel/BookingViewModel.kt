package com.example.onceshare.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.onceshare.data.model.Booking
import com.example.onceshare.domain.usecases.AddBookingUseCase
import com.example.onceshare.domain.usecases.GetBookingsByApplianceUseCase
import com.example.onceshare.domain.usecases.GetBookingsByUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookingViewModel @Inject constructor(
    private val addBookingUseCase: AddBookingUseCase,
    private val getBookingsByUserUseCase: GetBookingsByUserUseCase,
    private val getBookingsByApplianceUseCase: GetBookingsByApplianceUseCase
) : ViewModel() {

    fun addBooking(booking: Booking, onResult: (Boolean, String) -> Unit) {
        viewModelScope.launch {
            val result = addBookingUseCase(booking)
            if (result.isSuccess) {
                onResult(true, "Booking successful")
            } else {
                onResult(false, result.exceptionOrNull()?.message ?: "Failed to book appliance")
            }
        }
    }

    fun getBookingsByUser(userId: String) = liveData {
        val result = getBookingsByUserUseCase(userId)
        emit(result.getOrNull() ?: emptyList())
    }

    fun getBookingsByAppliance(applianceId: String) = liveData {
        val result = getBookingsByApplianceUseCase(applianceId)
        emit(result.getOrNull() ?: emptyList())
    }
}
