package com.example.wallpaperapp.domain.usecase

import com.example.wallpaperapp.domain.models.Photo
import com.example.wallpaperapp.domain.repository.SavedWallpaperRepository

class GetSavedWallPapersUseCaseImpl(
    private val repository: SavedWallpaperRepository
): GetSavedWallPapersUseCase {

    override suspend fun getWallPapers(): List<Photo> {
        return repository.getSavedWallpapers()
    }

}