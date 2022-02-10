package com.example.wallpaperapp.domain.repository

import com.example.wallpaperapp.domain.models.Photo

interface SavedWallpaperRepository {

    suspend fun saveWallpaper(wallpaper: Photo,isImageSaved : (isSaved:Boolean) -> Unit)

    suspend fun unSaveWallpaper(id: Int,isImageSaved : (isSaved:Boolean) -> Unit)

    suspend fun getSavedWallpapers(): List<Photo>

    suspend fun isWallpaperSaved(id:Int) :Boolean
}