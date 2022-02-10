package com.example.wallpaperapp.domain.usecase.is_image_saved_usecase

import com.example.wallpaperapp.domain.repository.SavedWallpaperRepository

class IsImageSavedUseCaseImpl(private val savedWallpaperRepository: SavedWallpaperRepository) :
    IsImageSavedUseCase {
    override suspend fun isImageSaved(id: Int): Boolean {
        return savedWallpaperRepository.isWallpaperSaved(id)
    }
}