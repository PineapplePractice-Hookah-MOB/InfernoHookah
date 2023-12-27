package com.pineapplepractice.infernohookah.domain.repositoryinterface

import com.pineapplepractice.infernohookah.data.datamodels.BookingRequest
import com.pineapplepractice.infernohookah.data.datamodels.BookingResponse
import com.pineapplepractice.infernohookah.domain.models.PhoneNumber

interface MainRepository {

    fun savePhoneToSharedPref(phoneNumber: PhoneNumber): Boolean

    suspend fun GetBookingByUserId(id: Int): BookingResponse?

    suspend fun SaveBooking(request: BookingRequest) :Boolean

}