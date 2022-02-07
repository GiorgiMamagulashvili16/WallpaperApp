package com.example.wallpaperapp.presentation.wallpapers_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wallpaperapp.domain.repository.ImagesRepository
import com.example.wallpaperapp.domain.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class WallpapersViewModel(private val imagesRepository: ImagesRepository) : ViewModel() {

    private val wallpapersScreenStateFlow =
        MutableStateFlow<WallpapersScreenStates>(WallpapersScreenStates.Idle)
    val wallpapersScreenState: StateFlow<WallpapersScreenStates> =
        wallpapersScreenStateFlow.asStateFlow()

    init {
        getWallpapers()
    }
    fun getWallpapers() = viewModelScope.launch(Dispatchers.IO) {
        val response = imagesRepository.getImages("android", 1)
        when (response) {
            is Resource.Success -> {
                wallpapersScreenStateFlow.emit(WallpapersScreenStates.Success(response.data))
            }
            is Resource.Error -> {
                wallpapersScreenStateFlow.emit(WallpapersScreenStates.Error(response.message))
            }
        }
    }
}