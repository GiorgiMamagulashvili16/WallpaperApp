package com.example.wallpaperapp.presentation.wallpapers_screen

import android.os.Build
import android.text.Editable
import android.text.TextWatcher
import androidx.annotation.RequiresApi

class SearchTextWatcher(private val action: (CharSequence) -> Unit): TextWatcher {
    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        if (p0?.isNotBlank() == true && p0.last() == '\n'){
            action(p0)
        }
    }

    override fun afterTextChanged(p0: Editable?) {

    }
}