package com.example.partfolio2.DI.Components

import com.example.partfolio2.DI.Modules.LocalData
import com.example.partfolio2.DI.Modules.NetworkModule
import com.example.partfolio2.MainActivity
import com.example.partfolio2.Pages.Home_page
import com.example.partfolio2.Pages.ItemPage
import com.example.partfolio2.Pages.SavePhotoPage
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