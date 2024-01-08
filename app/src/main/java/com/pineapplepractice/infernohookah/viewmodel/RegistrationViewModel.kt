package com.pineapplepractice.infernohookah.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class RegistrationViewModel: ViewModel() {

    class Factory(
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(RegistrationViewModel::class.java)) {
                return RegistrationViewModel(
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }

    }

}