package com.example.onceshare.domain.usecases

import com.example.onceshare.data.model.Booking
import com.example.onceshare.data.repository.BookingRepository
import javax.inject.Inject

class GetBookingsByUserUseCase @Inject constructor(
    private val bookingRepository: BookingRepository
) {
    suspend operator fun invoke(userId: String): Result<List<Booking>> {
        return bookingRepository.getBookingsByUser(userId)
    }
}
