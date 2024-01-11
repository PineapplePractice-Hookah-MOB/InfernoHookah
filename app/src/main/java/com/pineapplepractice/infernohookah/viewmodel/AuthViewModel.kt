package com.pineapplepractice.infernohookah.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.pineapplepractice.infernohookah.domain.models.SavePhoneFromET
import com.pineapplepractice.infernohookah.domain.models.User
import com.pineapplepractice.infernohookah.domain.usecase.GetUserByLoginUseCase
import com.pineapplepractice.infernohookah.domain.usecase.LoginByPhoneUseCase
import com.pineapplepractice.infernohookah.domain.usecase.SavePhoneToSharedPrefUseCase
import com.pineapplepractice.infernohookah.domain.usecase.SaveUserUseCase
import com.pineapplepractice.infernohookah.domain.usecase.ValidatePhoneNumberUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class AuthViewModel(
    private val getUserByLoginUseCase: GetUserByLoginUseCase
) : ViewModel() {

    private val _showToastEvent = MutableSharedFlow<String>()
    val showToastEvent: SharedFlow<String>
        get() = _showToastEvent.asSharedFlow()

    private val _user = MutableSharedFlow<User>()
    val user: SharedFlow<User>
        get() = _user.asSharedFlow()

    /*    fun savePhone(phone: String) {
            val params1 = SavePhoneFromET(phone)
            val (isSuccess, validatePhone) = validatePhoneNumberUseCase.execute(params1)
            if (isSuccess) {
                val resultData: Boolean = savePhoneToSharedPrefUseCase.execute(phone = validatePhone)
                Log.e("TAG", "class AuthViewModel fun savePhone - сохранение в шаред - $resultData")
            } else {
                Log.e("TAG", "class AuthViewModel fun savePhone - не сохранилось в шаред")
            }
        }*/

    /*    fun loginByPhone() {

        }*/

    fun getUserByLogin(login: String) {
        viewModelScope.launch {
            getUserByLoginUseCase.execute(login)?.let {
                _user.emit(it)
            }
                ?: run {
                    _showToastEvent.emit("Пользователь с логином $login не найден. Зарегистрируйтесь, пожалуйста.")
                }
        }
    }

    class Factory(
        val getUserByLoginUseCase: GetUserByLoginUseCase
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
                return AuthViewModel(
                    getUserByLoginUseCase = getUserByLoginUseCase
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }

    }
}