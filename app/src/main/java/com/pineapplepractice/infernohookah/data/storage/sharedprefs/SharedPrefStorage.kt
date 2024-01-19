package com.example.cleanarchitecturekiparokovalenko.data.storage.sharedprefs

import android.content.Context
import com.pineapplepractice.infernohookah.data.storage.Storage
import com.pineapplepractice.infernohookah.domain.models.PhoneNumber

//репозитории и стораджи должны быть без if
//Если хочется добавить if, то создаем новый UseCase
class SharedPrefStorage(private val context: Context) : Storage {
    private val sharedPreferences =
        context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)

    override fun saveUserId(userId: String): Boolean {
        sharedPreferences.edit().putString(KEY_USER_ID, userId).apply()
        return true
    }

    override fun getUserId(): String {
        return sharedPreferences.getString(KEY_USER_ID, "") ?: ""
    }

    override fun saveAccessToken(accessToken: String): Boolean {
        sharedPreferences.edit().putString(KEY_ACCESS_TOKEN, accessToken).apply()
        return true
    }

    override fun getAccessToken(): String {
        return sharedPreferences.getString(KEY_ACCESS_TOKEN, "") ?: ""
    }

    override fun saveRefreshToken(refreshToken: String): Boolean {
        sharedPreferences.edit().putString(KEY_REFRESH_TOKEN, refreshToken).apply()
        return true
    }

    override fun getRefreshToken(): String {
        return sharedPreferences.getString(KEY_REFRESH_TOKEN, "") ?: ""
    }

    override fun savePhone(phoneNumber: PhoneNumber): Boolean {
        return true
    }

    override fun getPhone(): PhoneNumber {
        return PhoneNumber("+375298656168")
    }

    companion object {
        private const val SHARED_PREFS_NAME = "shared_prefs_name"
        private const val KEY_ACCESS_TOKEN = "access_token"
        private const val KEY_REFRESH_TOKEN = "refresh_token"
        private const val KEY_USER_ID = "user_id"
    }
}