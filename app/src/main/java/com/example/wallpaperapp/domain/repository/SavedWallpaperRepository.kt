package com.example.wallpaperapp.domain.repository

import com.example.wallpaperapp.domain.models.Photo

interface SavedWallpaperRepository {

    fun saveWallpaper(wallpaper:Photo)

    fun unSaveWallpaper(id:Int)

    fun getSavedWallpapers(): List<Photo>

}