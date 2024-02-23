package com.example.partfolio2.dI.Components

import com.example.partfolio2.dI.Modules.LocalData
import com.example.partfolio2.dI.Modules.NetworkModule
import com.example.partfolio2.MainActivity
import com.example.partfolio2.pages.Home_page
import com.example.partfolio2.pages.ItemPage
import com.example.partfolio2.pages.SavePhotoPage
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class,LocalData::class])
interface AppComponents {
    fun inject(mainActivity: MainActivity)
    fun inject(homePage: Home_page)
    fun inject(itemPage: ItemPage)
    fun inject(savePhotoPage: SavePhotoPage)
}