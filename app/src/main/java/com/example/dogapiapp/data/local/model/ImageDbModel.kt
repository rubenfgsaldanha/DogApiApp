package com.example.dogapiapp.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dob_breed_image")
data class ImageDbModel(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,

    @ColumnInfo(name = "height")
    val height: Int?,

    @ColumnInfo(name = "image_id")
    val imageId: String,

    @ColumnInfo(name = "url")
    val url: String?,

    @ColumnInfo(name = "width")
    val width: Int?,
)
