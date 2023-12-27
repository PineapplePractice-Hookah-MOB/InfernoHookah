package com.pineapplepractice.infernohookah.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.pineapplepractice.infernohookah.domain.models.SavePhoneFromET
import com.pineapplepractice.infernohookah.domain.usecase.LoginByPhoneUseCase
import com.pineapplepractice.infernohookah.domain.usecase.SavePhoneToSharedPrefUseCase
import com.pineapplepractice.infernohookah.domain.usecase.ValidatePhoneNumberUseCase

class AuthViewModel(
    private val loginByPhoneUseCase: LoginByPhoneUseCase,
    private val savePhoneToSharedPrefUseCase: SavePhoneToSharedPrefUseCase,
    private val validatePhoneNumberUseCase: ValidatePhoneNumberUseCase
) : ViewModel() {

    fun savePhone(phone: String) {
        val params1 = SavePhoneFromET(phone)
        val (isSuccess, validatePhone) = validatePhoneNumberUseCase.execute(params1)
        if (isSuccess) {
            val resultData: Boolean = savePhoneToSharedPrefUseCase.execute(phone = validatePhone)
            Log.e("TAG", "class AuthViewModel fun savePhone - сохранение в шаред - $resultData")
        } else {
            Log.e("TAG", "class AuthViewModel fun savePhone - не сохранилось в шаред")
        }
    }

    fun loginByPhone() {

    }
}