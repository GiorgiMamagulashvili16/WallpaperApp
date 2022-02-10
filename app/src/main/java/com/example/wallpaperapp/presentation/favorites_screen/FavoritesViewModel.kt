package com.example.wallpaperapp.presentation.favorites_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wallpaperapp.domain.usecase.GetSavedWallPapersUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.lang.Exception

class FavoritesViewModel(
    private val getSavedWallPapersUseCase: GetSavedWallPapersUseCase
): ViewModel() {

    init {
        getWallPapers()
    }

    private val wallpapersScreenStateFlow =
        MutableStateFlow<FavoritesScreenState>(FavoritesScreenState.Idle)
    val wallpapersScreenState: StateFlow<FavoritesScreenState> =
        wallpapersScreenStateFlow.asStateFlow()

    private fun getWallPapers() = viewModelScope.launch(Dispatchers.IO) {
        try {
            val data = getSavedWallPapersUseCase.getWallPapers()
            wallpapersScreenStateFlow.emit(FavoritesScreenState.Success(data))
        }catch (e: Exception){
            wallpapersScreenStateFlow.emit(FavoritesScreenState.Error(e.message))
        }
    }

}