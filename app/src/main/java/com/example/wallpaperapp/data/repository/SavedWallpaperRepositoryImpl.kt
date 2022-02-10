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
    override suspend fun saveWallpaper(wallpaper: Photo, isImageSaved: (isSaved: Boolean) -> Unit) {
        wallpapersDao.saveWallpaper(photoDomainMapper.mapModel(wallpaper))
        isImageSaved.invoke(wallpapersDao.isImageSaved(wallpaper.id))
    }

    override suspend fun unSaveWallpaper(id: Int, isImageSaved: (isSaved: Boolean) -> Unit) {
        wallpapersDao.unSaveWallpaper(id)
        isImageSaved.invoke(wallpapersDao.isImageSaved(id))
    }

    override suspend fun getSavedWallpapers(): List<Photo> {
        return photoEntityMapper.mapToList(wallpapersDao.getSavedWallpapers())
    }

    override suspend fun isWallpaperSaved(id: Int): Boolean {
        return wallpapersDao.isImageSaved(id)
    }
}