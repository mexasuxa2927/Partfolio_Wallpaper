package com.example.partfolio2.ViewModel

import android.provider.ContactsContract.CommonDataKinds.StructuredName
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.partfolio2.DataModel.RandomDataByTag.ListDataItem
import com.example.partfolio2.Database.DataEntity
import com.example.partfolio2.Database.PhotoDao
import com.example.partfolio2.Repository.Repository
import com.example.partfolio2.Retrofit.ApiKey
import com.example.partfolio2.Utils.Resources
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class MyViewModel @Inject constructor(private val repository: Repository):ViewModel(){
   private var stateFlow = MutableStateFlow<Resources>(Resources.LOADING)
    private var listdata  = MutableLiveData<List<DataEntity>>()


    fun fetchPhotos(title: String){
        viewModelScope.launch {
            val value = flow { emit(repository.getRemoteData().getRandomPhoto(ApiKey.apiKey,"30",title)) }
            value.catch {
                stateFlow.value  = Resources.ERROR("${it.message}")
            }
                .collect(){
                    stateFlow.value = Resources.SUCCES(it.body() ?: emptyList())
                }

        }
    }

    fun getPhoto(title:String):StateFlow<Resources>{
        fetchPhotos(title)
        return stateFlow
    }


    fun savePhoto(dataEntity: DataEntity){
        viewModelScope.launch(Dispatchers.IO) {
            repository.getLocalData().inserSingleData(dataEntity)
        }
    }
    fun getAllData():MutableLiveData<List<DataEntity>>{
        viewModelScope.launch {
           listdata.value  =  repository.getLocalData().getAllData()
        }
        return listdata
    }

    fun deleteData(dataEntity: DataEntity){
        viewModelScope.launch(Dispatchers.IO) {
            repository.getLocalData().deleteData(dataEntity)
        }
    }

}



