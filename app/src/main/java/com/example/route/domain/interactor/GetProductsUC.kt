package com.example.route.domain.interactor

import com.example.route.domain.repository.IProductRepository

class GetProductsUC (private val repository: IProductRepository) {
    suspend operator fun invoke() = repository.getProductsRemote()
}