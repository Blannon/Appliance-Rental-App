package com.example.onceshare.data.model

import androidx.compose.ui.graphics.Brush

data class Appliance(
    val id: String = "",
    val name: String = "",
    val description: String = "",
    val condition: String = "",
    val rentalPrice: Double = 0.0,
    val ownerId: String = "",
    val availabilityPeriod: String = "",
    val imageUri: String? = null,
    //val color: Brush
)
