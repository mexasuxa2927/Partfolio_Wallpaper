package com.example.partfolio2.Repository

import com.example.partfolio2.Database.PhotoDao
import com.example.partfolio2.Retrofit.ApiService
import java.security.PrivateKey
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