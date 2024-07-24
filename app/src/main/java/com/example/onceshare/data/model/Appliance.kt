package com.example.onceshare.data.model

data class Appliance(
    val id: String = "",
    val name: String = "",
    val description: String = "",
    val condition: String = "",
    val rentalPrice: Double = 0.0,
    val ownerId: String = "",
    val availabilityPeriod: String = ""
)
