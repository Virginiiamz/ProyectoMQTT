package com.proyectomoviles.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.proyectomoviles.dispositivos.ActuadorValvula
import com.proyectomoviles.dispositivos.CerraduraElectronica
import com.proyectomoviles.dispositivos.ControladorClima
import com.proyectomoviles.dispositivos.ControladorIluminacion
import com.proyectomoviles.dispositivos.Dispositivo
import com.proyectomoviles.dispositivos.MedidorConsumoAgua
import com.proyectomoviles.dispositivos.MedidorGas
import com.proyectomoviles.dispositivos.SensorApertura
import com.proyectomoviles.dispositivos.SensorCalidadAire
import com.proyectomoviles.dispositivos.SensorGas
import com.proyectomoviles.dispositivos.SensorLuz
import com.proyectomoviles.dispositivos.SensorMovimiento
import com.proyectomoviles.dispositivos.SensorNivelAgua
import com.proyectomoviles.dispositivos.SensorPresion
import com.proyectomoviles.dispositivos.SensorTemperatura
import com.proyectomoviles.dispositivos.SensorVibracion

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
                is  SensorLuz -> ConfiguracionSensorLuz(dispositivo)
                is  SensorTemperatura -> ConfiguracionSensorTemperatura(dispositivo)
                is  SensorMovimiento -> ConfiguracionSensorMovimiento(dispositivo)
                is  SensorVibracion -> ConfiguracionSensorVibracion(dispositivo)
                is  SensorNivelAgua -> ConfiguracionSensorNivelAgua(dispositivo)
                is  SensorPresion -> ConfiguracionSensorPresion(dispositivo)
                is  SensorApertura -> ConfiguracionSensorApertura(dispositivo)
                is  SensorCalidadAire -> ConfiguracionSensorCalidadAire(dispositivo)
                is  ActuadorValvula -> ConfiguracionActuadorValvula(dispositivo)
                is  CerraduraElectronica -> ConfiguracionCerraduraElectronica(dispositivo)
                is ControladorClima -> ConfiguracionControladorClima(dispositivo)
                is MedidorConsumoAgua -> ConfiguracionMedidorConsumoAgua(dispositivo)
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







//SENSORES:

@Composable
fun ConfiguracionSensorLuz(sensorLuz: SensorLuz) {
    Column {
        Text(text = "Configuración del Sensor de Luz")
        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Estado: ${if (sensorLuz.estadoEncendido) "Encendido" else "Apagado"}")
        Spacer(modifier = Modifier.height(8.dp))

        val nuevoEstado = !sensorLuz.estadoEncendido
        Button(onClick = {
            sensorLuz.estadoEncendido = nuevoEstado
        }) {
            Text(text = if (sensorLuz.estadoEncendido) "Apagar" else "Encender")
        }
    }
}
@Composable
fun ConfiguracionSensorTemperatura(sensorTemperatura: SensorTemperatura) {
    Column {
        Text(text = "Configuración del Sensor de Temperatura")
        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Temperatura: ${sensorTemperatura.grados}°C")
        Text(text = "Humedad: ${sensorTemperatura.humedad}%")
    }
}
@Composable
fun ConfiguracionSensorMovimiento(sensorMovimiento: SensorMovimiento) {
    Column {
        Text(text = "Configuración del Sensor de Movimiento")
        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Estado: ${if (sensorMovimiento.estado) "Detectando movimiento" else "No detectando movimiento"}")
        Spacer(modifier = Modifier.height(8.dp))

        val nuevoEstado = !sensorMovimiento.estado
        Button(onClick = {
            sensorMovimiento.estado = nuevoEstado
        }) {
            Text(text = if (sensorMovimiento.estado) "Desactivar" else "Activar")
        }
    }
}
@Composable
fun ConfiguracionSensorVibracion(sensorVibracion: SensorVibracion) {
    Column {
        Text(text = "Configuración del Sensor de Vibración")
        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Estado: ${if (sensorVibracion.estado) "Vibración detectada" else "No detectada"}")
        Spacer(modifier = Modifier.height(8.dp))

        val nuevoEstado = !sensorVibracion.estado
        Button(onClick = {
            sensorVibracion.estado = nuevoEstado
        }) {
            Text(text = if (sensorVibracion.estado) "Desactivar" else "Activar")
        }
    }
}

