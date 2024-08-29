package com.diploma.work.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "coming_soon_products")
data class ComingSoonProductEntity(
    @PrimaryKey(autoGenerate = false) val id: Int,
    val image: String,
    val startingDate: String,
    val brand: String
)