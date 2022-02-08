package com.example.wallpaperapp.presentation.wallpapers_screen

import android.util.Log
import android.util.Log.d
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wallpaperapp.R
import com.example.wallpaperapp.databinding.WallpapersFragmentBinding
import com.example.wallpaperapp.domain.util.extensions.flowObserver
import com.example.wallpaperapp.presentation.base.BaseFragment
import com.example.wallpaperapp.presentation.base.Inflate
import com.example.wallpaperapp.presentation.wallpapers_screen.adapters.CategoryAdapter
import com.example.wallpaperapp.presentation.wallpapers_screen.adapters.WallpapersAdapter
import kotlin.reflect.KClass

class WallpapersFragment : BaseFragment<WallpapersFragmentBinding, WallpapersViewModel>() {
    override val viewModelClass: KClass<WallpapersViewModel>
        get() = WallpapersViewModel::class

    override fun inflateFragment(): Inflate<WallpapersFragmentBinding> {
        return WallpapersFragmentBinding::inflate
    }

    private val wallpapersAdapter by lazy { WallpapersAdapter() }
    private val categoryAdapter by lazy { CategoryAdapter() }
    override fun onBindViewModel(viewModel: WallpapersViewModel) {
        observeWallpapers(viewModel)
        setMotionTransitions(viewModel)
        observeCategories(viewModel)
    }

    private fun setMotionTransitions(viewModel: WallpapersViewModel) {
        with(binding)
        {
            searchImageView.setOnClickListener {
                with(root) {
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
                with(root) {
                    viewModel.setCategories()
                    setTransition(R.id.menuMotion)
                    transitionToEnd()
                    if (currentState == R.id.menuMotionStart)
                        transitionToEnd()
                    else{
                        viewModel.setCategories()
                        transitionToStart()
                    }

                }
            }
        }
    }

    private fun observeCategories(viewModel: WallpapersViewModel) {
        flowObserver(viewModel.categories) {
            initCategoryRecycle()
            categoryAdapter.submitList(it)
            d("ITEM","$it")
        }
    }

    private fun observeWallpapers(viewModel: WallpapersViewModel) {
        flowObserver(viewModel.wallpapersScreenState) {
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

    private fun initCategoryRecycle() {
        with(binding.categoryRecyclerView) {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = categoryAdapter
        }
    }

    private fun initRecyclerView() {
        with(binding.imagesRecyclerView) {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = wallpapersAdapter
        }
    }
}