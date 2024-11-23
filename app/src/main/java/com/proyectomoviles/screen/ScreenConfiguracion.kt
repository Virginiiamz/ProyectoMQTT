package com.proyectomoviles.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.proyectomoviles.dispositivos.ControladorIluminacion
import com.proyectomoviles.dispositivos.Dispositivo
import com.proyectomoviles.dispositivos.MedidorGas
import com.proyectomoviles.dispositivos.SensorGas

@Composable
fun ConfiguracionScreen(
    navController: NavController,
    dispositivo: Dispositivo
) {
    Scaffold(
        topBar = {
            //TopAppBar me da errores así que lo hago así
            ComoTopAppBarSinTopAppBar(dispositivo, onBackPressed = { navController.navigateUp() })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            when (dispositivo) {
                is ControladorIluminacion -> ConfiguracionControladorIluminacion(dispositivo)
                is SensorGas -> ConfiguracionSensorGas(dispositivo)
                is MedidorGas -> ConfiguracionMedidorGas(dispositivo)
                else -> Text(text = "Configuración no disponible para este dispositivo")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    navController.navigate("ScreenInicio") {
                        popUpTo("ScreenInicio") { inclusive = true }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Aceptar")
            }
        }
    }
}

@Composable
fun ComoTopAppBarSinTopAppBar(dispositivo: Dispositivo, onBackPressed: () -> Unit) {
    // TopAppBar me odia así que lo hago con box y row os vale?
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.align(Alignment.CenterStart)
        ) {
            IconButton(onClick = onBackPressed) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Atrás",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
            Text(
                text = "Configuración de ${dispositivo.nombre}",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
    }
}



@Composable
fun ConfiguracionControladorIluminacion(controlador: ControladorIluminacion) {
    Column {
        Text(text = "Configuración del Controlador de Iluminación")
        Spacer(modifier = Modifier.height(8.dp))

        Spacer(modifier = Modifier.height(8.dp))

        val nuevoEstado = !controlador.encendido
        Button(onClick = {
            controlador.encendido = nuevoEstado
        }) {
            Text(text = if (controlador.encendido) "Apagar" else "Encender")
        }
    }
}

@Composable
fun ConfiguracionSensorGas(sensorGas: SensorGas) {
    Column {
        Text(text = "Configuración del Sensor de Gas")
        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Concentración de CO2: ${sensorGas.concentracionCO2} ppm")
        Text(text = "Concentración de CH4: ${sensorGas.concentracionCH4} ppm")
    }
}

@Composable
fun ConfiguracionMedidorGas(medidorGas: MedidorGas) {
    Column {
        Text(text = "Configuración del Medidor de Gas")
        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Consumo actual: ${medidorGas.metroscubicos} m³")
    }
}