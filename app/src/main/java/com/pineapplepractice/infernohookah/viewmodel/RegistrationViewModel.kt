package com.pineapplepractice.infernohookah.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.pineapplepractice.infernohookah.domain.models.User
import com.pineapplepractice.infernohookah.domain.usecase.SaveUserUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class RegistrationViewModel(private val saveUserUseCase: SaveUserUseCase) : ViewModel() {

    private val _showToastEvent = MutableSharedFlow<String>()
    val showToastEvent: SharedFlow<String>
        get() = _showToastEvent.asSharedFlow()

    fun registerUser(user: User) {
        viewModelScope.launch {
            if (saveUserUseCase.execute(user))
                _showToastEvent.emit("Вы зарегистрированы")
        }
    }

    class Factory(
        val saveUserUseCase: SaveUserUseCase
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(RegistrationViewModel::class.java)) {
                return RegistrationViewModel(
                    saveUserUseCase = saveUserUseCase
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }

    }

}