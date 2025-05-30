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
import com.proyectomoviles.screen.ForgotPasswordScreen
import com.proyectomoviles.screen.InicioScreen
import com.proyectomoviles.screen.ScreenLogin
import com.proyectomoviles.screen.SignUpScreen
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
            SignUpScreen(
                auth
            ) { navController.popBackStack() }
        }

        composable<ForgotPassword> {
            ForgotPasswordScreen(
                auth
            ) {
                navController.navigate(Login) {
                    popUpTo(Login) { inclusive = true }
                }
            }
        }

        composable<Inicio> { backStatEntry ->
            InicioScreen(
                navigateToElementos = { navController.navigate(Elementos) },
                navigateToInicio = { navController.navigate(Inicio) },
                navigateToLogin = {
                    navController.navigate(Login) {
                        popUpTo(Inicio) { inclusive = true } // Limpia la pila
                    }
                },
                mqttService = mqttService,
                firestoreManager = firestore,
                auth = auth // Añade el authManager aquí
            )
        }
        composable<Elementos> { backStackEntry ->
            ElementosScreen(
                onNavigateToConfiguracion = { nombreDispositivo ->
                    navController.navigate(ConfigurarDispositivos(nombreDispositivo))
                },
                onLogout = {
                    navController.navigate(Login) {
                        popUpTo(Inicio) { inclusive = true } // Limpia la pila
                    }
                }
            )
        }


        composable<ConfigurarDispositivos> { backStatEntry ->
            val tipoDispositivo = backStatEntry.toRoute<ConfigurarDispositivos>()
            ConfiguracionScreen(
                tipoDispositivo.tipo,
                null,
                { navController.navigate(Inicio) },
                { navController.navigate(Login) { popUpTo(Inicio) { inclusive = true } } },
                mqttService,
                auth,
                firestore
            )
        }

    }
}