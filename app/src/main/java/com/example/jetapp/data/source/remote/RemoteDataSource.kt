package com.example.jetapp.data.source.remote

import android.util.Log
import com.example.jetapp.data.source.remote.network.ApiResponse
import com.example.jetapp.data.source.remote.network.ApiService
import com.example.jetapp.data.source.remote.response.ProductResponseItem
import com.example.jetapp.domain.model.ProductItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {
    suspend fun getListProduct() : Flow<ApiResponse<List<ProductResponseItem>>>  = flow {
        try {
            val response = apiService.getListProducts()
            Log.d("ListProduct", "data: $response")
            emit(ApiResponse.Success(response))
        } catch (e: Exception) {
            Log.d("ListProduct", "error: ${e.message.toString()}")
            emit(ApiResponse.Error(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)

    suspend fun getDetailProducts(id: Int): Flow<ApiResponse<ProductResponseItem>> = flow {
        try {
            val response = apiService.getDetailProducts(id)
            Log.d("DetailProduct", "detail: $response")
            emit(ApiResponse.Success(response))
        } catch (e: Exception) {
            Log.d("DetailProduct", "error: ${e.message.toString()}")
            emit(ApiResponse.Error(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)
}