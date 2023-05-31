package com.example.partfolio2.Database

import androidx.room.*
import com.example.partfolio2.DataModel.RandomDataByTag.ListDataItem
import retrofit2.Response

@Dao
interface PhotoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserSingleData(dataEntity: DataEntity)

    @Query("SELECT * FROM photos_table")
    suspend fun getAllData():List<DataEntity>

    @Delete
    suspend fun deleteData(dataEntity: DataEntity)

}