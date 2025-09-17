package com.sargis.khlopuzyan.retrofitapi.navigation

sealed class Screen(val route: String) {
    object ImageSearchScreen : Screen("image_search_screen")
    object ImageDetailScreen : Screen("image_detail_screen")
}