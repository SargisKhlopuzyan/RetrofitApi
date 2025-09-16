package com.sargis.khlopuzyan.retrofitapi.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sargis.khlopuzyan.retrofitapi.data.constant.PixabayApiRetrofitBuilder
import com.sargis.khlopuzyan.retrofitapi.data.remote.PixabayApiService
import com.sargis.khlopuzyan.retrofitapi.data.repository.PixabayRepositoryImpl
import com.sargis.khlopuzyan.retrofitapi.domain.usecase.SearchImagesUseCaseImpl
import com.sargis.khlopuzyan.retrofitapi.domain.util.Result
import kotlinx.coroutines.launch

class ImageSearchViewModel : ViewModel() {

    val pixabayApiService: PixabayApiService = PixabayApiRetrofitBuilder.build()
    val pixabayRepository = PixabayRepositoryImpl(pixabayApiService)
    val searchImagesUseCase = SearchImagesUseCaseImpl(pixabayRepository)

    val state: MutableState<ImageSearchUiState> = mutableStateOf(ImageSearchUiState())

    fun searchImageByQuery(query: String) {
        viewModelScope.launch {
            state.value = ImageSearchUiState(
                isLoading = true,
                error = ""
            )

            try {
                val result = searchImagesUseCase.searchImagesByQuery(query)
                when (result) {
                    is Result.Error -> {
                        state.value = ImageSearchUiState(
                            isLoading = false,
                            error = "Something went wrong"
                        )
                    }

                    is Result.Success -> {
                        val result = result.data?.hits ?: emptyList()
                        state.value = ImageSearchUiState(
                            isLoading = false,
                            data = result,
                            error = ""
                        )
                    }
                }
                println("result: $result")
            } catch (e: Exception) {
                state.value = ImageSearchUiState(
                    isLoading = false,
                    error = "Something went wrong"
                )
            }
        }
    }
}