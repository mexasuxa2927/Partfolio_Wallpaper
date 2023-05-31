package com.example.partfolio2.Utils

import com.example.partfolio2.DataModel.RandomDataByTag.ListDataItem

sealed class Resources {

    data class ERROR(val msg: String) : Resources()
    data class SUCCES(val list: List<ListDataItem>):Resources()
    object LOADING:Resources()
}