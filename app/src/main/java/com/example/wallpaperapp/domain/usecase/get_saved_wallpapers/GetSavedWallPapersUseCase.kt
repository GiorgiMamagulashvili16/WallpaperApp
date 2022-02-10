package com.example.wallpaperapp.domain.usecase.get_saved_wallpapers

import com.example.wallpaperapp.domain.models.Photo

interface GetSavedWallPapersUseCase {

    suspend fun getWallPapers(): List<Photo>

}