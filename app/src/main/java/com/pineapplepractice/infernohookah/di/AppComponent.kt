package com.pineapplepractice.infernohookah.di

import com.pineapplepractice.infernohookah.di.modules.DatabaseModule
import com.pineapplepractice.infernohookah.di.modules.DomainModule
import com.pineapplepractice.infernohookah.di.modules.RemoteModule
import com.pineapplepractice.infernohookah.viewmodel.HomeViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    //Внедряем все модули, нужные для этого компонента
    modules = [
        RemoteModule::class,
        DatabaseModule::class,
        DomainModule::class
    ]
)
interface AppComponent {
    fun inject(homeViewModel: HomeViewModel)

}