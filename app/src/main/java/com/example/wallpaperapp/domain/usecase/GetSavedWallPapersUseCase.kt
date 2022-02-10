package com.example.wallpaperapp.domain.usecase

import com.example.wallpaperapp.domain.models.Photo

interface GetSavedWallPapersUseCase {

    suspend fun getWallPapers(): List<Photo>

}