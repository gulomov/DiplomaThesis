package com.diploma.work.database.converter

import androidx.room.TypeConverter
import com.diploma.work.database.entity.ProductImages
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory


class Converters {
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val listType = Types.newParameterizedType(List::class.java, ProductImages::class.java)
    private val adapter = moshi.adapter<List<ProductImages>>(listType)

    @TypeConverter
    fun fromImagesList(images: List<ProductImages>): String {
        return adapter.toJson(images)
    }

    @TypeConverter
    fun toImagesList(imagesJson: String): List<ProductImages> {
        return adapter.fromJson(imagesJson) ?: emptyList()
    }
}