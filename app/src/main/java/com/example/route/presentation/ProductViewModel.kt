package com.example.route.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.model.Resource
import com.example.common.model.exception.RouteException
import com.example.route.domain.interactor.GetProductsUC
import com.example.route.domain.models.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(private val getProductsUC: GetProductsUC) : ViewModel() {

    private val _productList: MutableStateFlow<List<Product>?> = MutableStateFlow(null)
    val productList: StateFlow<List<Product>?> = _productList

    fun getProducts() {
        viewModelScope.launch {
            getProductsUC.invoke().collect {
                when (it) {
                    is Resource.Failure -> {
                        Log.d("TAG", "getProducts: ${it.exception}")
                    }
                    is Resource.Loading -> {
                        Log.d("TAG", "getProducts: Loading")
                    }
                    is Resource.Success -> {
                        _productList.value = it.model
                    }
                }
            }
        }
    }
}