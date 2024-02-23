package com.example.partfolio2.dI.Modules

import android.content.Context
import androidx.room.Room
import com.example.partfolio2.database.AppDatabase
import com.example.partfolio2.database.PhotoDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class LocalData(val context: Context ) {

    @Singleton
    @Provides
    fun provideContext():Context   = context

    @Singleton
    @Provides
    fun provideAppDatabase(context: Context):AppDatabase{
        return Room.databaseBuilder(context,AppDatabase::class.java,"local.db").build()
    }


    @Singleton
    @Provides
    fun provideDao(appDatabase: AppDatabase):PhotoDao{
        return appDatabase.photoDao()
    }



}