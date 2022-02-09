package com.example.wallpaperapp.data.mappers

import com.example.wallpaperapp.data.models.PhotoEntity
import com.example.wallpaperapp.domain.models.Photo


class PhotoEntityMapper(private val srcMapper: SrcEntityMapper) : Mapper<PhotoEntity, Photo> {
    override fun mapModel(model: PhotoEntity): Photo {
        return Photo(model.id, model.alt, srcMapper.mapModel(model.src))
    }
}