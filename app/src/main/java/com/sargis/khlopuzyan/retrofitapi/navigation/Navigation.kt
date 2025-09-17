package com.sargis.khlopuzyan.retrofitapi.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.sargis.khlopuzyan.retrofitapi.ui.imageDetailScreen.ImageDetailScreen
import com.sargis.khlopuzyan.retrofitapi.ui.imageDetailScreen.ImageDetailViewModel
import com.sargis.khlopuzyan.retrofitapi.ui.imageSearchScreen.ImageSearchScreen
import com.sargis.khlopuzyan.retrofitapi.ui.imageSearchScreen.ImageSearchViewModel
import com.sargis.khlopuzyan.retrofitapi.ui.imageSearchScreen.UiEvent
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.ImageSearchScreen.route
    ) {
        composable(route = Screen.ImageSearchScreen.route) {
            //    val viewModel: ImageSearchViewModel = viewModel()
            val viewModel: ImageSearchViewModel = koinViewModel()
            val uiState = viewModel.uiState.collectAsStateWithLifecycle()
            LaunchedEffect(Unit) {
                viewModel.navigation.collect {
                    println("LOG_TAG - TEST CLICKED $it")
                }
            }
            ImageSearchScreen(
                navController = navController,
                uiState = uiState,
                searchImage = { query ->
                    viewModel.onEvent(UiEvent.OnSearchImage(query))
                },
                testClicked = {
                    viewModel.onEvent(UiEvent.OnTestClicked)
                }
            ) {
                navController.navigate(Screen.ImageDetailScreen.route + "?imageId=$it")
            }
        }
        composable(
            route = Screen.ImageDetailScreen.route + "?imageId={imageId}",
            arguments = listOf(
                navArgument("imageId") {
                    type = NavType.LongType
                    defaultValue = -1
                    nullable = false
                }
            )
        ) { backStackEntry ->
            val cityId = backStackEntry.arguments?.getLong("cityId") ?: -1
            koinViewModel<ImageDetailViewModel>(
                parameters = { parametersOf(cityId) }
            )
            ImageDetailScreen(navController)
        }
    }
}