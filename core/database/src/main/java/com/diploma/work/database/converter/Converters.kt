package com.diploma.work.database.converter

import androidx.room.TypeConverter
import com.diploma.work.database.entity.ProductImages
import com.diploma.work.database.entity.ProductSizes
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.adapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory


class Converters {
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val imagesListType =
        Types.newParameterizedType(List::class.java, ProductImages::class.java)
    private val imagesAdapter = moshi.adapter<List<ProductImages>>(imagesListType)

    private val sizesListType =
        Types.newParameterizedType(List::class.java, ProductSizes::class.java)
    private val sizesAdapter = moshi.adapter<List<ProductSizes>>(sizesListType)

    @TypeConverter
    fun fromImagesList(images: List<ProductImages>): String {
        return imagesAdapter.toJson(images)
    }

    @TypeConverter
    fun toImagesList(imagesJson: String): List<ProductImages> {
        return imagesAdapter.fromJson(imagesJson) ?: emptyList()
    }

    @TypeConverter
    fun fromSizesList(sizes: List<ProductSizes>): String {
        return sizesAdapter.toJson(sizes)
    }

    @TypeConverter
    fun toSizesList(sizesJson: String): List<ProductSizes> {
        return sizesAdapter.fromJson(sizesJson) ?: emptyList()
    }
}