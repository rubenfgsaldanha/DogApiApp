package com.example.dogapiapp.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dog_breed_height")
data class HeightDbModel(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,

    @ColumnInfo(name = "imperial")
    val imperial: String?,

    @ColumnInfo(name = "metric")
    val metric: String?,
)
