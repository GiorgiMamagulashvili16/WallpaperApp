package com.example.wallpaperapp.domain.usecase.get_wallpapers

import com.example.wallpaperapp.domain.models.Photo
import com.example.wallpaperapp.domain.util.Resource

interface GetWallPapersUseCase {

    suspend fun getWallPapers(query: String, page: Int): Resource<List<Photo>>

}