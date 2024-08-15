package com.example.onceshare.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onceshare.data.model.Appliance
import com.example.onceshare.data.model.Booking
import com.example.onceshare.domain.usecases.AddApplianceUseCase
import com.example.onceshare.domain.usecases.GetAppliancesUseCase
import com.example.onceshare.domain.usecases.GetBookingsByApplianceUseCase
import com.example.onceshare.data.repository.ApplianceRepository
import com.google.firebase.Firebase
import com.google.firebase.storage.storage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class ApplianceViewModel @Inject constructor(
    private val addApplianceUseCase: AddApplianceUseCase,
    private val getAppliancesUseCase: GetAppliancesUseCase,
    private val getBookingsByApplianceUseCase: GetBookingsByApplianceUseCase,
    private val applianceRepository: ApplianceRepository // Inject the repository
) : ViewModel() {

    val searchQuery = MutableStateFlow("")
    private val _appliances = MutableStateFlow<List<Appliance>>(emptyList())
    private val _selectedAppliance = MutableStateFlow<Appliance?>(null)
    private val _bookings = MutableStateFlow<List<Booking>>(emptyList())

    @OptIn(ExperimentalCoroutinesApi::class)
    val appliances: StateFlow<List<Appliance>> = searchQuery
        .flatMapLatest { query ->
            if (query.isBlank()) {
                _appliances
            } else {
                _appliances.map { list ->
                    list.filter { it.name.contains(query, ignoreCase = true) }
                }
            }
        }.stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun addAppliance(appliance: Appliance, onResult: (Boolean, String) -> Unit) {
        viewModelScope.launch {
            val result = addApplianceUseCase(appliance)
            if (result.isSuccess) {
                onResult(true, "Appliance added successfully")
                getAppliances()
            } else {
                onResult(false, result.exceptionOrNull()?.message ?: "Failed to add appliance")
            }
        }
    }

    fun getAppliances() {
        viewModelScope.launch {
            val result = getAppliancesUseCase()
            if (result.isSuccess) {
                _appliances.value = result.getOrNull() ?: emptyList()
            } else {
                _appliances.value = emptyList()
            }
        }
    }

    fun getApplianceById(applianceId: String): StateFlow<Appliance?> {
        viewModelScope.launch {
            _selectedAppliance.value = _appliances.value.find { it.id == applianceId }
        }
        return _selectedAppliance
    }

    fun getBookingsByAppliance(applianceId: String): StateFlow<List<Booking>> {
        viewModelScope.launch {
            val result = getBookingsByApplianceUseCase(applianceId)
            _bookings.value = result.getOrNull() ?: emptyList()
        }
        return _bookings
    }

    fun getImageUrl(imagePath: String): Flow<String> = flow {
        try {
            val storageRef = Firebase.storage.reference.child(imagePath)
            val downloadUrl = storageRef.downloadUrl.await()
            emit(downloadUrl.toString())
        } catch (e: Exception) {
            Log.e("ApplianceViewModel", "Error fetching image URL", e)
            emit("")
        }
    }

}
