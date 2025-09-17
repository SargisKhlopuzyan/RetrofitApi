package com.sargis.khlopuzyan.retrofitapi.ui.imageSearchScreen

sealed interface UiEvent {
    data class OnSearchImage(val query: String) : UiEvent
    object OnTestClicked : UiEvent
}