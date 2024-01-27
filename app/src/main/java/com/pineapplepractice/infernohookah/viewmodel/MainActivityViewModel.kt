package com.pineapplepractice.infernohookah.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.pineapplepractice.infernohookah.domain.usecase.GetUserNameFromStorageUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class MainActivityViewModel(private val getUserNameFromStorageUseCase: GetUserNameFromStorageUseCase) : ViewModel() {
    private val _userName = MutableSharedFlow<String>()

    val userName: SharedFlow<String>
        get() = _userName.asSharedFlow()

/*    init {
        viewModelScope.launch {
            val name = getUserNameFromStorageUseCase.execute()
            println("MainActivityViewModel: имя пользователя: $name")
            _userName.emit(name)
        }
    }*/

    fun getUserName() {
        viewModelScope.launch {
            val name = getUserNameFromStorageUseCase.execute()
            println("MainActivityViewModel: getUserName: имя пользователя: $name")
            _userName.emit(name)
        }
    }

    class Factory(
        val getUserNameFromStorageUseCase: GetUserNameFromStorageUseCase
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)) {
                return MainActivityViewModel(
                    getUserNameFromStorageUseCase = getUserNameFromStorageUseCase
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }

    }
}