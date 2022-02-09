package com.example.wallpaperapp.presentation.detail_screen

import android.app.WallpaperManager
import android.content.Context
import android.graphics.Bitmap
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wallpaperapp.domain.models.Photo
import com.example.wallpaperapp.domain.repository.SavedWallpaperRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailViewModel(private val savedWallpaperRepository: SavedWallpaperRepository) :
    ViewModel() {

    fun saveWallpaper(wallpaper: Photo) {
        viewModelScope.launch(Dispatchers.IO) {
            savedWallpaperRepository.saveWallpaper(wallpaper)
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun setImageAtLockScreen(bitmap: Bitmap, context: Context) {
        val wallpapersFragment = WallpaperManager.getInstance(context)
        wallpapersFragment.setBitmap(bitmap, null, true, WallpaperManager.FLAG_LOCK)
    }

    fun setImageAtHomeScreen(bitmap: Bitmap, context: Context) {
        val wallpapersFragment = WallpaperManager.getInstance(context)
        wallpapersFragment.setBitmap(bitmap)
    }
}