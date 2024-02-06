package com.pineapplepractice.infernohookah.domain.usecase

import com.pineapplepractice.infernohookah.domain.repositoryinterface.MainRepository

class GetUserNameFromStorageUseCase(private val mainRepository: MainRepository) {
    suspend fun execute(): String {
        return mainRepository.getUserNameFromStorage()
    }
}