package com.example.wallpaperapp.presentation.detail_screen

import com.example.wallpaperapp.databinding.DetailFragmentBinding
import com.example.wallpaperapp.presentation.base.BaseFragment
import com.example.wallpaperapp.presentation.base.Inflate
import kotlin.reflect.KClass

class DetailFragment : BaseFragment<DetailFragmentBinding, DetailViewModel>() {
    override val viewModelClass: KClass<DetailViewModel>
        get() = DetailViewModel::class

    override fun inflateFragment(): Inflate<DetailFragmentBinding> {
        return DetailFragmentBinding::inflate
    }

    override fun onBindViewModel(viewModel: DetailViewModel) {
        TODO("Not yet implemented")
    }


}