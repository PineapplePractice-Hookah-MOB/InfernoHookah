package com.pineapplepractice.infernohookah.data

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

class PreferenceProvider(context: Context) {

    private val appContext = context.applicationContext
    private val preference: SharedPreferences =
        appContext.getSharedPreferences("settings", Context.MODE_PRIVATE)

    init {
    }

    fun saveToken(token: String) {
        preference.edit { putString(KEY_ACCESS_TOKEN, token) }
    }

    fun getToken(): String {
        return preference.getString(KEY_ACCESS_TOKEN, "") ?: ""
    }

    //Ключи для наших настроек, по ним мы их будем получать
    companion object {
        private const val KEY_ACCESS_TOKEN = "access_token"
    }
}