package com.example.partfolio2.App

import android.app.Application
import com.example.partfolio2.DI.Components.AppComponents
import com.example.partfolio2.DI.Components.DaggerAppComponents
import com.example.partfolio2.DI.Modules.LocalData

class App :Application(){

    companion object{
        lateinit var appComponents: AppComponents

    }
    override fun onCreate() {
        super.onCreate()
        appComponents  =DaggerAppComponents.builder()
            .localData(LocalData(this))
            .build()
    }
}