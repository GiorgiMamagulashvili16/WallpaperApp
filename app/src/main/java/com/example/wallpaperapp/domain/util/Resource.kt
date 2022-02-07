package com.example.wallpaperapp.domain.util

sealed class Resource<out T> {

    data class Success<T>(val data: T, val message: String? = null) : Resource<T>()

    data class Error<T>(val message: String?) : Resource<T>()

}