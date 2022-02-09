package com.example.wallpaperapp.data.mappers

import com.example.wallpaperapp.data.models.SrcEntity
import com.example.wallpaperapp.domain.models.Src

class SrcEntityMapper : Mapper<SrcEntity,Src>{
    override fun mapModel(model: SrcEntity): Src {
        return Src(model.portrait)
    }
}