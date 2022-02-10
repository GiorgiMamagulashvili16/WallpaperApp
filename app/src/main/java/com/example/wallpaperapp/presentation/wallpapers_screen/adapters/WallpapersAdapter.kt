package com.example.wallpaperapp.presentation.wallpapers_screen.adapters

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wallpaperapp.databinding.WallpaperImageItemBinding
import com.example.wallpaperapp.domain.models.Photo
import com.example.wallpaperapp.domain.util.ItemDiffUtil
import com.example.wallpaperapp.domain.util.extensions.loadImage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

typealias onImageClick = (image: String) -> Unit

class WallpapersAdapter : ListAdapter<Photo, WallpapersAdapter.WallpaperVH>(ItemDiffUtil<Photo>()) {

     lateinit var onItemClick: (wallPaper: Photo) -> Unit

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
        holder.onBind(getItem(position), onItemClick)
    }

    class WallpaperVH(private val binding: WallpaperImageItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(photo: Photo, onItemClick: (wallPaper: Photo) -> Unit) {
            with(binding) {
                wallpaperImageView.loadImage(photo.src.portrait)
                wallpaperTitleTextView.text = photo.alt
                root.setOnClickListener {
                    onItemClick.invoke(photo)
                }
            }
        }
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun submitList(list: List<Photo>?) {
        super.submitList(list)
        notifyDataSetChanged()
    }
}