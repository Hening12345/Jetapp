package com.example.jetapp.ui.screen.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetapp.data.source.Resource
import com.example.jetapp.data.source.local.entity.ProductEntity
import com.example.jetapp.domain.model.ProductItem
import com.example.jetapp.domain.usecase.MainUseCase
import kotlinx.coroutines.launch

class MainViewModel(private val mainUseCase: MainUseCase) : ViewModel() {
    
    private val _list = MutableLiveData<Resource<List<ProductItem>>>()

    val list: LiveData<Resource<List<ProductItem>>> get() = _list

    private val _detail = MutableLiveData<Resource<ProductItem>>()

    val detail: LiveData<Resource<ProductItem>> get() = _detail

    private val _favorite = MutableLiveData<Resource<List<ProductEntity>>>()

    val favorite: LiveData<Resource<List<ProductEntity>>> get() = _favorite


    fun getListProduct() {
        _list.value = Resource.Loading(true)
        viewModelScope.launch {
            mainUseCase.getListProduct().collect {
                _list.value = it
            }
        }
    }

    fun getDetailProduct(id: Int) {
        _detail.value = Resource.Loading(true)
        viewModelScope.launch {
            mainUseCase.getDetailProduct(id).collect {
                _detail.value = it
            }
        }
    }

    fun getFavoriteProducts() {
       _favorite.value = Resource.Loading(true)
        viewModelScope.launch {
            mainUseCase.getFavoriteProducts().collect {
                _favorite.value = it
            }
        }
    }

    fun addToFavorite(productEntity: ProductEntity, isFavorite: Boolean) {
        viewModelScope.launch {
            mainUseCase.addToFavorite(productEntity, isFavorite).collect {
                Log.d("MainViewModel", "data: $it")
            }
        }
    }

    fun removeFavorite(productEntity: ProductEntity, isFavorite: Boolean) {
        viewModelScope.launch {
            mainUseCase.removeFavorite(productEntity, isFavorite).collect {
                getFavoriteProducts()
            }
        }
    }

}