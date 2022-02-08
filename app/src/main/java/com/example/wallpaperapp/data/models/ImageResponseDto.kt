package com.example.wallpaperapp.data.models

import com.google.gson.annotations.SerializedName

data class ImageResponseDto(
    @SerializedName("next_page")
    val nextPage: String,
    val page: Int,
    @SerializedName("per_page")
    val perPage: Int,
    val photos: List<PhotoDto>,
    @SerializedName("total_results")
    val totalResults: Int
)