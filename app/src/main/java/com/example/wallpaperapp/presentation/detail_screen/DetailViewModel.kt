package com.example.wallpaperapp.presentation.detail_screen

import android.app.WallpaperManager
import android.graphics.Bitmap
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wallpaperapp.BuildConfig
import com.example.wallpaperapp.domain.models.Photo
import com.example.wallpaperapp.domain.usecase.is_image_saved_usecase.IsImageSavedUseCase
import com.example.wallpaperapp.domain.usecase.remove_wallpaper_usecase.RemoveWallpaperUseCase
import com.example.wallpaperapp.domain.usecase.save_wallpaper_usecase.SaveWallpaperUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailViewModel(
    private val saveWallpaperUseCase: SaveWallpaperUseCase,
    private val wallpaperManager: WallpaperManager,
    private val removeWallpaperUseCase: RemoveWallpaperUseCase,
    private val isImageSavedUseCase: IsImageSavedUseCase
) :
    ViewModel() {
    private val isWallpaperSavedLiveData = MutableLiveData<Boolean>()
    val isWallpaperSaved: LiveData<Boolean> = isWallpaperSavedLiveData

    fun saveWallpaper(wallpaper: Photo) {
        viewModelScope.launch(Dispatchers.IO) {
            saveWallpaperUseCase.saveWallpaper(wallpaper) {
                isWallpaperSavedLiveData.postValue(it)
            }
        }
    }

    fun removeWallpaper(id: Int) = viewModelScope.launch(Dispatchers.IO) {
        removeWallpaperUseCase.removeWallpaper(id) {
            isWallpaperSavedLiveData.postValue(it)
        }
    }

    fun isWallpaperSaved(id: Int) = viewModelScope.launch(Dispatchers.IO) {
        isWallpaperSavedLiveData.postValue(isImageSavedUseCase.isImageSaved(id))
    }

    fun setImageAtLockScreen(bitmap: Bitmap) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            wallpaperManager.setBitmap(bitmap, null, true, WallpaperManager.FLAG_LOCK)
        }else setImageAtBothScreen(bitmap)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun setImageAtHomeScreen(bitmap: Bitmap) {

    fun setImageAtHomeScreen(bitmap: Bitmap,) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            wallpaperManager.setBitmap(bitmap, null, true, WallpaperManager.FLAG_SYSTEM)
        }else setImageAtBothScreen(bitmap)
    }

    fun setImageAtBothScreen(bitmap: Bitmap){
        wallpaperManager.setBitmap(bitmap)
    }
}