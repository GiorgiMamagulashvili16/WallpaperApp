package com.example.wallpaperapp.domain.util.extensions

import android.content.Context
import android.graphics.Bitmap
import com.bumptech.glide.Glide

fun String.getAsBitmap(context: Context, width: Int, height: Int): Bitmap {
    return Glide.with(context).asBitmap().load(this).submit(width, height).get()
}