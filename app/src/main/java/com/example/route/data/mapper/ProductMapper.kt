package com.example.route.data.mapper

import com.example.common.mapper.Mapper
import com.example.route.data.models.dto.ProductDto
import com.example.route.domain.models.Dimensions
import com.example.route.domain.models.Meta
import com.example.route.domain.models.Product
import com.example.route.domain.models.Reviews
import java.math.BigDecimal
import java.math.RoundingMode

internal object ProductMapper : Mapper<ProductDto, Product, Unit>() {
    override fun dtoToDomain(model: ProductDto): Product {
        val price = model.price ?: 0.0
        val discountPercentage = model.discountPercentage ?: 0.0
        val discountedPrice = if (price != 0.0 && discountPercentage != 0.0) {
            calculateDiscountedPrice(price, discountPercentage)
        } else {
            price
        }

        return Product(
            id = model.id ?: 0,
            title = model.title.orEmpty(),
            description = model.description.orEmpty(),
            category = model.category.orEmpty(),
            price = price, // Keep the original price
            discountPercentage = discountPercentage,
            discountedPrice = discountedPrice, // Store the discounted price
            rating = model.rating ?: 0.0,
            stock = model.stock ?: 0,
            tags = model.tags.orEmpty(),
            brand = model.brand.orEmpty(),
            sku = model.sku.orEmpty(),
            weight = model.weight ?: 0,
            dimensions = Dimensions(
                width = model.dimensionsDto?.width ?: 0.0,
                height = model.dimensionsDto?.height ?: 0.0,
                depth = model.dimensionsDto?.depth ?: 0.0
            ),
            warrantyInformation = model.warrantyInformation.orEmpty(),
            shippingInformation = model.shippingInformation.orEmpty(),
            availabilityStatus = model.availabilityStatus.orEmpty(),
            reviews = model.reviews?.map { review ->
                Reviews(
                    rating = review?.rating ?: 0,
                    comment = review?.comment.orEmpty(),
                    date = review?.date.orEmpty(),
                    reviewerName = review?.reviewerName.orEmpty(),
                    reviewerEmail = review?.reviewerEmail.orEmpty()
                )
            } ?: emptyList(),
            returnPolicy = model.returnPolicy.orEmpty(),
            minimumOrderQuantity = model.minimumOrderQuantity ?: 0,
            meta = Meta(
                createdAt = model.metaDto?.createdAt.orEmpty(),
                updatedAt = model.metaDto?.updatedAt.orEmpty(),
                barcode = model.metaDto?.barcode.orEmpty(),
                qrCode = model.metaDto?.qrCode.orEmpty()
            ),
            images = model.images.orEmpty(),
            thumbnail = model.thumbnail.orEmpty()
        )
    }

    private fun calculateDiscountedPrice(
        oldPrice: Double?,
        discountPercentage: Double?
    ): Double {
        if (oldPrice == null || discountPercentage == null) {
            return 0.00 // Return default value or handle error case
        }

        // Calculate discounted price
        val discountFactor = 1 - (discountPercentage / 100)
        val discountedPrice = oldPrice * discountFactor

        // Round to two decimal places
        val roundedDiscountedPrice =
            BigDecimal(discountedPrice).setScale(2, RoundingMode.HALF_UP).toDouble()
        return roundedDiscountedPrice
    }

    fun dtoListToDomainList(dtoList: List<ProductDto>?): List<Product> {
        return dtoList?.map { dtoToDomain(it) } ?: emptyList()
    }
}