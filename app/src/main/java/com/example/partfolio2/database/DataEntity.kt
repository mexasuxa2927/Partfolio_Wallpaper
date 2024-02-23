package com.example.partfolio2.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "photos_table")
data class DataEntity (
    @PrimaryKey
    @NotNull
    var data :String  = ""
    )