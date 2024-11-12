package com.proyectomoviles

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.proyectomoviles.screen.Elementos
import com.proyectomoviles.screen.ElementosScreen
import com.proyectomoviles.screen.Inicio
import com.proyectomoviles.screen.InicioScreen
import com.proyectomoviles.ui.theme.ProyectoMovilesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            ProyectoMovilesTheme {
                Navegacion()

            }
        }
    }
}

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

