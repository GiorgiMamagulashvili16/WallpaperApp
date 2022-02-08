package com.example.wallpaperapp.data.mappers

import com.example.wallpaperapp.data.models.SrcDto
import com.example.wallpaperapp.domain.models.Src

class SrcDtoMapper : Mapper<SrcDto, Src> {
    override fun mapModel(model: SrcDto): Src {
        with(model) {
            return Src(portrait)
        }
    }
}