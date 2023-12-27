package com.pineapplepractice.infernohookah.domain.usecase

import com.pineapplepractice.infernohookah.domain.models.PhoneNumber
import com.pineapplepractice.infernohookah.domain.models.ZvonokTellCodeResponse
import com.pineapplepractice.infernohookah.domain.repositoryinterface.MainRepository

class LoginByPhoneUseCase (private val mainRepository: MainRepository)
{
    fun execute(phoneNumber: PhoneNumber): ZvonokTellCodeResponse {
        return ZvonokTellCodeResponse("error")
    }
}