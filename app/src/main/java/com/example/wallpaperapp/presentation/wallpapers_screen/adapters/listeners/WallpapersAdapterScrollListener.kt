package com.example.wallpaperapp.presentation.wallpapers_screen.adapters.listeners

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class WallpapersAdapterScrollListener(
    private val manager: GridLayoutManager,
    private val pagingCallback: () -> Unit
): RecyclerView.OnScrollListener() {
    private var previousDataCount = manager.itemCount

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        if (manager.findFirstVisibleItemPosition() + manager.childCount >= manager.itemCount
            && previousDataCount != manager.itemCount){
            pagingCallback()
            previousDataCount = manager.itemCount
        }
    }

}