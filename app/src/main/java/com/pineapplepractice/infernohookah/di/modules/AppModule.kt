package com.pineapplepractice.infernohookah.di.modules

import android.content.Context
import com.pineapplepractice.infernohookah.domain.usecase.AuthUserUseCase
import com.pineapplepractice.infernohookah.domain.usecase.GetFirstBookingUseCase
import com.pineapplepractice.infernohookah.domain.usecase.GetMenuFromApiUseCase
import com.pineapplepractice.infernohookah.domain.usecase.GetUserByLoginUseCase
import com.pineapplepractice.infernohookah.domain.usecase.GetUserIdFromStorageUseCase
import com.pineapplepractice.infernohookah.domain.usecase.GetUserNameFromStorageUseCase
import com.pineapplepractice.infernohookah.domain.usecase.LoginByPhoneUseCase
import com.pineapplepractice.infernohookah.domain.usecase.SaveBookingUseCase
import com.pineapplepractice.infernohookah.domain.usecase.SavePhoneToSharedPrefUseCase
import com.pineapplepractice.infernohookah.domain.usecase.SaveReviewUseCase
import com.pineapplepractice.infernohookah.domain.usecase.SaveUserUseCase
import com.pineapplepractice.infernohookah.domain.usecase.ValidatePhoneNumberUseCase
import com.pineapplepractice.infernohookah.viewmodel.AuthViewModel
import com.pineapplepractice.infernohookah.viewmodel.DishesViewModel
import com.pineapplepractice.infernohookah.viewmodel.HomeViewModel
import com.pineapplepractice.infernohookah.viewmodel.MainActivityViewModel
import com.pineapplepractice.infernohookah.viewmodel.MiscellaneousViewModel
import com.pineapplepractice.infernohookah.viewmodel.RegistrationViewModel
import com.pineapplepractice.infernohookah.viewmodel.ReservationViewModel
import com.pineapplepractice.infernohookah.viewmodel.vmfactory.AuthViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val context: Context) {
    @Provides
    fun provideContext() = context

    @Singleton
    @Provides
    fun provideMainActivityViewModelFactory(
        getUserNameFromStorageUseCase: GetUserNameFromStorageUseCase
    ) = MainActivityViewModel.Factory(
        getUserNameFromStorageUseCase = getUserNameFromStorageUseCase
    )

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
        getFirstBookingUseCase: GetFirstBookingUseCase,
        getUserNameFromStorageUseCase: GetUserNameFromStorageUseCase
    ) = HomeViewModel.Factory(
        getFirstBookingUseCase = getFirstBookingUseCase,
        getUserNameFromStorageUseCase = getUserNameFromStorageUseCase

    )

    @Provides
    fun provideReservationViewModelFactory(
        saveBookingUseCase: SaveBookingUseCase,
        getUserIdFromStorageUseCase: GetUserIdFromStorageUseCase
    ) = ReservationViewModel.Factory(
        saveBookingUseCase = saveBookingUseCase,
        getUserIdFromStorageUseCase = getUserIdFromStorageUseCase
    )

    @Provides
    fun provideRegistrationViewModelFactory(
        saveUserUseCase: SaveUserUseCase,
        authUserUseCase : AuthUserUseCase
    ) = RegistrationViewModel.Factory(
        saveUserUseCase = saveUserUseCase,
        authUserUseCase = authUserUseCase
    )

    @Provides
    fun provideAuthViewModelFactory(
        getUserByLoginUseCase: GetUserByLoginUseCase,
        authUserUseCase : AuthUserUseCase

    ) = AuthViewModel.Factory(
        getUserByLoginUseCase = getUserByLoginUseCase,
        authUserUseCase = authUserUseCase
    )

    @Provides
    fun provideDishesViewModelFactory(
        getMenuFromApiUseCase: GetMenuFromApiUseCase

    ) = DishesViewModel.Factory(
        getMenuFromApiUseCase = getMenuFromApiUseCase
    )

    @Provides
    fun provideMiscellaneousViewModelFactory(
        saveReviewUseCase: SaveReviewUseCase

    ) = MiscellaneousViewModel.Factory(
        saveReviewUseCase = saveReviewUseCase
    )
}