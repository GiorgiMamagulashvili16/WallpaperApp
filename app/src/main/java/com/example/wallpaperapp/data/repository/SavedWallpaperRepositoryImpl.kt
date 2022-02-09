package com.example.wallpaperapp.data.repository

import com.example.wallpaperapp.data.db.WallpapersDao
import com.example.wallpaperapp.data.mappers.PhotoDomainMapper
import com.example.wallpaperapp.data.mappers.PhotoEntityMapper
import com.example.wallpaperapp.domain.models.Photo
import com.example.wallpaperapp.domain.repository.SavedWallpaperRepository

class SavedWallpaperRepositoryImpl(
    private val wallpapersDao: WallpapersDao,
    private val photoEntityMapper: PhotoEntityMapper,
    private val photoDomainMapper: PhotoDomainMapper
) :
    SavedWallpaperRepository {
    override suspend fun saveWallpaper(wallpaper: Photo) {
        wallpapersDao.saveWallpaper(photoDomainMapper.mapModel(wallpaper))
    }

    override suspend fun unSaveWallpaper(id: Int) {
        wallpapersDao.unSaveWallpaper(id)
    }

    override suspend fun getSavedWallpapers(): List<Photo> {
        return photoEntityMapper.mapToList(wallpapersDao.getSavedWallpapers())
    }
}