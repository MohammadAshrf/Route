package com.example.route.domain.interactor

import com.example.common.mapper.ExceptionMapper
import com.example.common.model.Resource
import com.example.route.domain.models.Product
import com.example.route.domain.repository.IProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException

class GetProductsUC(private val repository: IProductRepository) {
    suspend operator fun invoke(): Flow<Resource<List<Product>>> = channelFlow {
        send(Resource.loading())
        try {
            val result = repository.getProductsRemote()
            send(Resource.success(result))
            send(Resource.loading(false))
        } catch (e: HttpException) {
            val mappedException = ExceptionMapper.map(e)
            send(Resource.failure(mappedException))
            send(Resource.loading(false))
        } catch (e: Exception) {
            val mappedException = ExceptionMapper.map(e)
            send(Resource.failure(mappedException))
            send(Resource.loading(false))
        }
    }.flowOn(Dispatchers.IO)
}