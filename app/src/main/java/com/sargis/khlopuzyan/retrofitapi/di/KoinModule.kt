package com.sargis.khlopuzyan.retrofitapi.di

import com.sargis.khlopuzyan.retrofitapi.data.constant.PixabayApiRetrofitBuilder
import com.sargis.khlopuzyan.retrofitapi.data.repository.ImageSearchRepositoryImpl
import com.sargis.khlopuzyan.retrofitapi.domain.repository.ImageSearchRepository
import com.sargis.khlopuzyan.retrofitapi.domain.usecase.ImageSearchUseCase
import com.sargis.khlopuzyan.retrofitapi.domain.usecase.ImageSearchUseCaseImpl
import com.sargis.khlopuzyan.retrofitapi.ui.imageDetailScreen.ImageDetailViewModel
import com.sargis.khlopuzyan.retrofitapi.ui.imageSearchScreen.ImageSearchViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

private val repositoryModule = module {
    single<ImageSearchRepository> { ImageSearchRepositoryImpl(get()) }
    single { PixabayApiRetrofitBuilder.build() }
}

private val useCaseModule = module {
    single<ImageSearchUseCase> { ImageSearchUseCaseImpl(get()) }
}

private val viewModelModule = module {
    viewModel {
        ImageSearchViewModel(get())
    }
    viewModel {
        ImageDetailViewModel()
    }
}

val dataModule = listOf(repositoryModule)
val domainModule = listOf(useCaseModule)
val presentationModule = listOf(viewModelModule)
