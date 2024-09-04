package com.diploma.work.common.domain

import com.diploma.work.repository.repository.ComingSoonRepository
import javax.inject.Inject

class FetchComingSoonProductsUseCase @Inject constructor(
    private val comingSoonRepository: ComingSoonRepository
) {
    suspend operator fun invoke() = comingSoonRepository.fetchAndSaveComingSoonProducts()
}