package com.example.wallpaperapp.di

import com.example.wallpaperapp.data.network.api.provideHttpClient
import com.example.wallpaperapp.data.network.api.provideRetrofit
import com.example.wallpaperapp.data.network.interceptors.AuthorizationInterceptor
import com.example.wallpaperapp.data.network.interceptors.NetworkConnectionInterceptor
import org.koin.dsl.module

val networkModule = module {
    single { AuthorizationInterceptor() }
    single { NetworkConnectionInterceptor() }
    single { provideHttpClient(get(), get()) }
    single { provideRetrofit(get()) }
}