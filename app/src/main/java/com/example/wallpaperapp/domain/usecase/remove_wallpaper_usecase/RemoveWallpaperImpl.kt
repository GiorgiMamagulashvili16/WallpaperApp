package com.example.wallpaperapp.domain.usecase.remove_wallpaper_usecase

import com.example.wallpaperapp.domain.repository.SavedWallpaperRepository

class RemoveWallpaperImpl(private val savedWallpaperRepository: SavedWallpaperRepository) :
    RemoveWallpaperUseCase {
    override suspend fun removeWallpaper(id: Int, isImageSaved: (isSaved: Boolean) -> Unit) {
        return savedWallpaperRepository.unSaveWallpaper(id, isImageSaved)
    }
}