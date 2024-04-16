package com.diploma.work.home.domain

import com.diploma.work.repository.data.RecommendationItem
import com.diploma.work.repository.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetHomeRecommendationsUseCase @Inject constructor(
    private val homeRepository: HomeRepository
) {
    operator fun invoke(): Flow<List<RecommendationItem>> = homeRepository.getRecommendations()
}