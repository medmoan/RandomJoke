package com.example.randomjoke.core.util

sealed class ApiResource<out T>() {
    object Loading: ApiResource<Nothing>()
    data class Success<out T>(val data: T?): ApiResource<T>()
    data class Failed(val message: String?): ApiResource<Nothing>()
}