package com.pineapplepractice.infernohookah.domain.usecase

import com.pineapplepractice.infernohookah.domain.models.PhoneNumber
import com.pineapplepractice.infernohookah.domain.models.SavePhoneFromET

class ValidatePhoneNumberUseCase {
    fun execute(phone: SavePhoneFromET): Pair<Boolean, PhoneNumber> {
        return Pair(true, PhoneNumber("+375298656168"))
    }
}