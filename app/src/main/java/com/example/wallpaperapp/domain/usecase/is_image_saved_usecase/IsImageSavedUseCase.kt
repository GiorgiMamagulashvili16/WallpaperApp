package com.example.wallpaperapp.domain.usecase.is_image_saved_usecase

interface IsImageSavedUseCase {
    suspend fun isImageSaved(id:Int):Boolean
}