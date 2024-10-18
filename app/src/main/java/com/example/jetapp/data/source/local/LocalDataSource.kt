package com.example.jetapp.data.source.local

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.asFlow
import com.example.jetapp.data.source.Resource
import com.example.jetapp.data.source.local.entity.ProductEntity
import com.example.jetapp.data.source.local.room.ProductsDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class LocalDataSource(private val productsDao: ProductsDao) {

    fun getFavoriteProducts() : Flow<Resource<List<ProductEntity>>>  = flow{
        emit(Resource.Loading(true))
        try {
            val products = productsDao.getFavoriteProducts().asFlow().first()
            emit(Resource.Success(products))
        } catch (e: Exception) {
            Log.d("LocalDataSource", "Error retrieving favorite products: ${e.localizedMessage}")
            emit(Resource.Error(e.message.toString()))
        }
    }

    suspend fun addToFavorite(product: ProductEntity, favoriteState: Boolean) : Flow<Resource<ProductEntity>> = flow {
        try {
            product.isFavorite = favoriteState
            productsDao.insertProducts(product)
            emit(Resource.Success(product))
        }catch (e: Exception) {
            Log.d("LocalDataSource", "error : $product")
            emit(Resource.Error(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)

    suspend fun removeFavorite(product: ProductEntity, favoriteState: Boolean) : Flow<Resource<ProductEntity>> = flow {
        try {
            product.isFavorite = favoriteState
            productsDao.deleteProducts(product)
            emit(Resource.Success(product))
        }catch (e: Exception) {
            Log.d("LocalDataSource", "error : $product")
            emit(Resource.Error(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)
}