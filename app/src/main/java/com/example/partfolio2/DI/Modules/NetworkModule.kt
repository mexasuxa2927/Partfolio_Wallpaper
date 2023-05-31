package com.example.partfolio2.DI.Modules

import com.example.partfolio2.BuildConfig
import com.example.partfolio2.Retrofit.ApiService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton


@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideBaseUrel():String= "https://api.unsplash.com/"

    @Provides
    @Singleton
    fun provideGsonConverterFactory():GsonConverterFactory=GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
       var  interceptor =  HttpLoggingInterceptor();

       if(BuildConfig.DEBUG){
           interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
       }
       else{
           interceptor.setLevel(HttpLoggingInterceptor.Level.NONE)
       }
       return OkHttpClient.Builder().addInterceptor(interceptor).build();
   }

    @Provides
    @Singleton
    fun provideApiClient(baseUrl:String,gsonConverterFactory: GsonConverterFactory,client: OkHttpClient):Retrofit{
        return Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(gsonConverterFactory).client(client).build()
    }

    @Provides
    @Singleton
    fun provideApiService(apiclient:Retrofit):ApiService = apiclient.create(ApiService::class.java)




}




















