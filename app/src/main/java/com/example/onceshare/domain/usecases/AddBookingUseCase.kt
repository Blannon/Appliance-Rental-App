package com.example.onceshare.domain.usecases

import com.example.onceshare.data.model.Booking
import com.example.onceshare.data.repository.BookingRepository
import javax.inject.Inject

class AddBookingUseCase @Inject constructor(
    private val bookingRepository: BookingRepository
) {
    suspend operator fun invoke(booking: Booking): Result<Unit> {
        return bookingRepository.addBooking(booking)
    }
}
