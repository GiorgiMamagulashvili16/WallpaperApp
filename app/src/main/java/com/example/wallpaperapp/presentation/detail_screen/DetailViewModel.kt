package com.example.wallpaperapp.presentation.detail_screen

import android.app.WallpaperManager
import android.content.Context
import android.graphics.Bitmap
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel

class DetailViewModel : ViewModel() {
    @RequiresApi(Build.VERSION_CODES.N)
    fun setImageAtLockScreen(bitmap: Bitmap, context: Context){
        val wallpapersFragment = WallpaperManager.getInstance(context)
        wallpapersFragment.setBitmap(bitmap,null, true, WallpaperManager.FLAG_LOCK)
    }
    fun setImageAtHomeScreen(bitmap: Bitmap, context: Context){
        val wallpapersFragment = WallpaperManager.getInstance(context)
        wallpapersFragment.setBitmap(bitmap)
    }
}