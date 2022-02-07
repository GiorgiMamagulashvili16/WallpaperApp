package com.example.wallpaperapp.domain.util

import retrofit2.Response

inline fun <D, T> fetchData(
    responseCall: () -> Response<D>,
    call: (body: D) -> Resource<T>
): Resource<T> {
    return try {
        val response = responseCall.invoke()
        if (response.isSuccessful) {
            response.body()?.let {
                call.invoke(it)
            } ?: Resource.Error(DATA_IS_NULL_MESSAGE)
        } else {
            Resource.Error(response.errorBody()?.string())
        }
    } catch (e: Exception) {
        Resource.Error(e.message)
    }
}

const val DATA_IS_NULL_MESSAGE = "data is null"