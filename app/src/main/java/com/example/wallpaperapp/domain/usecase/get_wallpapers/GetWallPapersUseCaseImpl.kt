package com.example.wallpaperapp.domain.usecase.get_wallpapers

import com.example.wallpaperapp.domain.models.Photo
import com.example.wallpaperapp.domain.repository.ImagesRepository
import com.example.wallpaperapp.domain.util.Resource

class GetWallPapersUseCaseImpl(private val imagesRepository: ImagesRepository):
    GetWallPapersUseCase {

    override suspend fun getWallPapers(query: String, page: Int): Resource<List<Photo>> {
        return imagesRepository.getImages(query, page)
    }

}