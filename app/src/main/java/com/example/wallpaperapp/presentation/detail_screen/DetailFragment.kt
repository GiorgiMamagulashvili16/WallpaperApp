package com.example.wallpaperapp.presentation.detail_screen

import androidx.navigation.fragment.navArgs
import com.example.wallpaperapp.databinding.DetailFragmentBinding
import com.example.wallpaperapp.domain.util.extensions.loadImage
import com.example.wallpaperapp.presentation.base.BaseFragment
import com.example.wallpaperapp.presentation.base.Inflate
import kotlin.reflect.KClass

class DetailFragment : BaseFragment<DetailFragmentBinding, DetailViewModel>() {
    override val viewModelClass: KClass<DetailViewModel>
        get() = DetailViewModel::class

    override fun inflateFragment(): Inflate<DetailFragmentBinding> = DetailFragmentBinding::inflate


    private val args: DetailFragmentArgs by navArgs()

    override fun onBindViewModel(viewModel: DetailViewModel) {
        setInfo()
    }

    private fun setInfo() {
        with(binding) {
            wallpaperDetailImageView.loadImage(args.wallpaper.src.portrait)
            wallpaperDetailTitleTextView.text = args.wallpaper.alt
        }
    }
}