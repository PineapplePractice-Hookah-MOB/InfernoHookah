package com.pineapplepractice.infernohookah.di.modules

import android.content.Context
import androidx.room.Room
import com.pineapplepractice.infernohookah.data.repository.MainRepositoryImpl
import com.pineapplepractice.infernohookah.data.dao.InfernoDao
import com.pineapplepractice.infernohookah.data.db.InfernoDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun provideInfernoDao(context: Context) =
        Room.databaseBuilder(
            context,
            InfernoDatabase::class.java,
            "inferno_db"
        ).build().infernoDao()

}