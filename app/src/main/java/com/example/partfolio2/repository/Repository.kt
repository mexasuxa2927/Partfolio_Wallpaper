package com.example.partfolio2.repository

import com.example.partfolio2.database.PhotoDao
import com.example.partfolio2.retrofit.ApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class  Repository @Inject constructor(private val apiService: ApiService
    ,private val localDao: PhotoDao
) {

    fun getRemoteData():ApiService
    {
        return apiService
    }

    fun getLocalData():PhotoDao{
        return localDao
    }

}