package com.example.wallpaperapp.data.mappers

import com.example.wallpaperapp.data.models.PhotoEntity
import com.example.wallpaperapp.domain.models.Photo

class PhotoDomainMapper(private val srcDomainMapper: SrcDomainMapper) : Mapper<Photo, PhotoEntity> {
    override fun mapModel(model: Photo): PhotoEntity {
        return PhotoEntity(model.id,model.alt,srcDomainMapper.mapModel(model.src))
    }
}