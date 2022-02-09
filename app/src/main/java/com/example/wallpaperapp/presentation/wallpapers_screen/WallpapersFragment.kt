package com.example.wallpaperapp.presentation.wallpapers_screen

import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.wallpaperapp.databinding.WallpapersFragmentBinding
import com.example.wallpaperapp.presentation.base.BaseFragment
import com.example.wallpaperapp.presentation.base.Inflate
import com.example.wallpaperapp.presentation.wallpapers_screen.adapters.WallpapersAdapter
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
        recyclerViewListeners()
    }


    private fun observeWallpapers(viewModel: WallpapersViewModel) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.wallpapersScreenState.collect {
                when (it) {
                    is WallpapersScreenStates.Success -> {
                        initRecyclerView()
                        wallpapersAdapter.submitList(
                            it.data
                        )
                    }
                    is WallpapersScreenStates.Error -> {
                    }
                    is WallpapersScreenStates.Loading -> {

                    }
                    else -> Unit
                }
            }
        }
    }

    private fun initRecyclerView() {
        with(binding.imagesRecyclerView) {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = wallpapersAdapter
        }
    }

    private fun recyclerViewListeners() {
        wallpapersAdapter.onItemClick = { wallpaper ->
            findNavController().navigate(
                WallpapersFragmentDirections.actionWallpapersFragmentToDetailFragment(
                    wallpaper
                )
            )
        }
    }
}