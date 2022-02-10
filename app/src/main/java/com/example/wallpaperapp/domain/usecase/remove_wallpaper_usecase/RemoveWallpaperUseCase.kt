package com.example.wallpaperapp.domain.usecase.remove_wallpaper_usecase

interface RemoveWallpaperUseCase {
    suspend fun removeWallpaper(id: Int, isImageSaved: (isSaved: Boolean) -> Unit)
}