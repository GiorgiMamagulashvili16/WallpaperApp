package com.example.wallpaperapp.presentation.wallpapers_screen

import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wallpaperapp.R
import com.example.wallpaperapp.databinding.WallpapersFragmentBinding
import com.example.wallpaperapp.domain.util.extensions.flowObserver
import com.example.wallpaperapp.presentation.base.BaseFragment
import com.example.wallpaperapp.presentation.base.Inflate
import com.example.wallpaperapp.presentation.wallpapers_screen.adapters.CategoryAdapter
import com.example.wallpaperapp.presentation.wallpapers_screen.adapters.WallpapersAdapter
import com.example.wallpaperapp.presentation.wallpapers_screen.adapters.listeners.WallpapersAdapterScrollListener
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
    private val categoriesAdapter by lazy { CategoryAdapter() }
    override fun onBindViewModel(viewModel: WallpapersViewModel) {
        observeWallpapers(viewModel)
        initRecyclerView(viewModel)
        viewModel.getWallpapers()
        setMotionTransitions(viewModel)
        observeCategories(viewModel)
    }


    private fun observeWallpapers(viewModel: WallpapersViewModel) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.wallpapersScreenState.collect {
                when (it) {
                    is WallpapersScreenStates.Success -> {
                        wallpapersAdapter.submitList(it.data)
                        binding.wallpaperProgressBar.isVisible = false
                    }
                    is WallpapersScreenStates.Error -> {
                        binding.wallpaperProgressBar.isVisible = false
                    }
                    is WallpapersScreenStates.Loading -> {
                        binding.wallpaperProgressBar.isVisible = true
                    }
                    else -> Unit
                }
                viewModel.resetStateFlow()
            }
        }
    }

    private fun setMotionTransitions(viewModel: WallpapersViewModel) {
        with(binding)
        {
            searchImageView.setOnClickListener {
                with(motionLayout) {
                    setTransition(R.id.searchBarMotion)
                    transitionToEnd()
                    if (currentState == R.id.searchBarMotionStart) {
                        transitionToEnd()
                    } else {
                        transitionToStart()
                    }
                }
            }
            menuImageView.setOnClickListener {
                with(motionLayout) {
                    viewModel.setCategories()
                    setTransition(R.id.menuMotion)
                    transitionToEnd()
                    if (currentState == R.id.menuMotionStart)
                        transitionToEnd()
                    else {
                        transitionToStart()
                    }
                }
            }
        }
    }

    private fun observeCategories(viewModel: WallpapersViewModel) {
        flowObserver(viewModel.categoryFlow) {
            categoriesAdapter.submitList(it)
        }
    }

    private fun initRecyclerView(viewModel: WallpapersViewModel) {
        with(binding.imagesRecyclerView) {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = wallpapersAdapter
            val manager = layoutManager as GridLayoutManager
            val scrollListener = WallpapersAdapterScrollListener(manager) {
                viewModel.getWallpapers()
            }
            addOnScrollListener(scrollListener)
            initCategoriesRecycler()
        }
    }

    private fun initCategoriesRecycler() {
        with(binding.categoryRecyclerView) {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = categoriesAdapter
        }
    }
}