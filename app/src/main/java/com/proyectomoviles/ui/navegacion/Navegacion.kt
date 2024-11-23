package com.proyectomoviles.ui.navegacion

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.proyectomoviles.screen.ElementosScreen
import com.proyectomoviles.screen.InicioScreen

@Composable
fun Navegacion() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Inicio
    ) {
        composable<Inicio>{
            InicioScreen{
                navController.navigate(Elementos)
            }
        }
        composable<Elementos>{
                backStackEntry ->
            val elementos = backStackEntry.toRoute<Elementos>()
            ElementosScreen()
        }
    }
}