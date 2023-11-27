package com.pineapplepractice.infernohookah.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pineapplepractice.infernohookah.data.Promotions
import com.pineapplepractice.infernohookah.data.dao.InfernoDao

@Database(entities = [Promotions::class], version = 1, exportSchema = false)
abstract class InfernoDatabase : RoomDatabase() {
    abstract fun infernoDao(): InfernoDao
}