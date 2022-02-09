package com.example.wallpaperapp.domain.util.extensions

import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun Fragment.launchLifecycle(func: suspend () -> Unit) {
    viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
        func.invoke()
    }
}