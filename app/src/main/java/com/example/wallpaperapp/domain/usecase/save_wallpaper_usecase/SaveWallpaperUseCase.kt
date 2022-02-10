package com.example.wallpaperapp.domain.usecase.save_wallpaper_usecase

import com.example.wallpaperapp.domain.models.Photo

interface SaveWallpaperUseCase {
    suspend fun saveWallpaper(wallpaper: Photo, isImageSaved: (isSaved: Boolean) -> Unit)
}