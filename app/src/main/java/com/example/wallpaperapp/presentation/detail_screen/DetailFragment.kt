package com.example.wallpaperapp.presentation.detail_screen

import android.graphics.Color
import androidx.navigation.fragment.navArgs
import com.example.wallpaperapp.databinding.DetailFragmentBinding
import com.example.wallpaperapp.domain.models.Photo
import com.example.wallpaperapp.domain.util.extensions.getAsBitmap
import com.example.wallpaperapp.domain.util.extensions.launchLifecycle
import com.example.wallpaperapp.domain.util.extensions.loadImage
import com.example.wallpaperapp.presentation.base.BaseFragment
import com.example.wallpaperapp.presentation.base.Inflate
import kotlin.reflect.KClass

class DetailFragment : BaseFragment<DetailFragmentBinding, DetailViewModel>() {
    override val viewModelClass: KClass<DetailViewModel>
        get() = DetailViewModel::class

    override fun inflateFragment(): Inflate<DetailFragmentBinding> = DetailFragmentBinding::inflate


    private val args: DetailFragmentArgs by navArgs()
    private lateinit var wallPaper: Photo

    override fun onBindViewModel(viewModel: DetailViewModel) {
        wallPaper = args.wallpaper
        viewModel.isWallpaperSaved(wallPaper.id)
        setInfo()
        setButtonClickListeners(viewModel)
        observeIsSavedWallpaper(viewModel)
    }

    private fun setInfo() {
        with(binding) {
            wallpaperDetailImageView.loadImage(wallPaper.src.portrait)
        }
    }

    private fun playLottieAnimation() {
        binding.lotieAnim.playAnimation()
    }

    private fun setButtonClickListeners(vM: DetailViewModel) {
        with(binding) {
            favoritesButton.setOnClickListener {
                if (vM.isWallpaperSaved.value == true)
                    vM.removeWallpaper(args.wallpaper.id)
                else
                    vM.saveWallpaper(args.wallpaper)
                vM.saveWallpaper(args.wallpaper)
                playLottieAnimation()
            }
            lockScreenButton.setOnClickListener {
                launchLifecycle {
                    vM.setImageAtLockScreen(
                        args.wallpaper.src.portrait.getAsBitmap(requireContext(), 800, 1200)
                    )
                }
                playLottieAnimation()
            }
            homeButton.setOnClickListener {
                launchLifecycle {
                    vM.setImageAtHomeScreen(
                        args.wallpaper.src.portrait.getAsBitmap(requireContext(), 800, 1200)
                    )
                }
                playLottieAnimation()
            }
        }
    }

    private fun observeIsSavedWallpaper(viewModel: DetailViewModel) {
        viewModel.isWallpaperSaved.observe(viewLifecycleOwner, {
            with(binding.favoritesButton) {
                setColorFilter(if (it) Color.RED else Color.WHITE)
            }
        })
    }
}