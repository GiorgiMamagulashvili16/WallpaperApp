package com.example.wallpaperapp.data.mappers

import com.example.wallpaperapp.data.models.PhotoDto
import com.example.wallpaperapp.domain.models.Photo

class PhotoDtoMapper(private val srcDtoMapper: SrcDtoMapper) : Mapper<PhotoDto, Photo> {
    override fun mapModel(model: PhotoDto): Photo {
        with(model) {
            return Photo(id, alt, srcDtoMapper.mapModel(src))
        }
    }
}