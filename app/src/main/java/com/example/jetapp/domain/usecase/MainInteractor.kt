package com.example.jetapp.domain.usecase

import com.example.jetapp.data.source.Resource
import com.example.jetapp.data.source.local.entity.ProductEntity
import com.example.jetapp.domain.model.ProductItem
import com.example.jetapp.domain.repository.IMainRepository
import kotlinx.coroutines.flow.Flow

class MainInteractor(private val mainRepository: IMainRepository) : MainUseCase {
    override fun getListProduct(): Flow<Resource<List<ProductItem>>> = mainRepository.getListProduct()
    override fun getDetailProduct(id: Int): Flow<Resource<ProductItem>> = mainRepository.getDetailProduct(id)
    override fun getFavoriteProducts(): Flow<Resource<List<ProductEntity>>> = mainRepository.getFavoriteProducts()
    override suspend fun addToFavorite(productEntity: ProductEntity, favoriteState: Boolean): Flow<Resource<ProductEntity>> = mainRepository.addToFavorite(productEntity, favoriteState)
    override suspend fun removeFavorite(productEntity: ProductEntity, favoriteState: Boolean): Flow<Resource<ProductEntity>> = mainRepository.removeFavorite(productEntity, favoriteState)
}