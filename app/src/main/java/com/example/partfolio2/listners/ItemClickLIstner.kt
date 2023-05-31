package com.example.partfolio2.listners

import com.example.partfolio2.DataModel.RandomDataByTag.ListDataItem

interface ItemClickLIstner {
    fun itemClickListner(listDataItem: ListDataItem)
    fun logclickItem(listDataItem: ListDataItem)

}