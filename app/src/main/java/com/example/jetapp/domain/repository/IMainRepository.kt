package com.example.jetapp.domain.repository

import com.example.jetapp.data.source.Resource
import com.example.jetapp.data.source.local.entity.ProductEntity
import com.example.jetapp.domain.model.ProductItem
import kotlinx.coroutines.flow.Flow

interface IMainRepository {
    fun getListProduct() : Flow<Resource<List<ProductItem>>>

    fun getDetailProduct(id: Int) : Flow<Resource<ProductItem>>

    fun getFavoriteProducts() : Flow<Resource<List<ProductEntity>>>

    suspend fun addToFavorite(productEntity: ProductEntity, favoriteState: Boolean) : Flow<Resource<ProductEntity>>

    suspend fun removeFavorite(productEntity: ProductEntity, favoriteState: Boolean) : Flow<Resource<ProductEntity>>
}