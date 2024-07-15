package com.example.route.data.repository

import com.example.route.data.mapper.ProductMapper
import com.example.route.domain.models.Product
import com.example.route.domain.repository.IProductRepository
import com.example.route.domain.repository.remote.IProductRemoteDS

class ProductRepository(private val remoteDS: IProductRemoteDS) : IProductRepository {
    override suspend fun getProductsRemote(): List<Product> {
        val products = remoteDS.getProducts()
        return ProductMapper.dtoListToDomainList(products)
    }
}