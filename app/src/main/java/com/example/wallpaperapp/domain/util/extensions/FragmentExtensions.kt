package com.example.wallpaperapp.domain.util.extensions

import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers

fun <T> Fragment.flowObserver(flow: Flow<T>, observer: (t: T) -> Unit) {
    viewLifecycleOwner.lifecycleScope.launch {
        flow.collect { observer(it) }
    }
}

fun Fragment.launchLifecycle(func: suspend () -> Unit) {
    viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
        func.invoke()
    }
}