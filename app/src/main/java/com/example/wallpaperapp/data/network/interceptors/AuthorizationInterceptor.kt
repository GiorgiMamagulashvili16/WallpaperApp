package com.example.wallpaperapp.data.network.interceptors

import com.example.wallpaperapp.domain.util.Constants.API_KEY
import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val newRequest = request.newBuilder()
            .addHeader("Authorization", API_KEY)
            .build()
        return chain.proceed(newRequest)
    }
}