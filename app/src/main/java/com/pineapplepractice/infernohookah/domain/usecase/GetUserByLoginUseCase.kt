package com.pineapplepractice.infernohookah.domain.usecase

import com.pineapplepractice.infernohookah.data.datamodels.BookingResponse
import com.pineapplepractice.infernohookah.data.datamodels.UserRequest
import com.pineapplepractice.infernohookah.data.datamodels.UserResponse
import com.pineapplepractice.infernohookah.domain.models.User
import com.pineapplepractice.infernohookah.domain.repositoryinterface.MainRepository

class GetUserByLoginUseCase(private val mainRepository: MainRepository) {
    suspend fun execute(login: String): User? {
        return mainRepository.getUserByLogin(login)
    }
}