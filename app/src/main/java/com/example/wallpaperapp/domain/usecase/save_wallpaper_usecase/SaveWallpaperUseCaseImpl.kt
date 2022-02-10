package com.example.wallpaperapp.domain.usecase.save_wallpaper_usecase

import com.example.wallpaperapp.domain.models.Photo
import com.example.wallpaperapp.domain.repository.SavedWallpaperRepository

class SaveWallpaperUseCaseImpl(private val savedWallpaperRepository: SavedWallpaperRepository) :
    SaveWallpaperUseCase {
    override suspend fun saveWallpaper(wallpaper: Photo, isImageSaved: (isSaved: Boolean) -> Unit) {
        return savedWallpaperRepository.saveWallpaper(wallpaper, isImageSaved)
    }
}