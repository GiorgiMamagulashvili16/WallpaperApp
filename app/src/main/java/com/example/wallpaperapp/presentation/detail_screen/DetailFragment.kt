package com.example.wallpaperapp.presentation.detail_screen

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.navigation.fragment.navArgs
import com.example.wallpaperapp.R
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
        viewModel.checkIfWallpaperIsOnLockScreen(wallPaper.id)
        viewModel.checkIfWallpaperIsOnHomeScreen(wallPaper.id)
        observeIfWallpaperIsOnLockScreen(viewModel)
        observeIfWallpaperIsOnHomeScreen(viewModel)
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
            }
            lockScreenButton.setOnClickListener {
                launchLifecycle {
                    vM.setImageAtLockScreen(
                        args.wallpaper.src.portrait.getAsBitmap(requireContext(), 800, 1200),
                        wallPaper.id
                    )
                }
                vM.checkIfWallpaperIsOnLockScreen(wallPaper.id)
                playLottieAnimation()
            }
            homeButton.setOnClickListener {
                launchLifecycle {
                    vM.setImageAtHomeScreen(
                        args.wallpaper.src.portrait.getAsBitmap(requireContext(), 800, 1200),
                        wallPaper.id
                    )
                }
                vM.checkIfWallpaperIsOnHomeScreen(wallPaper.id)
                playLottieAnimation()

            }
        }
    }

    @SuppressLint("ResourceType")
    private fun observeIfWallpaperIsOnLockScreen(viewModel: DetailViewModel) {
        viewModel.isWallpaperOnLockScreen.observe(viewLifecycleOwner) {
            binding.lockScreenButton.setColorFilter(
                if (it) Color.parseColor(requireContext().getString(R.color.button_color_blue)) else Color.WHITE
            )
        }
    }

    @SuppressLint("ResourceType")
    private fun observeIfWallpaperIsOnHomeScreen(viewModel: DetailViewModel) {
        viewModel.isWallpaperOnHomeScreen.observe(viewLifecycleOwner) {
            binding.homeButton.setColorFilter(if (it) Color.parseColor(requireContext().getString(R.color.button_color_blue)) else Color.WHITE)
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