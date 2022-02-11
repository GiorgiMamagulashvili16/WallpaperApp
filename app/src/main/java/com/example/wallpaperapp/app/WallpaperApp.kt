package com.example.wallpaperapp.app

import android.app.Application
import com.example.wallpaperapp.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class WallpaperApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@WallpaperApp)
            modules(
                listOf(
                    networkModule, wallpapersScreenModule, mapperModule, detailsScreenModule,
                    roomModule, favoritesScreenModule, datastoreModule
                )
            )
        }
    }
}