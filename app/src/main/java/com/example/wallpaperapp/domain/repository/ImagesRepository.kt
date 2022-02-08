package com.example.wallpaperapp.domain.repository

import com.example.wallpaperapp.domain.models.Photo
import com.example.wallpaperapp.domain.util.Resource

interface ImagesRepository {
    suspend fun getImages(query: String, page: Int): Resource<List<Photo>>
}