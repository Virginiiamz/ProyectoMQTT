package com.proyectomoviles.ui.navegacion

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.proyectomoviles.data.AuthManager
import com.proyectomoviles.data.FirestoreManager
import com.proyectomoviles.dispositivos.Dispositivo
import com.proyectomoviles.screen.ConfiguracionScreen
import com.proyectomoviles.screen.ElementosScreen
import com.proyectomoviles.screen.InicioScreen
import com.proyectomoviles.screen.ScreenLogin
import com.proyectomoviles.services.MqttService

@Composable
fun Navegacion(auth: AuthManager, mqttService: MqttService) {
    val navController = rememberNavController()
    val context = LocalContext.current
    val firestore = FirestoreManager(auth, context)

    NavHost(
        navController = navController,
        startDestination = Login
    ) {
        composable<Login> {
            ScreenLogin (
                auth,
                { navController.navigate(SignUp) },
                {
                    navController.navigate(Inicio) {
                        popUpTo(Login) { inclusive = true }
                    }
                },
                { navController.navigate(ForgotPassword) }
            )
        }

        composable<SignUp> {
//            SignUpScreen(
//                auth
//            ) { navController.popBackStack() }
        }

        composable<ForgotPassword> {
//            ForgotPasswordScreen(
//                auth
//            ) {
//                navController.navigate(login) {
//                    popUpTo(login) { inclusive = true }
//                }
//            }
        }

        composable<Inicio> { backStatEntry ->
            val dispositivo = backStatEntry.toRoute<Inicio>()
            InicioScreen({navController.navigate(Elementos)}, {navController.navigate(Inicio)}, mqttService)
//                navController.navigate(Elementos)
//                {navController.navigate(Inicio)}

//            }
        }
        composable<Elementos> { backStackEntry ->
//            Navegacion a la pantalla Elementos
            val elementos = backStackEntry.toRoute<Elementos>()
            ElementosScreen { nombreDispositivo ->
                (navController.navigate(ConfigurarDispositivos(nombreDispositivo)))
            }
        }

        composable<ConfigurarDispositivos> { backStatEntry ->
            val tipoDispositivo = backStatEntry.toRoute<ConfigurarDispositivos>()
            ConfiguracionScreen(tipoDispositivo.tipo, null, { navController.navigate(Inicio) }, mqttService, auth, firestore)
        }
    }
}