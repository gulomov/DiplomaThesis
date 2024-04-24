package com.diploma.work.home.domain

import com.diploma.work.repository.data.NewsItem
import com.diploma.work.repository.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetHomeScreenNewsUseCase @Inject constructor(
    private val homeRepository: HomeRepository
) {
    operator fun invoke() = homeRepository.getNewsInfo()
}
