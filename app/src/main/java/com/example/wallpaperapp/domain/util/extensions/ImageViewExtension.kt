package com.example.wallpaperapp.domain.util.extensions

import android.content.Context
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide

fun AppCompatImageView.loadImage(url: String?) {
    Glide.with(this.context).load(url).into(this)
}

fun AppCompatImageView.loadAsBitmap(url: String) {
    Glide.with(this.context).asBitmap().load(url).into(this)
}
fun AppCompatImageView.setDrawableImage(context: Context, drawable: Int) {
    this.setImageDrawable(AppCompatResources.getDrawable(context, drawable))
}