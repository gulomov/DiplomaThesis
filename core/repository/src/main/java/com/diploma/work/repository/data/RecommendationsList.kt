package com.diploma.work.repository.data

import com.google.firebase.database.PropertyName

data class RecommendationsList(
    @PropertyName("recommendationsList")
    val recommendationsList: List<RecommendationItem>? = emptyList(),
)

data class RecommendationItem(
    @PropertyName("id") val id: Int? = null,
    @PropertyName("image") val image: String? = null,
    @PropertyName("brand") val brand: String? = null
)
