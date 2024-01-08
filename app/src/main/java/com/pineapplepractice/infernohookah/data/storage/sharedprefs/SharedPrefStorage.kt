package com.example.cleanarchitecturekiparokovalenko.data.storage.sharedprefs

import android.content.Context
import com.pineapplepractice.infernohookah.data.storage.Storage
import com.pineapplepractice.infernohookah.domain.models.PhoneNumber

//репозитории и стораджи должны быть без if
//Если хочется добавить if, то создаем новый UseCase
class SharedPrefStorage(private val context: Context) : Storage {
    private val sharedPreferences =
        context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)

    override fun savePhone(phoneNumber: PhoneNumber): Boolean {
        return true
    }

    override fun getPhone(): PhoneNumber {
        return PhoneNumber("+375298656168")
    }

    companion object {
        private const val SHARED_PREFS_NAME = "shared_prefs_name"
    }
}