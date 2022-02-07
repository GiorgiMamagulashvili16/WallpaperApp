package com.example.wallpaperapp.data.repository

import com.example.wallpaperapp.data.mappers.PhotoDtoMapper
import com.example.wallpaperapp.data.network.api.WallpapersApiService
import com.example.wallpaperapp.domain.models.Photo
import com.example.wallpaperapp.domain.repository.ImagesRepository
import com.example.wallpaperapp.domain.util.Resource
import com.example.wallpaperapp.domain.util.fetchData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ImageRepositoryImpl(
    private val wallpapersApiService: WallpapersApiService,
    private val photoDtoMapper: PhotoDtoMapper
) :
    ImagesRepository {
    override suspend fun getImages(query: String, page: Int): Resource<List<Photo>> =
        withContext(Dispatchers.IO) {
            return@withContext fetchData(responseCall = {
                wallpapersApiService.getPhotos(
                    query,
                    page
                )
            }, call = {
                val result = photoDtoMapper.mapToList(it.photoDtos)
                Resource.Success(result)
            })
        }
}