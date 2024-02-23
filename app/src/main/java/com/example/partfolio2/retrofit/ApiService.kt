package com.example.partfolio2.retrofit

import com.example.partfolio2.data_model.RandomDataByTag.ListDataItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {


    @GET("photos/random/")
    suspend fun getRandomPhoto(@Query("client_id") key:String,@Query("count") count:String,@Query("query") term:String,@Query("orientation") orientatin:String ="portrait" ):Response<List<ListDataItem>>



}