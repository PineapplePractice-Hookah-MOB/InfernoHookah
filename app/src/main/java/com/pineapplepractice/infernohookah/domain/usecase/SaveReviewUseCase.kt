package com.pineapplepractice.infernohookah.domain.usecase

import com.pineapplepractice.infernohookah.data.datamodels.BookingRequest
import com.pineapplepractice.infernohookah.domain.repositoryinterface.MainRepository

class SaveReviewUseCase(private val mainRepository: MainRepository) {
    suspend fun execute(review: String): Boolean  {
        return mainRepository.saveReview(review)
    }
}