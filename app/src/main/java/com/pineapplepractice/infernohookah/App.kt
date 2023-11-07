package com.pineapplepractice.infernohookah

import android.app.Application
import com.pineapplepractice.infernohookah.di.AppComponent
import com.pineapplepractice.infernohookah.di.DaggerAppComponent
import com.pineapplepractice.infernohookah.di.modules.DatabaseModule
import com.pineapplepractice.infernohookah.di.modules.DomainModule
import com.pineapplepractice.infernohookah.di.modules.RemoteModule

class App : Application() {
    lateinit var dagger: AppComponent
    override fun onCreate() {
        super.onCreate()

        instance = this
        dagger = DaggerAppComponent.builder()
            .remoteModule(RemoteModule())
            .databaseModule(DatabaseModule())
            .domainModule(DomainModule(this))
            .build()
    }

    companion object {
        //Здесь статически хранится ссылка на экземпляр App
        lateinit var instance: App
            //Приватный сеттер, чтобы нельзя было в эту переменную присвоить что-либо другое
            private set
    }
}