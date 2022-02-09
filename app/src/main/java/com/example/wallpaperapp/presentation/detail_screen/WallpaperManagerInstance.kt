package com.example.wallpaperapp.presentation.detail_screen

import android.app.WallpaperManager
import android.content.Context

fun provideWallpaperInstance(ctx:Context) : WallpaperManager = WallpaperManager.getInstance(ctx)