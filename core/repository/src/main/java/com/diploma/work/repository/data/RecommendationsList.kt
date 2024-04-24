package com.diploma.work.repository.data

import com.google.firebase.database.PropertyName

data class RecommendationsList(
    @PropertyName("title") val title: String? = null,
    @PropertyName("recommendationsList")
    val recommendationsList: List<RecommendationItem>? = emptyList()
)

data class RecommendationItem(
    @PropertyName("id") val id: Int? = null,
    @PropertyName("image") val image: String? = null
)
