package com.proyectomoviles.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.proyectomoviles.dispositivos.ControladorIluminacion
import com.proyectomoviles.dispositivos.Dispositivo
import com.proyectomoviles.dispositivos.MedidorGas
import com.proyectomoviles.dispositivos.SensorGas
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf

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
            .background(MaterialTheme.colorScheme.primary)
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
    var metrosCubicosInput by remember { mutableStateOf(medidorGas.metroscubicos.toString()) }

    Column {
        Text(text = "Configuración del Medidor de Gas")
        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Consumo actual: ${medidorGas.metroscubicos} m³")
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = metrosCubicosInput,
            onValueChange = { input ->
                // Para comrpobar q sea un número
                if (input.toFloatOrNull() != null || input.isEmpty()) {
                    metrosCubicosInput = input
                }
            },
            label = { Text("Nuevo consumo (m³)") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val nuevoValor = metrosCubicosInput.toFloatOrNull()
                if (nuevoValor != null) {
                    medidorGas.metroscubicos = nuevoValor.toDouble()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Actualizar consumo")
        }
    }
}