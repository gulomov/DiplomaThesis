package com.diploma.work.home.domain

import com.diploma.work.repository.repository.HomeRepository
import javax.inject.Inject

class GetHomeScreenNewsUseCase @Inject constructor(
    private val homeRepository: HomeRepository,
) {
    operator fun invoke() = homeRepository.getNewsInfo()
}
