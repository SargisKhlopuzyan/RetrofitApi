package com.sargis.khlopuzyan.retrofitapi.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sargis.khlopuzyan.retrofitapi.data.constant.PixabayApiRetrofitBuilder
import com.sargis.khlopuzyan.retrofitapi.data.remote.PixabayApi
import com.sargis.khlopuzyan.retrofitapi.data.repository.PixabayRepositoryImpl
import com.sargis.khlopuzyan.retrofitapi.domain.usecase.SearchImagesUseCaseImpl
import kotlinx.coroutines.launch

class ImageSearchViewModel : ViewModel() {

    val pixabayApi: PixabayApi = PixabayApiRetrofitBuilder.build()
    val pixabayRepository = PixabayRepositoryImpl(pixabayApi)
    val searchImagesUseCase = SearchImagesUseCaseImpl(pixabayRepository)

    fun searchImageByQuery(query: String) {
        viewModelScope.launch {
            val result = searchImagesUseCase.searchImagesByQuery(query)
            println("result: $result")
        }
    }
}