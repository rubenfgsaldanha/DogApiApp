package com.example.dogapiapp.data.local.dbmodels

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dog_breed_weight")
data class WeightDbModel(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,

    @ColumnInfo(name = "imperial")
    val imperial: String,

    @ColumnInfo(name = "metric")
    val metric: String,
)
