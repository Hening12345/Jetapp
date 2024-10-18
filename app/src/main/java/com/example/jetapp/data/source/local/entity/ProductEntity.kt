package com.example.jetapp.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class ProductEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "category")
    val category: String,

    @ColumnInfo(name = "image")
    val image: String?,

    @ColumnInfo(name = "price")
    val price: Double? = 0.0,

    @ColumnInfo(name = "favorite")
    var isFavorite: Boolean? = null
)