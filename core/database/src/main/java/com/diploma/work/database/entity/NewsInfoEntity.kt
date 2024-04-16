package com.diploma.work.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news_info")
data class NewsInfoEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "image") val image: String?,
    @ColumnInfo(name = "body") val body: String?,
    @ColumnInfo(name = "title") val title: String?
)
