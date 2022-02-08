package com.example.wallpaperapp.presentation.wallpapers_screen

import android.app.WallpaperManager
import android.util.Log.d
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.example.wallpaperapp.databinding.WallpapersFragmentBinding
import com.example.wallpaperapp.presentation.base.BaseFragment
import com.example.wallpaperapp.presentation.base.Inflate
import com.example.wallpaperapp.presentation.wallpapers_screen.adapters.WallpapersAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlin.reflect.KClass

class WallpapersFragment : BaseFragment<WallpapersFragmentBinding, WallpapersViewModel>() {
    override val viewModelClass: KClass<WallpapersViewModel>
        get() = WallpapersViewModel::class

    override fun inflateFragment(): Inflate<WallpapersFragmentBinding> {
        return WallpapersFragmentBinding::inflate
    }

    private val wallpapersAdapter by lazy { WallpapersAdapter() }
    override fun onBindViewModel(viewModel: WallpapersViewModel) {
        observeWallpapers(viewModel)
    }

    private fun observeWallpapers(viewModel: WallpapersViewModel) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.wallpapersScreenState.collect {
                when (it) {
                    is WallpapersScreenStates.Success -> {
                        initRecyclerView()
                        d("ERRORSTATE", "${it.data}")
                        wallpapersAdapter.submitList(
                            it.data
                        )
                    }
                    is WallpapersScreenStates.Error -> {
                        d("ERRORSTATE", "${it.message}")
                    }
                    is WallpapersScreenStates.Loading -> {

                    }
                }
            }
        }
    }

    private fun initRecyclerView() {
        with(binding.imagesRecyclerView) {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = wallpapersAdapter
        }
        wallpapersAdapter.onImageClick = {
            viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
                WallpaperManager.getInstance(requireContext()).setBitmap(
                    Glide.with(requireContext()).asBitmap().load(it).submit(1080   ,1920).get()
                )
            }

        }
    }
}