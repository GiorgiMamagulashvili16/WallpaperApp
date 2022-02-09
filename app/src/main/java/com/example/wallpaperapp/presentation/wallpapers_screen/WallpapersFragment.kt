package com.example.wallpaperapp.presentation.wallpapers_screen

import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.example.wallpaperapp.R
import com.example.wallpaperapp.databinding.WallpapersFragmentBinding
import com.example.wallpaperapp.domain.util.extensions.setActionOnSpecifiedProgress
import com.example.wallpaperapp.domain.util.extensions.setDrawableImage
import com.example.wallpaperapp.domain.util.extensions.flowObserver
import com.example.wallpaperapp.presentation.base.BaseFragment
import com.example.wallpaperapp.presentation.base.Inflate
import com.example.wallpaperapp.presentation.wallpapers_screen.adapters.CategoryAdapter
import com.example.wallpaperapp.presentation.wallpapers_screen.adapters.WallpapersAdapter
import com.example.wallpaperapp.presentation.wallpapers_screen.adapters.listeners.WallpapersAdapterScrollListener
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlin.math.abs
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
        observeCategories(viewModel)
        configureSearch(viewModel)
        viewModel.setCategories()
    }

    private fun configureSearch(viewModel: WallpapersViewModel){
        with(binding.searchEditText){
            val watcher = SearchTextWatcher{
                viewModel.getSearchedWallPapers(it)
                setText("")
            }
            addTextChangedListener(watcher)
        }
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
        }
        initCategoriesRecycler()
    }

    private fun initCategoriesRecycler() {
        with(binding.categoryRecyclerView) {
            clipToPadding = false
            clipChildren = false
            offscreenPageLimit = 3
            getChildAt(0)
            overScrollMode = View.OVER_SCROLL_NEVER
            adapter = categoriesAdapter
        }
        val transformer = CompositePageTransformer()
        with(transformer) {
            addTransformer(MarginPageTransformer(8))
            addTransformer { view: View, fl: Float ->
                val v = 1 - abs(fl)
                view.scaleY = 0.9f + v * 0.2f
            }
        }
        binding.categoryRecyclerView.setPageTransformer(transformer)
    }
}