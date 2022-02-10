package com.example.wallpaperapp.data.db

import android.content.Context
import androidx.room.Room

fun provideRoomInstance(context: Context): WallpapersDataBase =
    Room.databaseBuilder(context, WallpapersDataBase::class.java, "wallpaperDB").build()

fun provideWallpaperDao(wallpapersDataBase: WallpapersDataBase): WallpapersDao =
    wallpapersDataBase.getDao()