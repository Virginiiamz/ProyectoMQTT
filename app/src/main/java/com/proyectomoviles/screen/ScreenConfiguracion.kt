package com.proyectomoviles.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.proyectomoviles.dispositivos.SensorGas


@Composable
fun ConfiguracionScreen(
    navController: NavController,
    dispositivo: Dispositivo
) {
    Scaffold(
        topBar = { MyTopAppBar(dispositivo) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            // Configuración según el dispositivo
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
fun MyTopAppBar(dispositivo: Dispositivo) {
    TopAppBar(
        title = {
            Row {
                Text(text = "Configuración de ${dispositivo.nombre}")
                Spacer(modifier = Modifier.width(8.dp))
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary
        )
    )
}

@Composable
fun ConfiguracionControladorIluminacion(controlador: ControladorIluminacion) {
    Column {
        Text(text = "Configuración del Controlador de Iluminación")
        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Intensidad de luz actual: ${controlador.lx} lx")
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