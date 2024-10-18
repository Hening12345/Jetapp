package com.example.jetapp.utils

import com.example.jetapp.data.source.remote.response.ProductResponseItem
import com.example.jetapp.domain.model.ProductItem

object DataMapper {

    fun String.capitalizeWords(): String = split(" ").joinToString(" ") { it.capitalize() }

    fun productResponseToProductItem(data: List<ProductResponseItem>) : List<ProductItem> =
        data.map {
            ProductItem(
                image = it.image,
                price = it.price,
                description = it.description,
                title = it.title,
                id = it.id,
                category = it.category
            )
        }

    fun detailProductResponseToDetailProductItem(data: ProductResponseItem) : ProductItem =
        ProductItem(
            image = data.image,
            price = data.price,
            description = data.description,
            title = data.title,
            id = data.id,
            category = data.category
        )
}