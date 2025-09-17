package com.sargis.khlopuzyan.retrofitapi.ui.imageSearchScreen

sealed interface NavigationEvent {
    object NavigateBack : NavigationEvent
    data class NavigateDetailScreen(val id: Int) : NavigationEvent
    data class ShowToast(val query: String) : NavigationEvent
}