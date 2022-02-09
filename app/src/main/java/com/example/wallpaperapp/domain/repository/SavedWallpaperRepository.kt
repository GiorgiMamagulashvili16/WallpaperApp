package com.example.wallpaperapp.domain.repository

import com.example.wallpaperapp.domain.models.Photo

interface SavedWallpaperRepository {

    suspend fun saveWallpaper(wallpaper: Photo)

    suspend fun unSaveWallpaper(id: Int)

    suspend fun getSavedWallpapers(): List<Photo>

}