package com.diploma.work.repository.data

import com.google.firebase.database.PropertyName

data class NewsInfo(
    @PropertyName("newsList") val newsList: List<NewsItem>? = emptyList(),
    @PropertyName("title") val title: String? = null,
)

data class NewsItem(
    @PropertyName("id") val id: Int? = null,
    @PropertyName("image") val image: String? = null,
    @PropertyName("body") val body: String? = null,
    @PropertyName("title") val title: String? = null,
)
