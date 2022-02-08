package com.example.wallpaperapp.data.network.api

import com.example.wallpaperapp.data.network.interceptors.AuthorizationInterceptor
import com.example.wallpaperapp.data.network.interceptors.NetworkConnectionInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
    Retrofit.Builder().baseUrl(BASE_URL).client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create()).build()

fun provideHttpClient(
    networkConnectionException: NetworkConnectionInterceptor,
    authorizationInterceptor: AuthorizationInterceptor
): OkHttpClient = OkHttpClient().newBuilder().addInterceptor(networkConnectionException)
    .addInterceptor(authorizationInterceptor).build()

fun provideImageApi(retrofit: Retrofit): WallpapersApiService =
    retrofit.create(WallpapersApiService::class.java)

private const val BASE_URL = "https://api.pexels.com/v1/"