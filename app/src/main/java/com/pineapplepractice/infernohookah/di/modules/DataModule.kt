package com.pineapplepractice.infernohookah.di.modules

import android.content.Context
import com.example.cleanarchitecturekiparokovalenko.data.storage.sharedprefs.SharedPrefStorage
import com.pineapplepractice.infernohookah.data.dao.InfernoDao
import com.pineapplepractice.infernohookah.data.remote.NetworkApi
import com.pineapplepractice.infernohookah.data.remote.booking.BookingApi
import com.pineapplepractice.infernohookah.data.remote.zvonok.ZvonokApi
import com.pineapplepractice.infernohookah.data.repository.MainRepositoryImpl
import com.pineapplepractice.infernohookah.data.storage.Storage
import com.pineapplepractice.infernohookah.domain.repositoryinterface.MainRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    fun provideStorage(context: Context): Storage {
        return SharedPrefStorage(context = context)
    }

    @Provides
    @Singleton
    fun provideRepository(
        storage: Storage,
        networkApi: NetworkApi,
        bookingApi: BookingApi
    ): MainRepository {
        return MainRepositoryImpl(
            storage = storage,
            networkApi = networkApi,
            bookingApi = bookingApi
        )
    }

    /*    @Provides
    @Singleton
    fun provideRepository(
        storage: Storage,
        networkApi: NetworkApi,
        infernoDao: InfernoDao
    ): MainRepository {
        return MainRepositoryImpl(
            storage = storage,
            networkApi = networkApi,
            infernoDao = infernoDao
        )
    }*/
}