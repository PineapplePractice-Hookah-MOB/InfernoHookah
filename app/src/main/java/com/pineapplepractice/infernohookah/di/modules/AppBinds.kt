package com.pineapplepractice.infernohookah.di.modules

import androidx.lifecycle.ViewModelProvider
import com.pineapplepractice.infernohookah.viewmodel.MainActivityViewModel
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface AppBinds {
    @Singleton
    @Binds
    fun bindViewModelFactory(factory: MainActivityViewModel.Factory): ViewModelProvider.Factory

}