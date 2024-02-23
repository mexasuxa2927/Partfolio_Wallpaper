package com.example.partfolio2.database

import androidx.room.*

@Dao
interface PhotoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserSingleData(dataEntity: DataEntity)

    @Query("SELECT * FROM photos_table")
    suspend fun getAllData():List<DataEntity>

    @Delete
    suspend fun deleteData(dataEntity: DataEntity)

}