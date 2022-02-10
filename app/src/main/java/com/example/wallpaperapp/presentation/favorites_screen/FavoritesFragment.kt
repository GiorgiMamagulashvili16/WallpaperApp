package com.example.wallpaperapp.presentation.favorites_screen

import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.wallpaperapp.databinding.FragmentFavoritesBinding
import com.example.wallpaperapp.domain.util.extensions.flowObserver
import com.example.wallpaperapp.domain.util.extensions.makeToast
import com.example.wallpaperapp.presentation.base.BaseFragment
import com.example.wallpaperapp.presentation.base.Inflate
import com.example.wallpaperapp.presentation.favorites_screen.adapters.FavoritesAdapter
import kotlin.reflect.KClass

class FavoritesFragment : BaseFragment<FragmentFavoritesBinding, FavoritesViewModel>() {
    override val viewModelClass: KClass<FavoritesViewModel> = FavoritesViewModel::class
    override fun inflateFragment(): Inflate<FragmentFavoritesBinding> =
        FragmentFavoritesBinding::inflate

    private val favoritesAdapter by lazy { FavoritesAdapter() }

    override fun onBindViewModel(viewModel: FavoritesViewModel) {
        initRecyclerView()
        observeStates(viewModel)
        configureBackNavigation()
    }

    private fun configureBackNavigation(){
        binding.backNavImageView.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun initRecyclerView(){
        val layoutManager = GridLayoutManager(requireContext(), 2)

        favoritesAdapter.setOnItemClickListener {
            findNavController().navigate(
                FavoritesFragmentDirections.actionFavoritesFragmentToDetailFragment(it)
            )
        }

        with(binding.favoritesRecyclerView){
            adapter = favoritesAdapter
            setLayoutManager(layoutManager)
        }
    }

    private fun observeStates(viewModel: FavoritesViewModel) {
        flowObserver(viewModel.wallpapersScreenState) {
            binding.wallpaperProgressBar.isVisible = false
            when (it) {
                is FavoritesScreenState.Success -> favoritesAdapter.submitList(it.data)
                is FavoritesScreenState.Error -> makeToast(it.message)
                FavoritesScreenState.Loading -> binding.wallpaperProgressBar.isVisible = true
                else -> Unit
            }
        }
    }

}