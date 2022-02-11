package com.example.wallpaperapp.di

import com.example.wallpaperapp.data.datastore.WallpaperDatastore
import com.example.wallpaperapp.data.db.provideRoomInstance
import com.example.wallpaperapp.data.db.provideWallpaperDao
import com.example.wallpaperapp.data.mappers.*
import com.example.wallpaperapp.data.network.api.provideHttpClient
import com.example.wallpaperapp.data.network.api.provideImageApi
import com.example.wallpaperapp.data.network.api.provideRetrofit
import com.example.wallpaperapp.data.network.interceptors.AuthorizationInterceptor
import com.example.wallpaperapp.data.network.interceptors.NetworkConnectionInterceptor
import com.example.wallpaperapp.data.repository.ImageRepositoryImpl
import com.example.wallpaperapp.data.repository.SavedWallpaperRepositoryImpl
import com.example.wallpaperapp.domain.repository.ImagesRepository
import com.example.wallpaperapp.domain.repository.SavedWallpaperRepository
import com.example.wallpaperapp.domain.usecase.get_saved_wallpapers.GetSavedWallPapersUseCase
import com.example.wallpaperapp.domain.usecase.get_saved_wallpapers.GetSavedWallPapersUseCaseImpl
import com.example.wallpaperapp.domain.usecase.get_wallpapers.GetWallPapersUseCase
import com.example.wallpaperapp.domain.usecase.get_wallpapers.GetWallPapersUseCaseImpl
import com.example.wallpaperapp.domain.usecase.is_image_saved_usecase.IsImageSavedUseCase
import com.example.wallpaperapp.domain.usecase.is_image_saved_usecase.IsImageSavedUseCaseImpl
import com.example.wallpaperapp.domain.usecase.remove_wallpaper_usecase.RemoveWallpaperImpl
import com.example.wallpaperapp.domain.usecase.remove_wallpaper_usecase.RemoveWallpaperUseCase
import com.example.wallpaperapp.domain.usecase.save_wallpaper_usecase.SaveWallpaperUseCase
import com.example.wallpaperapp.domain.usecase.save_wallpaper_usecase.SaveWallpaperUseCaseImpl
import com.example.wallpaperapp.presentation.detail_screen.DetailFragment
import com.example.wallpaperapp.presentation.detail_screen.DetailViewModel
import com.example.wallpaperapp.presentation.detail_screen.provideWallpaperInstance
import com.example.wallpaperapp.presentation.favorites_screen.FavoritesFragment
import com.example.wallpaperapp.presentation.favorites_screen.FavoritesViewModel
import com.example.wallpaperapp.presentation.wallpapers_screen.WallpapersFragment
import com.example.wallpaperapp.presentation.wallpapers_screen.WallpapersViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

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
        factory<GetWallPapersUseCase> { GetWallPapersUseCaseImpl(get()) }
        viewModel { WallpapersViewModel(get()) }
    }
}

val detailsScreenModule = module {
    scope<DetailFragment> {
        factory<SavedWallpaperRepository> { SavedWallpaperRepositoryImpl(get(), get(), get()) }
        viewModel { DetailViewModel(get(), get(), get(), get(),get()) }
        factory { provideWallpaperInstance(androidContext()) }
        factory<SaveWallpaperUseCase> { SaveWallpaperUseCaseImpl(get()) }
        factory<RemoveWallpaperUseCase> { RemoveWallpaperImpl(get()) }
        factory<IsImageSavedUseCase> { IsImageSavedUseCaseImpl(get()) }
    }
}

val favoritesScreenModule = module {
    scope<FavoritesFragment> {
        factory<SavedWallpaperRepository> { SavedWallpaperRepositoryImpl(get(), get(), get()) }
        factory<GetSavedWallPapersUseCase> { GetSavedWallPapersUseCaseImpl(get()) }
        viewModel { FavoritesViewModel(get()) }
    }
}

val mapperModule = module {
    single { SrcDtoMapper() }
    single { SrcEntityMapper() }
    single { SrcDomainMapper() }
    single { PhotoDtoMapper(get()) }
    single { PhotoEntityMapper(get()) }
    single { PhotoDomainMapper(get()) }
}
val datastoreModule = module {
    single { WallpaperDatastore(androidContext()) }
}