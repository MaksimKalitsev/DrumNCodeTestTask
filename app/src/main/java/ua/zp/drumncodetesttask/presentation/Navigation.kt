package ua.zp.drumncodetesttask.presentation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import ua.zp.drumncodetesttask.presentation.startscreen.StartScreen

@Composable
fun Navigation() {

    val navController = rememberNavController()
    val viewModel = hiltViewModel<PhotosListViewModel>()

    NavHost(navController = navController, startDestination = Screen.StartScreen.route) {
        composable(Screen.StartScreen.route) {
            StartScreen(navController = navController, viewModel)
        }
        composable(Screen.DetailsScreen.route) {}
    }
}

sealed class Screen(val route: String) {
    object StartScreen : Screen("start_screen")
    object DetailsScreen : Screen("details_screen")
}