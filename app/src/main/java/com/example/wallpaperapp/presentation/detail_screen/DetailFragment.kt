package com.example.wallpaperapp.presentation.detail_screen

import android.graphics.Color
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.navArgs
import com.example.wallpaperapp.databinding.DetailFragmentBinding
import com.example.wallpaperapp.domain.util.extensions.getAsBitmap
import com.example.wallpaperapp.domain.util.extensions.launchLifecycle
import com.example.wallpaperapp.domain.util.extensions.loadImage
import com.example.wallpaperapp.presentation.base.BaseFragment
import com.example.wallpaperapp.presentation.base.Inflate
import kotlin.reflect.KClass

@RequiresApi(Build.VERSION_CODES.N)
class DetailFragment : BaseFragment<DetailFragmentBinding, DetailViewModel>() {
    override val viewModelClass: KClass<DetailViewModel>
        get() = DetailViewModel::class

    override fun inflateFragment(): Inflate<DetailFragmentBinding> = DetailFragmentBinding::inflate


    private val args: DetailFragmentArgs by navArgs()

    override fun onBindViewModel(viewModel: DetailViewModel) {
        setInfo()
        setButtonClickListeners(viewModel)
        observeIsSavedWallpaper(viewModel)
        viewModel.isWallpaperSaved(args.wallpaper.id)
    }

    private fun setInfo() {
        with(binding) {
            wallpaperDetailImageView.loadImage(args.wallpaper.src.portrait)
        }
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
                        args.wallpaper.src.portrait.getAsBitmap(requireContext(), 900, 1200)
                    )
                }
            }
            homeButton.setOnClickListener {
                launchLifecycle {
                    vM.setImageAtHomeScreen(
                        args.wallpaper.src.portrait.getAsBitmap(requireContext(), 900, 1200)
                    )
                }
            }
        }
    }

    private fun observeIsSavedWallpaper(viewModel: DetailViewModel) {
        viewModel.isWallpaperSaved.observe(viewLifecycleOwner, {
            if (it) {
                binding.favoritesButton.setColorFilter(Color.RED)
            } else {
                binding.favoritesButton.setColorFilter(Color.WHITE)
            }
        })
    }
}