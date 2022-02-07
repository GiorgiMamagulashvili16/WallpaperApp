package com.example.wallpaperapp.data.models

import com.google.gson.annotations.SerializedName

data class PhotoDto(
    val alt: String,
    @SerializedName("avg_color")
    val avgColor: String,
    val height: Int,
    val id: Int,
    val liked: Boolean,
    val photographer: String,
    @SerializedName("photographer_id")
    val photographerId: Int,
    @SerializedName("photographer_url")
    val photographerUrl: String,
    val srcDto: SrcDto,
    val url: String,
    val width: Int
)