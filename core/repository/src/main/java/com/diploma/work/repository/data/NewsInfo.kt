package com.diploma.work.repository.data

data class NewsInfo(
    val newsList: List<NewsItem>? = emptyList(),
    val titleText: String? = null
)

data class NewsItem(
    val imageUrl: String? = null,
    val bodyText: String? = null,
    val titleText: String? = null
)
