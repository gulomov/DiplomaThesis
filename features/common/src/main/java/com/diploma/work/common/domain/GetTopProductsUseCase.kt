package com.diploma.work.common.domain

import com.diploma.work.repository.repository.HomeRepository
import javax.inject.Inject

class GetTopProductsUseCase
    @Inject
    constructor(
        private val homeRepository: HomeRepository,
    ) {
        operator fun invoke() = homeRepository.getTopProducts()
    }
