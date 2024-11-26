package com.proyectomoviles.ui.navegacion

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.proyectomoviles.dispositivos.Dispositivo
import com.proyectomoviles.screen.ConfiguracionScreen
import com.proyectomoviles.screen.ElementosScreen
import com.proyectomoviles.screen.InicioScreen

@Composable
fun Navegacion() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Inicio
    ) {
        composable<Inicio> { backStatEntry ->
            val dispositivo = backStatEntry.toRoute<Inicio>()
            InicioScreen({navController.navigate(Elementos)}, {navController.navigate(Inicio)})
//                navController.navigate(Elementos)
//                {navController.navigate(Inicio)}

//            }
        }
        composable<Elementos> { backStackEntry ->
//            Navegacion a la pantalla Elementos
            val elementos = backStackEntry.toRoute<Elementos>()
            ElementosScreen { tipoDispositivo ->
                (navController.navigate(ConfigurarDispositivos(tipoDispositivo.nombre)))
            }
        }

        composable<ConfigurarDispositivos> { backStatEntry ->
            val tipoDispositivo = backStatEntry.toRoute<ConfigurarDispositivos>()
            ConfiguracionScreen(tipoDispositivo.tipo, null) { navController.navigate(Inicio) }
        }
    }
}