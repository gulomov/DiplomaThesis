package com.diploma.work.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "top_products_list")
data class TopProductsListEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "image") val imageUrl: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "sale_percentage") val salePercentage: Int,
    @ColumnInfo(name = "sale_starts_date") val saleStartsDate: String,
    @ColumnInfo(name = "sale_ends_date") val saleEndsDate: String
)
