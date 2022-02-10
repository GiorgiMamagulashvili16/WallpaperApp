package com.example.wallpaperapp.presentation.favorites_screen

import com.example.wallpaperapp.domain.models.Photo

sealed class FavoritesScreenState {
    object Idle : FavoritesScreenState()
    data class Success(val data: List<Photo>) : FavoritesScreenState()
    data class Error(val message: String?) : FavoritesScreenState()
    object Loading : FavoritesScreenState()
}
