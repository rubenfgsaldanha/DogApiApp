package com.example.dogapiapp.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.dogapiapp.data.local.converters.DogBreedConverters

@Entity(tableName = "dog_breeds")
data class DogBreedDbModel(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,

    @ColumnInfo(name = "bred_for")
    val bredFor: String?,

    @ColumnInfo(name = "breed_group")
    val breedGroup: String?,

    @TypeConverters(DogBreedConverters::class)
    @ColumnInfo(name = "height")
    val height: HeightDbModel?,

    @ColumnInfo(name = "breed_id")
    val breedId: Int,

    @TypeConverters(DogBreedConverters::class)
    @ColumnInfo(name = "image")
    val image: ImageDbModel?,

    @ColumnInfo(name = "life_span")
    val lifeSpan: String?,

    @ColumnInfo(name = "name")
    val name: String?,

    @ColumnInfo(name = "origin")
    val origin: String?,

    @ColumnInfo(name = "reference_image_id")
    val referenceImageId: String?,

    @ColumnInfo(name = "temperament")
    val temperament: String?,

    @TypeConverters(DogBreedConverters::class)
    @ColumnInfo(name = "weight")
    val weight: WeightDbModel?,
)