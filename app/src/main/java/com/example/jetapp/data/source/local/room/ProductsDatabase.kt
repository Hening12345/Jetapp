package com.example.jetapp.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.jetapp.data.source.local.entity.ProductEntity

@Database(entities = [ProductEntity::class], version = 1, exportSchema = false)
abstract class ProductsDatabase : RoomDatabase() {
    abstract fun productsDao() : ProductsDao
}