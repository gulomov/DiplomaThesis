package com.diploma.work.comingsoon.domain

import com.diploma.work.repository.repository.ComingSoonRepository
import javax.inject.Inject

class GetComingSoonProductsUseCase @Inject constructor(
    private val comingSoonRepository: ComingSoonRepository
) {
    suspend operator fun invoke() = comingSoonRepository.getComingSoonProducts()
}