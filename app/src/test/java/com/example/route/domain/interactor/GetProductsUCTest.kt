package com.example.route.domain.interactor

import com.example.route.domain.models.Product
import com.example.route.domain.repository.IProductRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Assert.assertThrows
import org.junit.Before
import org.junit.Test

class GetProductsUCTest {
    @MockK
    private lateinit var productRepository: IProductRepository

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `should return list of products when getProductsRemote is called`() {
        runTest {
            // Arrange
            val expectedProducts = listOf(mockk<Product>())
            coEvery { productRepository.getProductsRemote() } returns expectedProducts

            // Act
            val actualProducts = productRepository.getProductsRemote()

            // Assert
            Assert.assertEquals(expectedProducts, actualProducts)
        }
    }

    @Test
    fun `should return empty list when repository returns empty list`() {
        runTest {
            coEvery { productRepository.getProductsRemote() } returns emptyList()
            val actualProducts = productRepository.getProductsRemote()
            Assert.assertTrue(actualProducts.isEmpty())
        }
    }

    @Test
    fun `should throw exception when repository throws exception`() {
        runTest {
            val exception = Exception("Network error")
            coEvery { productRepository.getProductsRemote() } throws exception
            assertThrows(exception.javaClass) {
                runTest {
                    productRepository.getProductsRemote()
                }
            }
        }
    }
}