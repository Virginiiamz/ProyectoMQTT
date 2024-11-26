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
import com.proyectomoviles.screen.Prueba

@Composable
fun Navegacion() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Inicio
    ) {
        composable<Inicio> { backStatEntry ->
            val dispositivo = backStatEntry.toRoute<Inicio>()
            InicioScreen {
                navController.navigate(Elementos)
            }
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
//        composable<Elementos> { backStackEntry ->
//            val elementos = backStackEntry.toRoute<Elementos>()
//            ElementosScreen { dispositivo ->
//                navController.navigate("Configuracion/${dispositivo.nombre}")
//            }
//        }
//        composable(
//            route = "Configuracion/{nombreDispositivo}",
//            arguments = listOf(navArgument("nombreDispositivo") { type = NavType.StringType })
//        ) { backStackEntry ->
//            val nombreDispositivo = backStackEntry.arguments?.getString("nombreDispositivo") ?: ""
//            val dispositivo = listaElementos().find { it.nombre == nombreDispositivo }
//            dispositivo?.let {
//                ConfiguracionScreen(navController, it)
//            }
//        }
    }
}