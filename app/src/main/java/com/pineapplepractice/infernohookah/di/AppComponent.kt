package com.pineapplepractice.infernohookah.di

import com.pineapplepractice.infernohookah.di.modules.AppModule
import com.pineapplepractice.infernohookah.di.modules.DataModule
import com.pineapplepractice.infernohookah.di.modules.DatabaseModule
import com.pineapplepractice.infernohookah.di.modules.DomainModule
import com.pineapplepractice.infernohookah.di.modules.RemoteModule
import com.pineapplepractice.infernohookah.view.fragments.AuthFragment
import com.pineapplepractice.infernohookah.view.fragments.DishesFragment
import com.pineapplepractice.infernohookah.view.fragments.HomeFragment
import com.pineapplepractice.infernohookah.view.fragments.MiscellaneousFragment
import com.pineapplepractice.infernohookah.view.fragments.RegistrationFragment
import com.pineapplepractice.infernohookah.view.fragments.ReservationFragment
import com.pineapplepractice.infernohookah.viewmodel.HomeViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    //Внедряем все модули, нужные для этого компонента
    modules = [
        AppModule::class,
        DatabaseModule::class,
        DataModule::class,
        DomainModule::class,
        RemoteModule::class,
    ]
)
interface AppComponent {
    fun inject(homeViewModel: HomeViewModel)
    fun inject(homeFragment: HomeFragment)
    fun inject(reservationFragment: ReservationFragment)
    fun inject(registrationFragment: RegistrationFragment)
    fun inject(authFragment: AuthFragment)
    fun inject(dishesFragment: DishesFragment)
    fun inject(miscellaneousFragment: MiscellaneousFragment)

}