package com.example.dogapiapp.data.local.converters

import androidx.room.TypeConverter
import com.example.dogapiapp.data.local.dbmodels.HeightDbModel
import com.example.dogapiapp.data.local.dbmodels.ImageDbModel
import com.example.dogapiapp.data.local.dbmodels.WeightDbModel
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi

object DogBreedConverters {

    private val moshi = Moshi.Builder().build()

    @TypeConverter
    fun fromHeightToJson(height: HeightDbModel?): String? {
        val jsonAdapter: JsonAdapter<HeightDbModel> = moshi.adapter(HeightDbModel::class.java)
        return jsonAdapter.toJson(height)
    }

    @TypeConverter
    fun fromJsonToHeight(jsonString: String?): HeightDbModel? {
        val jsonAdapter: JsonAdapter<HeightDbModel> = moshi.adapter(HeightDbModel::class.java)
        return jsonString?.let { jsonAdapter.fromJson(it) }
    }

    @TypeConverter
    fun fromWeightToJson(weight: WeightDbModel?): String? {
        val jsonAdapter: JsonAdapter<WeightDbModel> = moshi.adapter(WeightDbModel::class.java)
        return jsonAdapter.toJson(weight)
    }

    @TypeConverter
    fun fromJsonToWeight(jsonString: String?): WeightDbModel? {
        val jsonAdapter: JsonAdapter<WeightDbModel> = moshi.adapter(WeightDbModel::class.java)
        return jsonString?.let { jsonAdapter.fromJson(it) }
    }

    @TypeConverter
    fun fromImageToJson(image: ImageDbModel?): String? {
        val jsonAdapter: JsonAdapter<ImageDbModel> = moshi.adapter(ImageDbModel::class.java)
        return jsonAdapter.toJson(image)
    }

    @TypeConverter
    fun fromJsonToImage(jsonString: String?): ImageDbModel? {
        val jsonAdapter: JsonAdapter<ImageDbModel> = moshi.adapter(ImageDbModel::class.java)
        return jsonString?.let { jsonAdapter.fromJson(it) }
    }
}