@Composable
fun ConfiguracionSensorNivelAgua(sensorNivelAgua: SensorNivelAgua) {
    Column {
        Text(text = "Configuración del Sensor de Nivel de Agua")
        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Nivel de agua: ${sensorNivelAgua.litros} litros")
    }
}

@Composable
fun ConfiguracionSensorPresion(sensorPresion: SensorPresion) {
    Column {
        Text(text = "Configuración del Sensor de Presión")
        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Presión: ${sensorPresion.presion} bar")
    }
}

@Composable
fun ConfiguracionSensorApertura(sensorApertura: SensorApertura) {
    Column {
        Text(text = "Configuración del Sensor de Apertura")
        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Estado: ${if (sensorApertura.estado) "Apertura detectada" else "No detectada"}")
        Spacer(modifier = Modifier.height(8.dp))

        val nuevoEstado = !sensorApertura.estado
        Button(onClick = {
            sensorApertura.estado = nuevoEstado
        }) {
            Text(text = if (sensorApertura.estado) "Cerrar" else "Abrir")
        }
    }
}

@Composable
fun ConfiguracionSensorCalidadAire(sensorCalidadAire: SensorCalidadAire) {
    Column {
        Text(text = "Configuración del Sensor de Calidad del Aire")
        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Índice de Calidad del Aire: ${sensorCalidadAire.ICA}")
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

//ACTUADORES:

@Composable
fun ConfiguracionActuadorValvula(actuadorValvula: ActuadorValvula) {
    Column {
        Text(text = "Configuración del Actuador de Válvula")
        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Estado: ${if (actuadorValvula.activo) "Activo" else "Inactivo"}")
        Spacer(modifier = Modifier.height(8.dp))

        val nuevoEstado = !actuadorValvula.activo
        Button(onClick = {
            // Cambiar el estado del actuador
            actuadorValvula.activo = nuevoEstado
        }) {
            Text(text = if (actuadorValvula.activo) "Desactivar" else "Activar")
        }
    }
}

@Composable
fun ConfiguracionCerraduraElectronica(cerradura: CerraduraElectronica) {
    Column {
        Text(text = "Configuración de la Cerradura Electrónica")
        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Estado: ${if (cerradura.cerrado) "Cerrada" else "Abierta"}")
        Spacer(modifier = Modifier.height(8.dp))

        val nuevoEstado = !cerradura.cerrado
        Button(onClick = {
            // Cambiar el estado de la cerradura
            cerradura.cerrado = nuevoEstado
        }) {
            Text(text = if (cerradura.cerrado) "Abrir" else "Cerrar")
        }
    }
}

//MOnitoreo:

@Composable
fun ConfiguracionControladorIluminacion(controlador: ControladorIluminacion) {
    Column {
        Text(text = "Configuración del Controlador de Iluminación")
        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Estado: ${if (controlador.encendido) "Encendido" else "Apagado"}")
        Spacer(modifier = Modifier.height(8.dp))

        val nuevoEstado = !controlador.encendido
        Button(onClick = {
            // Cambiar el estado del controlador
            controlador.encendido = nuevoEstado
        }) {
            Text(text = if (controlador.encendido) "Apagar" else "Encender")
        }
    }
}

@Composable
fun ConfiguracionControladorClima(controlador: ControladorClima) {
    Column {
        Text(text = "Configuración del Controlador de Clima")
        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Temperatura actual: ${controlador.temperatura}°C")
        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Humedad actual: ${controlador.humedad}%")
    }
}

@Composable
fun ConfiguracionMedidorConsumoAgua(medidor: MedidorConsumoAgua) {
    Column {
        Text(text = "Configuración del Medidor de Consumo de Agua")
        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Consumo actual: ${medidor.litros} litros")
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