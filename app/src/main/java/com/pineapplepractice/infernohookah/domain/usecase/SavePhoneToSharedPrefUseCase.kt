package com.pineapplepractice.infernohookah.domain.usecase

import com.pineapplepractice.infernohookah.domain.models.PhoneNumber
import com.pineapplepractice.infernohookah.domain.models.SavePhoneFromET
import com.pineapplepractice.infernohookah.domain.repositoryinterface.MainRepository

class SavePhoneToSharedPrefUseCase(private val mainRepository: MainRepository) {
    fun execute(phone: PhoneNumber): Boolean {
        return true
    }
}