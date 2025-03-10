package com.example.jetapp.data.source

sealed class Resource<out T> {
    data class Success<out T>(val data: T) : Resource<T>()
    data class Error<out T>(val message: String) : Resource<T>()
    data class Loading<out T>(val loading: Boolean) : Resource<T>()
}