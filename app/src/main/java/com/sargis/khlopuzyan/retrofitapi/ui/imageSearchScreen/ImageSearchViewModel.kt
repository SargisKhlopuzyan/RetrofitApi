package com.sargis.khlopuzyan.retrofitapi.ui.imageSearchScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sargis.khlopuzyan.retrofitapi.domain.usecase.ImageSearchUseCase
import com.sargis.khlopuzyan.retrofitapi.domain.util.Result
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class ImageSearchViewModel(
    val imageSearchUseCase: ImageSearchUseCase,
) : ViewModel() {

//    val pixabayApiService: PixabayApiService = PixabayApiRetrofitBuilder.build()
//    val imageSearchRepository = ImageSearchRepositoryImpl(pixabayApiService)
//    val imageSearchUseCase = ImageSearchUseCaseImpl(pixabayRepository)

//    private val _uiState: MutableState<UiState> = mutableStateOf(UiState())
//    val uiState: State<UiState> = _uiState

    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    private val _navigation: Channel<NavigationEvent> = Channel<NavigationEvent>()
    val navigation: Flow<NavigationEvent> = _navigation.receiveAsFlow()

    fun onEvent(event: UiEvent) {
        when (event) {
            is UiEvent.OnSearchImage -> searchImageByQuery(event.query)
            is UiEvent.OnTestClicked -> {
                _navigation.trySend(NavigationEvent.NavigateDetailScreen(1))
            }
        }
    }

    fun onNavigationEvent(event: NavigationEvent) {
        when (event) {
            NavigationEvent.NavigateBack -> {
                _navigation.trySend(NavigationEvent.NavigateBack)
            }

            is NavigationEvent.NavigateDetailScreen -> {
                _navigation.trySend(NavigationEvent.NavigateDetailScreen(1))
            }

            is NavigationEvent.ShowToast -> {
                _navigation.trySend(NavigationEvent.ShowToast("THis is demo toast"))
            }
        }
    }

    private fun searchImageByQuery(query: String) {
        viewModelScope.launch {
            _uiState.value = UiState(
                isLoading = true,
                error = ""
            )

            try {
                val result = imageSearchUseCase.searchImageByQuery(query)
                when (result) {
                    is Result.Error -> {
                        _uiState.value = UiState(
                            isLoading = false,
//                            error = "Something went wrong"
                            error = result.uiError?.name ?: "Something went wrong"
                        )
                    }

                    is Result.Success -> {
                        val result = result.data?.hits ?: emptyList()
                        _uiState.value = UiState(
                            isLoading = false,
                            data = result,
                            error = ""
                        )
                    }
                }
                println("result: $result")
            } catch (e: Exception) {
                _uiState.value = UiState(
                    isLoading = false,
                    error = "Something went wrong"
                )
            }
        }
    }
}