package com.sargis.khlopuzyan.retrofitapi.ui.imageDetailScreen

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.sargis.khlopuzyan.retrofitapi.ui.imageSearchScreen.ImageSearchViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ImageDetailScreen(navController: NavHostController) {
    val viewModel: ImageSearchViewModel = koinViewModel()
}