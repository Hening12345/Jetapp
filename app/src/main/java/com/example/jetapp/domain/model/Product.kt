package com.example.jetapp.domain.model

import com.google.gson.annotations.SerializedName

data class Product(
	val product: List<ProductItem>
)

data class ProductItem(
	val image: String? = null,
	val price: Any? = null,
	val rating: Rating? = null,
	val description: String? = null,
	val id: Int? = null,
	val title: String? = null,
	val category: String? = null
)

data class Rating(
	val rate: Any? = null,
	val count: Int? = null
)
