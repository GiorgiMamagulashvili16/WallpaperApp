package com.example.wallpaperapp.data.network.api

import com.example.wallpaperapp.data.models.ImageResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WallpapersApiService {

    @GET("search?")
    suspend fun getPhotos(
        @Query("query")
        query: String,
        @Query("per_page")
        perPage: Int = 20,
        @Query("page")
        page: Int = 1
    ): Response<ImageResponseDto>
}