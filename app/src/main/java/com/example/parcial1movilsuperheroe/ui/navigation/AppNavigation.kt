package com.example.parcial1movilsuperheroe.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.parcial1movilsuperheroe.ui.SuperheroViewModel
import com.example.parcial1movilsuperheroe.ui.SuperheroViewModelFactory
import com.example.parcial1movilsuperheroe.ui.screens.DetailScreen
import com.example.parcial1movilsuperheroe.ui.screens.HomeScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    // Instanciar ViewModel anclado a toda la navegación usando nuestro Factory
    val viewModel: SuperheroViewModel = viewModel(factory = SuperheroViewModelFactory())

    NavHost(navController = navController, startDestination = "home") {
        
        composable("home") {
            HomeScreen(
                viewModel = viewModel,
                onHeroClick = { heroId ->
                    navController.navigate("detail/$heroId")
                }
            )
        }

        composable(
            route = "detail/{heroId}",
            arguments = listOf(navArgument("heroId") { type = NavType.IntType })
        ) { backStackEntry ->
            val heroId = backStackEntry.arguments?.getInt("heroId") ?: return@composable
            
            DetailScreen(
                heroId = heroId,
                viewModel = viewModel,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}
