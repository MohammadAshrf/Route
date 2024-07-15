package com.example.route.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.route.databinding.FragmentProductsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProductsFragment : Fragment() {
    private var _viewBinding: FragmentProductsBinding? = null
    private lateinit var productViewModel: ProductViewModel
    private val binding get() = _viewBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        productViewModel = ViewModelProvider(this)[ProductViewModel::class.java]
        initViews()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _viewBinding = FragmentProductsBinding.inflate(inflater, container, false)

        val productList = ProductListAdapter()
        binding.rvProductList.adapter = productList

        viewLifecycleOwner.lifecycleScope.launch {
            productViewModel.productList.collect {
                productList.submitList(it)
                productViewModel.getProducts()
            }
        }

        return binding.root
    }



    private fun initViews() {
        _viewBinding?.vm = productViewModel
        _viewBinding?.lifecycleOwner = this
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _viewBinding = null
    }
}