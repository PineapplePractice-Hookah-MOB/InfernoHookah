package com.pineapplepractice.infernohookah.viewmodel.vmfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pineapplepractice.infernohookah.domain.usecase.LoginByPhoneUseCase
import com.pineapplepractice.infernohookah.domain.usecase.SavePhoneToSharedPrefUseCase
import com.pineapplepractice.infernohookah.domain.usecase.ValidatePhoneNumberUseCase
import com.pineapplepractice.infernohookah.viewmodel.AuthViewModel

class AuthViewModelFactory(
    val loginByPhoneUseCase: LoginByPhoneUseCase,
    val savePhoneToSharedPrefUseCase: SavePhoneToSharedPrefUseCase,
    val validatePhoneNumberUseCase: ValidatePhoneNumberUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AuthViewModel(
            loginByPhoneUseCase = loginByPhoneUseCase,
            savePhoneToSharedPrefUseCase = savePhoneToSharedPrefUseCase,
            validatePhoneNumberUseCase = validatePhoneNumberUseCase
        ) as T
    }

}