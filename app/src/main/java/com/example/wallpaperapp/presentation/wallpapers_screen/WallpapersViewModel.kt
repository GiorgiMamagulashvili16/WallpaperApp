package com.example.wallpaperapp.presentation.wallpapers_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wallpaperapp.domain.models.Photo
import com.example.wallpaperapp.domain.usecase.GetWallPapersUseCase
import com.example.wallpaperapp.domain.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class WallpapersViewModel(
    private val getWallPapersUseCase: GetWallPapersUseCase
    ) : ViewModel() {
    private var page = 1

    private val wallpapersScreenStateFlow =
        MutableStateFlow<WallpapersScreenStates>(WallpapersScreenStates.Idle)
    val wallpapersScreenState: StateFlow<WallpapersScreenStates> =
        wallpapersScreenStateFlow.asStateFlow()

    private val currentWallpapers = mutableListOf<Photo>()

    fun getWallpapers() = viewModelScope.launch(Dispatchers.IO) {
        wallpapersScreenStateFlow.value = WallpapersScreenStates.Loading
        when (val response = getWallPapersUseCase.getWallPapers("i", page)) {
            is Resource.Success -> {
                currentWallpapers.addAll(response.data)
                wallpapersScreenStateFlow.emit(WallpapersScreenStates.Success(currentWallpapers.toList()))
                page++
            }
            is Resource.Error -> {
                wallpapersScreenStateFlow.emit(WallpapersScreenStates.Error(response.message))
            }
        }
    }

    fun resetStateFlow(){
        wallpapersScreenStateFlow.value = WallpapersScreenStates.Idle
    }

}