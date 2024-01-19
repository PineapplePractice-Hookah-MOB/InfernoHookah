package com.pineapplepractice.infernohookah.domain.repositoryinterface

import com.pineapplepractice.infernohookah.data.datamodels.BookingRequest
import com.pineapplepractice.infernohookah.data.datamodels.BookingResponse
import com.pineapplepractice.infernohookah.data.datamodels.TokenResponse
import com.pineapplepractice.infernohookah.data.datamodels.UserResponse
import com.pineapplepractice.infernohookah.domain.models.PhoneNumber
import com.pineapplepractice.infernohookah.domain.models.User
import kotlinx.coroutines.Deferred

interface MainRepository {

    suspend fun authUser(user: User): TokenResponse?

    fun savePhoneToSharedPref(phoneNumber: PhoneNumber): Boolean

    suspend fun getBookingByUserId(id: Int): BookingResponse?

    suspend fun saveBooking(request: BookingRequest) :Boolean

    suspend fun saveUser(user: User): Boolean

    suspend fun getAllUser(): List<UserResponse>?

    suspend fun getUserIdFromStorage(): String
    suspend fun getUserByLogin(login: String): User?
//    suspend fun getUserByLogin(login: String)

}