package com.example.jetapp.data.repository

import android.util.Log
import com.example.jetapp.data.source.Resource
import com.example.jetapp.data.source.local.LocalDataSource
import com.example.jetapp.data.source.local.entity.ProductEntity
import com.example.jetapp.data.source.remote.RemoteDataSource
import com.example.jetapp.data.source.remote.network.ApiResponse
import com.example.jetapp.data.source.remote.network.ApiService
import com.example.jetapp.domain.model.ProductItem
import com.example.jetapp.domain.repository.IMainRepository
import com.example.jetapp.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class MainRepository(private val remoteDataSource: RemoteDataSource, private val localDataSource: LocalDataSource) : IMainRepository {
    override fun getListProduct(): Flow<Resource<List<ProductItem>>>  = flow {
        emit(Resource.Loading(true))
        when(val apiResponse = remoteDataSource.getListProduct().first()) {
            is ApiResponse.Success -> {
                val data = DataMapper.productResponseToProductItem(apiResponse.data)
                Log.d("MainRepository", "Data mapped: $data")
                emit(Resource.Success(data))
            }
            is ApiResponse.Error -> {
                Log.d("MainRepository", "Error: ${apiResponse.message}")
                emit(Resource.Error(apiResponse.message))
            }
            is ApiResponse.Empty -> {
                Log.d("MainRepository", "No data")
            }
        }
    }

    override fun getDetailProduct(id: Int): Flow<Resource<ProductItem>> = flow{
        emit(Resource.Loading(true))
        when(val apiResponse = remoteDataSource.getDetailProducts(id).first()) {
            is ApiResponse.Success -> {
                val data = DataMapper.detailProductResponseToDetailProductItem(apiResponse.data)
                emit(Resource.Success(data))
            }
            is ApiResponse.Error -> {
                Log.d("MainRepository", "Error : ${apiResponse.message}")
                emit(Resource.Error(apiResponse.message))
            }
            is ApiResponse.Empty -> {
                Log.d("MainRepository", "No data")
            }
        }
    }

    override fun getFavoriteProducts(): Flow<Resource<List<ProductEntity>>> {
        return localDataSource.getFavoriteProducts()
    }

    override suspend fun addToFavorite(productEntity: ProductEntity, favoriteState: Boolean): Flow<Resource<ProductEntity>>  {
        return localDataSource.addToFavorite(productEntity, favoriteState)
    }

    override suspend fun removeFavorite(productEntity: ProductEntity, favoriteState: Boolean): Flow<Resource<ProductEntity>> {
        return localDataSource.removeFavorite(productEntity, favoriteState)
    }
}