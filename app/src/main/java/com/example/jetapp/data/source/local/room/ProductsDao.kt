package com.example.jetapp.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.jetapp.data.source.local.entity.ProductEntity

@Dao
interface ProductsDao {
    @Query("SELECT * FROM products")
    fun getProducts() : LiveData<List<ProductEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertProducts(products: ProductEntity)

    @Delete
    suspend fun deleteProducts(products: ProductEntity)

    @Query("SELECT * FROM products where favorite = 1")
    fun getFavoriteProducts() : LiveData<List<ProductEntity>>
}