package com.example.wallpaperapp.presentation.wallpapers_screen.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.wallpaperapp.databinding.WallpaperImageItemBinding
import com.example.wallpaperapp.domain.models.Photo
import com.example.wallpaperapp.domain.util.ItemDiffUtil
import com.example.wallpaperapp.domain.util.extensions.loadImage

class WallpapersAdapter : ListAdapter<Photo, WallpapersAdapter.WallpaperVH>(ItemDiffUtil<Photo>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WallpaperVH {
        return WallpaperVH(
            WallpaperImageItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: WallpaperVH, position: Int) {
        holder.onBind(getItem(position))
    }

    class WallpaperVH(private val binding: WallpaperImageItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(photo: Photo) {
            with(binding) {
                wallpaperImageView.loadImage(photo.src.portrait)
                wallpaperTitleTextView.text = photo.alt
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun submitList(list: List<Photo>?) {
        super.submitList(list)
        notifyDataSetChanged()
    }
}