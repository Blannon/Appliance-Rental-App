package com.example.onceshare.data.model

data class Booking(
    val id: String = "",
    val applianceId: String = "",
    val userId: String = "",
    val startDate: Long = 0L,
    val endDate: Long = 0L,
    val status: String = "pending"
)
