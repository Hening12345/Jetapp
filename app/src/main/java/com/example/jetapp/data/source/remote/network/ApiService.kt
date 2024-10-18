package com.example.jetapp.data.source.remote.network

import com.example.jetapp.data.source.remote.response.ProductResponse
import com.example.jetapp.data.source.remote.response.ProductResponseItem
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("products")
    suspend fun getListProducts() : List<ProductResponseItem>

    @GET("products/{id}")
    suspend fun getDetailProducts(
        @Path("id") id: Int
    ) : ProductResponseItem
}