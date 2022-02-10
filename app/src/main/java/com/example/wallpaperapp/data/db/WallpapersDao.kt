package com.example.wallpaperapp.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.wallpaperapp.data.models.PhotoEntity

@Dao
interface WallpapersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveWallpaper(wallpaper: PhotoEntity)

    @Query("DELETE FROM wallpaper WHERE id=:id")
    fun unSaveWallpaper(id: Int)

    @Query("SELECT * FROM wallpaper ORDER BY id DESC")
    fun getSavedWallpapers(): List<PhotoEntity>

    @Query("SELECT EXISTS(SELECT * FROM wallpaper WHERE id = :id)")
    fun isImageSaved(id: Int): Boolean
}