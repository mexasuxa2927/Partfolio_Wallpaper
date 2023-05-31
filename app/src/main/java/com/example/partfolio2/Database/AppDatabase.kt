package com.example.partfolio2.Database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.partfolio2.DataModel.RandomDataByTag.ListDataItem

@Database(entities = [DataEntity::class], version = 1)
abstract class AppDatabase :RoomDatabase() {
    abstract fun photoDao():PhotoDao
}