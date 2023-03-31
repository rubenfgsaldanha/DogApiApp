package com.example.dogapiapp.data.local.converters

import androidx.room.TypeConverter
import com.example.dogapiapp.data.local.model.HeightDbModel
import com.example.dogapiapp.data.local.model.ImageDbModel
import com.example.dogapiapp.data.local.model.WeightDbModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object DogBreedConverters {

    @TypeConverter
    @JvmStatic
    fun fromHeightToJson(height: HeightDbModel?): String? =
        height?.let {
            Gson().toJson(it)
        }

    @TypeConverter
    @JvmStatic
    fun fromJsonToHeight(jsonString: String?): HeightDbModel? {
        val type = object : TypeToken<HeightDbModel?>() {}.type
        return jsonString?.let {
            Gson().fromJson(jsonString, type)
        }
    }

    @TypeConverter
    @JvmStatic
    fun fromWeightToJson(weight: WeightDbModel?): String? =
        weight?.let {
            Gson().toJson(it)
        }

    @TypeConverter
    @JvmStatic
    fun fromJsonToWeight(jsonString: String?): WeightDbModel? {
        val type = object : TypeToken<WeightDbModel?>() {}.type
        return jsonString?.let {
            Gson().fromJson(jsonString, type)
        }
    }

    @TypeConverter
    @JvmStatic
    fun fromImageToJson(image: ImageDbModel?): String? =
        image?.let {
            Gson().toJson(image)
        }

    @TypeConverter
    @JvmStatic
    fun fromJsonToImage(jsonString: String?): ImageDbModel? {
        val type = object : TypeToken<ImageDbModel?>() {}.type
        return jsonString?.let {
            Gson().fromJson(jsonString, type)
        }
    }
}