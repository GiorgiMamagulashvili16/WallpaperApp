package com.example.wallpaperapp.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Photo(
    val id: Int,
    val alt: String,
    val src: Src,
):Parcelable