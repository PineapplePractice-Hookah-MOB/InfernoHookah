package com.pineapplepractice.infernohookah.di.modules

import android.content.Context
import androidx.room.Room
import com.pineapplepractice.infernohookah.data.MainRepository
import com.pineapplepractice.infernohookah.data.dao.InfernoDao
import com.pineapplepractice.infernohookah.data.db.InfernoDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun provideArtDao(context: Context) =
        Room.databaseBuilder(
            context,
            InfernoDatabase::class.java,
            "maibo_db"
        ).build().infernoDao()

    @Provides
    @Singleton
    fun provideRepository(infernoDao: InfernoDao) = MainRepository(infernoDao)

}