package com.pineapplepractice.infernohookah.domain.usecase

import com.pineapplepractice.infernohookah.data.datamodels.TokenResponse
import com.pineapplepractice.infernohookah.domain.models.User
import com.pineapplepractice.infernohookah.domain.repositoryinterface.MainRepository

class AuthUserUseCase(private val mainRepository: MainRepository) {
    suspend fun execute(user: User): Boolean {
        return (mainRepository.authUser(user) != null)
    }
}