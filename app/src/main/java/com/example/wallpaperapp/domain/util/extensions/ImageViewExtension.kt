package com.example.wallpaperapp.domain.util.extensions

import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide

fun AppCompatImageView.loadImage(url: String) {
    Glide.with(this.context).load(url).into(this)
}