package com.pineapplepractice.infernohookah.di.modules

import android.content.Context
import com.pineapplepractice.infernohookah.data.HookahApi
import com.pineapplepractice.infernohookah.data.MainRepository
import com.pineapplepractice.infernohookah.domain.HookahInteractor
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DomainModule(val context: Context) {
    @Provides
    fun provideContext() = context

    @Singleton
    @Provides
    fun provideInteractor(
        hookahApi: HookahApi,
        repository: MainRepository
    ) = HookahInteractor(
        retrofitService = hookahApi,
        repo = repository
    )

}