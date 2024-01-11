package com.pineapplepractice.infernohookah.di.modules

import android.content.Context
import com.pineapplepractice.infernohookah.domain.usecase.GetFirstBookingUseCase
import com.pineapplepractice.infernohookah.domain.usecase.GetUserByLoginUseCase
import com.pineapplepractice.infernohookah.domain.usecase.LoginByPhoneUseCase
import com.pineapplepractice.infernohookah.domain.usecase.SaveBookingUseCase
import com.pineapplepractice.infernohookah.domain.usecase.SavePhoneToSharedPrefUseCase
import com.pineapplepractice.infernohookah.domain.usecase.SaveUserUseCase
import com.pineapplepractice.infernohookah.domain.usecase.ValidatePhoneNumberUseCase
import com.pineapplepractice.infernohookah.viewmodel.AuthViewModel
import com.pineapplepractice.infernohookah.viewmodel.HomeViewModel
import com.pineapplepractice.infernohookah.viewmodel.RegistrationViewModel
import com.pineapplepractice.infernohookah.viewmodel.ReservationViewModel
import com.pineapplepractice.infernohookah.viewmodel.vmfactory.AuthViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class AppModule(val context: Context) {
    @Provides
    fun provideContext() = context

    @Provides
    fun provideAuthFragmentViewModelFactory(
        loginByPhoneUseCase: LoginByPhoneUseCase,
        savePhoneToSharedPrefUseCase: SavePhoneToSharedPrefUseCase,
        validatePhoneNumberUseCase: ValidatePhoneNumberUseCase
    ): AuthViewModelFactory {
        return AuthViewModelFactory(
            loginByPhoneUseCase = loginByPhoneUseCase,
            savePhoneToSharedPrefUseCase = savePhoneToSharedPrefUseCase,
            validatePhoneNumberUseCase = validatePhoneNumberUseCase
        )
    }

    //    @Singleton
    @Provides
    fun provideHomeViewModelFactory(
        getFirstBookingUseCase: GetFirstBookingUseCase
    ) = HomeViewModel.Factory(
        getFirstBookingUseCase = getFirstBookingUseCase

    )

    @Provides
    fun provideReservationViewModelFactory(
        saveBookingUseCase: SaveBookingUseCase
    ) = ReservationViewModel.Factory(
        saveBookingUseCase = saveBookingUseCase
    )

    @Provides
    fun provideRegistrationViewModelFactory(
        saveUserUseCase: SaveUserUseCase
    ) = RegistrationViewModel.Factory(
        saveUserUseCase = saveUserUseCase
    )

    @Provides
    fun provideAuthViewModelFactory(
        getUserByLoginUseCase: GetUserByLoginUseCase
    ) = AuthViewModel.Factory(
        getUserByLoginUseCase = getUserByLoginUseCase
    )
}