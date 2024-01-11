package com.pineapplepractice.infernohookah.domain.usecase

import com.pineapplepractice.infernohookah.data.datamodels.BookingRequest
import com.pineapplepractice.infernohookah.domain.repositoryinterface.MainRepository

class SaveBookingUseCase(private val mainRepository: MainRepository) {
    suspend fun execute(bookingRequest: BookingRequest): Boolean  {
        return mainRepository.saveBooking(bookingRequest)
    }
}