package com.example.partfolio2.app

import android.app.Application
import com.example.partfolio2.dI.Components.AppComponents
import com.example.partfolio2.dI.Components.DaggerAppComponents
import com.example.partfolio2.dI.Modules.LocalData

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