package com.example.partfolio2.utils

import com.example.partfolio2.data_model.RandomDataByTag.ListDataItem

sealed class Resources {

    data class ERROR(val msg: String) : Resources()
    data class SUCCES(val list: List<ListDataItem>):Resources()
    object LOADING:Resources()
}