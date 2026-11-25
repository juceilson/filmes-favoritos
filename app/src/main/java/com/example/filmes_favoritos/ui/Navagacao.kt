package com.example.filmes_favoritos.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.filmes_favoritos.App
import com.example.filmes_favoritos.ui.screens.DetalhesFilmeScreen
import com.example.filmes_favoritos.ui.screens.FavoritosScreen
import com.example.filmes_favoritos.ui.screens.FilmeListScreen
import com.example.filmes_favoritos.ui.screens.HomeScreen
import com.example.filmes_favoritos.ui.viewModels.DetalhesFilmeViewModel
import com.example.filmes_favoritos.ui.viewModels.FactoryViewModel
import com.example.filmes_favoritos.ui.viewModels.FavoritosViewModel
import com.example.filmes_favoritos.ui.viewModels.FilmeListViewModel

object NavRoutes {
    const val HOME = "home"
    const val FILME_LIST = "filme_list"
    const val DETALHES_FILME = "detalhes_filme/{id}"
    const val FAVORITOS = "favoritos"

    fun createMovieDetailsRoute(id: String): String = "detalhes_filme/$id"
}

@Composable
fun FilmesNavegacao(
    navController: NavHostController = rememberNavController()
) {
    val context = LocalContext.current.applicationContext
    val appContainer = (context as App).container

    NavHost(navController = navController, startDestination = NavRoutes.HOME) {

        composable(NavRoutes.HOME) {
            HomeScreen(
                onNavigateToMovieList = { navController.navigate(NavRoutes.FILME_LIST) },
                onNavigateToFavorites = { navController.navigate(NavRoutes.FAVORITOS) }
            )
        }

        composable(NavRoutes.FILME_LIST) {
            val filmeListViewModel: FilmeListViewModel = viewModel(
                factory = FactoryViewModel(appContainer.filmeRepository)
            )
            FilmeListScreen(
                viewModel = filmeListViewModel,
                onMovieClick = { id ->
                    navController.navigate(NavRoutes.createMovieDetailsRoute(id))
                },
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable(
            route = NavRoutes.DETALHES_FILME,
            arguments = listOf(
                navArgument("id") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id") ?: ""
            val movieDetailsViewModel: DetalhesFilmeViewModel = viewModel(
                factory = FactoryViewModel(appContainer.filmeRepository, id = id)
            )
            DetalhesFilmeScreen(
                viewModel = movieDetailsViewModel,
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable(NavRoutes.FAVORITOS) {
            val favoritosViewModel: FavoritosViewModel = viewModel(
                factory = FactoryViewModel(appContainer.filmeRepository)
            )
            FavoritosScreen(
                viewModel = favoritosViewModel,
                onMovieClick = { id ->
                    navController.navigate(NavRoutes.createMovieDetailsRoute(id))
                },
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}
