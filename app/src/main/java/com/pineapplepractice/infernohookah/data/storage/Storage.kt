package com.pineapplepractice.infernohookah.data.storage

import com.example.cleanarchitecturekiparokovalenko.data.storage.sharedprefs.SharedPrefStorage
import com.pineapplepractice.infernohookah.domain.models.PhoneNumber


//репозитории и стораджи должны быть без if
//Если хочется добавить if, то создаем новый UseCase
interface Storage {

    fun saveUserName(userName: String): Boolean

    fun getUserName(): String

    fun saveUserId(userId: String): Boolean

    fun getUserId(): String
    fun saveAccessToken(accessToken: String): Boolean

    fun getAccessToken(): String

    fun saveRefreshToken(refreshToken: String): Boolean

    fun getRefreshToken(): String

    fun savePhone(phoneNumber: PhoneNumber): Boolean

    fun getPhone(): PhoneNumber
}