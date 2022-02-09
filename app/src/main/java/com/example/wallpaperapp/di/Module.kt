package com.example.wallpaperapp.di

import com.example.wallpaperapp.data.db.provideRoomInstance
import com.example.wallpaperapp.data.db.provideWallpaperDao
import com.example.wallpaperapp.data.mappers.PhotoDomainMapper
import com.example.wallpaperapp.data.mappers.PhotoDtoMapper
import com.example.wallpaperapp.data.mappers.PhotoEntityMapper
import com.example.wallpaperapp.data.mappers.SrcDtoMapper
import com.example.wallpaperapp.data.network.api.provideHttpClient
import com.example.wallpaperapp.data.network.api.provideImageApi
import com.example.wallpaperapp.data.network.api.provideRetrofit
import com.example.wallpaperapp.data.network.interceptors.AuthorizationInterceptor
import com.example.wallpaperapp.data.network.interceptors.NetworkConnectionInterceptor
import com.example.wallpaperapp.data.repository.ImageRepositoryImpl
import com.example.wallpaperapp.data.repository.SavedWallpaperRepositoryImpl
import com.example.wallpaperapp.domain.repository.ImagesRepository
import com.example.wallpaperapp.domain.repository.SavedWallpaperRepository
import com.example.wallpaperapp.presentation.detail_screen.DetailFragment
import com.example.wallpaperapp.presentation.detail_screen.DetailViewModel
import com.example.wallpaperapp.presentation.wallpapers_screen.WallpapersFragment
import com.example.wallpaperapp.presentation.wallpapers_screen.WallpapersViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import kotlin.math.sin

val networkModule = module {
    single { AuthorizationInterceptor() }
    single { NetworkConnectionInterceptor() }
    single { provideHttpClient(get(), get()) }
    single { provideRetrofit(get()) }
}
val roomModule = module {
    single { provideRoomInstance(androidContext()) }
    single { provideWallpaperDao(get()) }
}
val wallpapersScreenModule = module {
    scope<WallpapersFragment> {
        factory { provideImageApi(get()) }
        factory<ImagesRepository> { ImageRepositoryImpl(get(), get()) }
        viewModel { WallpapersViewModel(get()) }
    }
}
val detailsScreenModule = module {
    scope<DetailFragment> {
        factory<SavedWallpaperRepository> { SavedWallpaperRepositoryImpl(get(), get(), get()) }
        viewModel { DetailViewModel() }
    }
}
val mapperModule = module {
    single { SrcDtoMapper() }
    single { PhotoDtoMapper(get()) }
    single { PhotoEntityMapper(get()) }
    single { PhotoDomainMapper(get()) }
}