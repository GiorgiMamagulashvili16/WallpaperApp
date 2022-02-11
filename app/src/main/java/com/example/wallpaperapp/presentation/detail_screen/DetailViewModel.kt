package com.example.wallpaperapp.presentation.detail_screen

import android.app.WallpaperManager
import android.graphics.Bitmap
import android.os.Build
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wallpaperapp.data.datastore.WallpaperDatastore
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
    private val isImageSavedUseCase: IsImageSavedUseCase,
    private val datastore: WallpaperDatastore
) : ViewModel() {

    private val isWallpaperSavedLiveData = MutableLiveData<Boolean>()
    val isWallpaperSaved: LiveData<Boolean> = isWallpaperSavedLiveData

    private val isWallpaperOnLockScreenLiveData = MutableLiveData<Boolean>()
    val isWallpaperOnLockScreen: LiveData<Boolean> = isWallpaperOnLockScreenLiveData

    private val isWallpaperOnHomeScreenLiveData = MutableLiveData<Boolean>()
    val isWallpaperOnHomeScreen: LiveData<Boolean> = isWallpaperOnHomeScreenLiveData

    fun checkIfWallpaperIsOnLockScreen(id: Int) = viewModelScope.launch(Dispatchers.IO) {
        isWallpaperOnLockScreenLiveData.postValue(
            datastore.getIntPreference(
                LOCK_SCREEN_KEY
            ) == id
        )
    }

    fun checkIfWallpaperIsOnHomeScreen(id: Int) = viewModelScope.launch {
        isWallpaperOnHomeScreenLiveData.postValue(
            datastore.getIntPreference(
                SYSTEM_SCREEN_KEY
            ) == id
        )
    }

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

    fun setImageAtLockScreen(bitmap: Bitmap, id: Int) = viewModelScope.launch(Dispatchers.IO) {
        datastore.setIntPreference(LOCK_SCREEN_KEY, id)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            wallpaperManager.setBitmap(bitmap, null, true, WallpaperManager.FLAG_LOCK)
        } else setImageAtBothScreen(bitmap)
        isWallpaperOnLockScreenLiveData.postValue(true)

    }

    fun setImageAtHomeScreen(bitmap: Bitmap, id: Int) = viewModelScope.launch(Dispatchers.IO) {
        datastore.setIntPreference(SYSTEM_SCREEN_KEY, id)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            wallpaperManager.setBitmap(bitmap, null, true, WallpaperManager.FLAG_SYSTEM)
        } else setImageAtBothScreen(bitmap)
        isWallpaperOnHomeScreenLiveData.postValue(true)
    }

    private fun setImageAtBothScreen(bitmap: Bitmap) {
        wallpaperManager.setBitmap(bitmap)
    }

    companion object {
        private const val LOCK_SCREEN_KEY = "lock_screen_key"
        private const val SYSTEM_SCREEN_KEY = "system_screen_key"
    }
}