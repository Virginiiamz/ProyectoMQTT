package com.proyectomoviles

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.proyectomoviles.data.AuthManager
import com.proyectomoviles.screen.ElementosScreen
import com.proyectomoviles.screen.InicioScreen
import com.proyectomoviles.services.MqttService
import com.proyectomoviles.ui.navegacion.Navegacion
import com.proyectomoviles.ui.theme.ProyectoMovilesTheme

class MainActivity : ComponentActivity() {
    val auth = AuthManager(this)
    lateinit var mqttService: MqttService
    private lateinit var idClient: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getClientId()
        mqttService = MqttService(applicationContext, idClient)
        mqttService.connect()
        //enableEdgeToEdge()
        setContent {
            ProyectoMovilesTheme {
                Navegacion(auth, mqttService)

            }
        }
    }

    fun getClientId() {
        val manufacturer = Build.MANUFACTURER
        val model = Build.MODEL
        idClient = "$manufacturer $model"
    }

    override fun onDestroy() {
        super.onDestroy()
        auth.signOut()
    }
}



