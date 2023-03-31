package com.example.dogapiapp.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dog_breeds_remote_keys")
data class DogBreedRemoteKeys(
    @PrimaryKey(autoGenerate = false)
    val id: Int,

    @ColumnInfo(name = "previous_key")
    val previousKey: Int?,

    @ColumnInfo(name = "next_key")
    val nextKey: Int?
)
