package com.example.onceshare.domain.usecases

import com.example.onceshare.data.model.Booking
import com.example.onceshare.data.repository.BookingRepository
import javax.inject.Inject

class GetBookingsByApplianceUseCase @Inject constructor(
    private val bookingRepository: BookingRepository
) {
    suspend operator fun invoke(applianceId: String): Result<List<Booking>> {
        return bookingRepository.getBookingsByAppliance(applianceId)
    }
}
