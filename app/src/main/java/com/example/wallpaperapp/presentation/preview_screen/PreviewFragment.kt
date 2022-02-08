package com.example.wallpaperapp.presentation.preview_screen

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.wallpaperapp.R
import com.example.wallpaperapp.databinding.PreviewFragmentBinding
import com.example.wallpaperapp.databinding.WallpapersFragmentBinding
import com.example.wallpaperapp.presentation.base.BaseFragment
import com.example.wallpaperapp.presentation.base.Inflate
import com.example.wallpaperapp.presentation.wallpapers_screen.WallpapersViewModel
import kotlin.reflect.KClass

class PreviewFragment : BaseFragment<PreviewFragmentBinding, PreviewViewModel>() {
    override val viewModelClass: KClass<PreviewViewModel>
        get() = PreviewViewModel::class

    override fun inflateFragment(): Inflate<PreviewFragmentBinding> {
        return PreviewFragmentBinding::inflate
    }

    override fun onBindViewModel(viewModel: PreviewViewModel) {
        TODO("Not yet implemented")
    }

}