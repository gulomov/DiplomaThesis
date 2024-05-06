package com.diploma.work.home.domain

import com.diploma.work.repository.repository.HomeRepository
import javax.inject.Inject

class GetNewsDetailUseCase @Inject constructor(
    private val homeRepository: HomeRepository
) {
    operator fun invoke(id: Int) = homeRepository.getNewsDetail(id)
}