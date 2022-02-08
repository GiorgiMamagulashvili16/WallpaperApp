package com.example.wallpaperapp.domain.models

data class Photo(
    val id: Int,
    val alt: String,
    val height: Int,
    val width: Int,
    val src: Src,
    val url: String,
)