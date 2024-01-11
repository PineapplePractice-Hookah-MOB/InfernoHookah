package com.pineapplepractice.infernohookah.domain.usecase

import com.pineapplepractice.infernohookah.domain.models.User
import com.pineapplepractice.infernohookah.domain.repositoryinterface.MainRepository

class SaveUserUseCase(private val mainRepository: MainRepository) {
    suspend fun execute(user: User): Boolean  {
        return mainRepository.saveUser(user)
    }
}