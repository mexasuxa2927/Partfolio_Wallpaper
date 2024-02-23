package com.example.partfolio2.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [DataEntity::class], version = 1)
abstract class AppDatabase :RoomDatabase() {
    abstract fun photoDao():PhotoDao
}