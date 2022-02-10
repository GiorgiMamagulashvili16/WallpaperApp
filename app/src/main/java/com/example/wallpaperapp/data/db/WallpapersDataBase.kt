package com.example.wallpaperapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.wallpaperapp.data.models.PhotoEntity


@Database(entities = [PhotoEntity::class],version = 1)
abstract class WallpapersDataBase : RoomDatabase() {

    abstract fun getDao() : WallpapersDao

}