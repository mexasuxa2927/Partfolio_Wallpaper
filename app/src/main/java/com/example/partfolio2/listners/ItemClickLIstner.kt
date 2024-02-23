package com.example.partfolio2.listners

import com.example.partfolio2.data_model.RandomDataByTag.ListDataItem

interface ItemClickLIstner {
    fun itemClickListner(listDataItem: ListDataItem)
    fun logclickItem(listDataItem: ListDataItem)

}