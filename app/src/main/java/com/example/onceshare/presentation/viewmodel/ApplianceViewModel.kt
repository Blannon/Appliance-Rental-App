package com.example.onceshare.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.onceshare.data.model.Appliance
import com.example.onceshare.domain.usecases.AddApplianceUseCase
import com.example.onceshare.domain.usecases.GetAppliancesUseCase
import com.example.onceshare.domain.usecases.GetBookingsByApplianceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ApplianceViewModel @Inject constructor(
    private val addApplianceUseCase: AddApplianceUseCase,
    private val getAppliancesUseCase: GetAppliancesUseCase,
    private val getBookingsByApplianceUseCase: GetBookingsByApplianceUseCase
) : ViewModel() {

    val searchQuery = MutableStateFlow("")
    private val _appliances = MutableStateFlow<List<Appliance>>(emptyList())
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

    fun getApplianceById(applianceId: String): Appliance? {
        // Fetch appliance by ID logic (assuming the appliances list is already fetched and stored in _appliances)
        return _appliances.value.find { it.id == applianceId }
    }

    fun getBookingsByAppliance(applianceId: String) = liveData {
        val result = getBookingsByApplianceUseCase(applianceId)
        emit(result.getOrNull() ?: emptyList())
    }
}
