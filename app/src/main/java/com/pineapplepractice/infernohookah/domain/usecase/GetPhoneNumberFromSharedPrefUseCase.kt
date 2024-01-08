package com.pineapplepractice.infernohookah.domain.usecase

import com.pineapplepractice.infernohookah.domain.models.PhoneNumber

class GetPhoneNumberFromSharedPrefUseCase {
    fun execute() : PhoneNumber{
        return PhoneNumber("+375298656168")

    }
}