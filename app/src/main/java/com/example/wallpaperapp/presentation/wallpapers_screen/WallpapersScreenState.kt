package com.example.wallpaperapp.presentation.wallpapers_screen

import com.example.wallpaperapp.data.models.PhotoDto
import com.example.wallpaperapp.domain.models.Photo

sealed class WallpapersScreenStates {
    object Idle : WallpapersScreenStates()
    data class Success(val data: List<Photo>) : WallpapersScreenStates()
    data class Error(val message: String?) : WallpapersScreenStates()
    object Loading:WallpapersScreenStates()
}
