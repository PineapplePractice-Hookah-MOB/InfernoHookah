package com.pineapplepractice.infernohookah.di.modules

import android.content.Context
import com.pineapplepractice.infernohookah.data.HookahApi
import com.pineapplepractice.infernohookah.data.repository.MainRepositoryImpl
import com.pineapplepractice.infernohookah.data.PreferenceProvider
import com.pineapplepractice.infernohookah.data.remote.NetworkApi
import com.pineapplepractice.infernohookah.data.remote.booking.BookingApi
import com.pineapplepractice.infernohookah.domain.HookahInteractor
import com.pineapplepractice.infernohookah.domain.repositoryinterface.MainRepository
import com.pineapplepractice.infernohookah.domain.usecase.GetFirstBookingUseCase
import com.pineapplepractice.infernohookah.domain.usecase.GetUserByLoginUseCase
import com.pineapplepractice.infernohookah.domain.usecase.LoginByPhoneUseCase
import com.pineapplepractice.infernohookah.domain.usecase.SaveBookingUseCase
import com.pineapplepractice.infernohookah.domain.usecase.SavePhoneToSharedPrefUseCase
import com.pineapplepractice.infernohookah.domain.usecase.SaveUserUseCase
import com.pineapplepractice.infernohookah.domain.usecase.ValidatePhoneNumberUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DomainModule() {

    @Singleton
    @Provides
    fun provideGetUserByLoginUseCase(mainRepository: MainRepository): GetUserByLoginUseCase {
        return GetUserByLoginUseCase(mainRepository = mainRepository)
    }
    @Singleton
    @Provides
    fun provideSaveUserUseCase(mainRepository: MainRepository): SaveUserUseCase {
        return SaveUserUseCase(mainRepository = mainRepository)
    }

    @Singleton
    @Provides
    fun provideSaveBookingUseCase(mainRepository: MainRepository): SaveBookingUseCase {
        return SaveBookingUseCase(mainRepository = mainRepository)
    }

    @Singleton
    @Provides
    fun provideGetFirstBookingUseCase(mainRepository: MainRepository): GetFirstBookingUseCase {
        return GetFirstBookingUseCase(mainRepository = mainRepository)
    }

    @Singleton
    @Provides
    fun provideLoginByPhoneUseCase(mainRepository: MainRepository): LoginByPhoneUseCase {
        return LoginByPhoneUseCase(mainRepository = mainRepository)
    }

    @Singleton
    @Provides
    fun provideSavePhoneToSharedPrefUseCase(mainRepository: MainRepository): SavePhoneToSharedPrefUseCase {
        return SavePhoneToSharedPrefUseCase(mainRepository = mainRepository)
    }

    @Singleton
    @Provides
    fun provideValidatePhoneNumberUseCase(): ValidatePhoneNumberUseCase {
        return ValidatePhoneNumberUseCase()
    }

    @Singleton
    @Provides
    //Создаем экземпляр SharedPreferences
    fun providePreferences(context: Context) = PreferenceProvider(context)

    @Singleton
    @Provides
    fun provideInteractor(
        hookahApi: HookahApi,
        repository: MainRepository,
        bookingApi: BookingApi
    ) = HookahInteractor(
        retrofitService = hookahApi,
        repo = repository,
        bookingApi = bookingApi

    )

}