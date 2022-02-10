package com.example.wallpaperapp.data.mappers

import com.example.wallpaperapp.data.models.SrcEntity
import com.example.wallpaperapp.domain.models.Src

class SrcDomainMapper : Mapper<Src,SrcEntity> {
    override fun mapModel(model: Src): SrcEntity {
        return SrcEntity(model.portrait)
    }
}