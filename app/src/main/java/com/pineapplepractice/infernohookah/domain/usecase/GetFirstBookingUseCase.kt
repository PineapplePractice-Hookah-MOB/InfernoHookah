package com.pineapplepractice.infernohookah.domain.usecase

import com.pineapplepractice.infernohookah.data.datamodels.BookingResponse
import com.pineapplepractice.infernohookah.domain.repositoryinterface.MainRepository

class GetFirstBookingUseCase(private val mainRepository: MainRepository) {
    suspend fun execute(): BookingResponse?   {
        return mainRepository.GetBookingByUserId(444)
    }
}