package com.diploma.work.gallery.domain

import com.diploma.work.repository.repository.AllProductsRepository
import javax.inject.Inject

class GetAllProductsUseCase @Inject constructor(
    private val allProductsRepository: AllProductsRepository
){
    operator fun invoke() = allProductsRepository.getAllProducts()
}