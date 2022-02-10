package com.example.wallpaperapp.presentation.wallpapers_screen.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.wallpaperapp.databinding.CategoryItemBinding
import com.example.wallpaperapp.domain.models.Category
import com.example.wallpaperapp.domain.util.ItemDiffUtil
import com.example.wallpaperapp.domain.util.extensions.loadImage

class CategoryAdapter :
    ListAdapter<Category, CategoryAdapter.CategoryVH>(ItemDiffUtil<Category>()) {

    var onClick: ((String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryVH {
        return CategoryVH(
            CategoryItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CategoryVH, position: Int) {
        holder.onBind(getItem(position), onClick)
    }

    class CategoryVH(private val binding: CategoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(category: Category, onClick: ((String) -> Unit)?) {
            with(binding) {
                backgroundImageView.loadImage(category.backgroundImageUrl)
                categoryTextView.text = category.categoryName

                root.setOnClickListener {
                    onClick?.invoke(category.categoryName)
                }
            }
        }
    }
}