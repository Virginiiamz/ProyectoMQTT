package com.proyectomoviles.funciones

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.proyectomoviles.dispositivos.ActuadorValvula
import com.proyectomoviles.dispositivos.CerraduraElectronica
import com.proyectomoviles.dispositivos.ControladorClima
import com.proyectomoviles.dispositivos.ControladorIluminacion
import com.proyectomoviles.dispositivos.Dispositivo
import com.proyectomoviles.dispositivos.MedidorConsumoAgua
import com.proyectomoviles.dispositivos.MedidorGas
import com.proyectomoviles.dispositivos.SensorApertura
import com.proyectomoviles.dispositivos.SensorCalidadAire
import com.proyectomoviles.dispositivos.SensorLuz
import com.proyectomoviles.dispositivos.SensorMovimiento
import com.proyectomoviles.dispositivos.SensorNivelAgua
import com.proyectomoviles.dispositivos.SensorPresion
import com.proyectomoviles.dispositivos.SensorTemperatura
import com.proyectomoviles.dispositivos.SensorVibracion
import com.proyectomoviles.screen.InicioViewModel
import com.proyectomoviles.services.MqttService
import com.proyectomoviles.ui.theme.Naranja
import com.proyectomoviles.ui.theme.Purple40

@Composable
fun mostrarInformacionDispositivos(
    id: String,
    tipoDispositivo: String,
    nombre: String,
    imagen: Int,
    ubicacion: String,
    token1: String,
    token2: String,
    inicioViewModel: InicioViewModel,
    mqttService: MqttService,
    navigateToInicio: () -> Unit
) {
    var mostrarDialogo by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .width(400.dp)
            .border(
                border = BorderStroke(2.dp, Color.LightGray),
                shape = RoundedCornerShape(8.dp),
            )
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(8.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Close, // Usa un ícono adecuado, como "Close"
                contentDescription = "Cerrar",
                tint = Color.Gray,
                modifier = Modifier
                    .size(24.dp)
                    .clickable {
                        mostrarDialogo = true
                    }
            )
        }

        if (mostrarDialogo) {
            mostrarEliminar(
                tipoDispositivo = tipoDispositivo,
                id = id,
                inicioViewModel = inicioViewModel,
                onDismiss = { mostrarDialogo = false },
                navigateToInicio
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 6.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.padding(6.dp)
                    ) {
                        Image(
                            painterResource(id = imagen),
                            contentDescription = nombre,
                            modifier = Modifier.padding(top = 6.dp, bottom = 6.dp)

                        )
                    }
                }
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .height(125.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        nombre,
                        modifier = Modifier.padding(start = 6.dp),
                        textAlign = TextAlign.Center
                    )
                    Spacer(
                        Modifier.height(1.dp)
                    )
                    Text(ubicacion, modifier = Modifier.padding(start = 6.dp))
                    Spacer(
                        Modifier.height(1.dp)
                    )
                }
            }

            Column {
                when (tipoDispositivo) {
                    "sensortemperatura" -> mostrarSensorTemperatura(token1, token2, mqttService)
                    "sensorluz" -> mostrarSensorLuz(token1, mqttService)
                    "sensormovimiento" -> mostrarSensorMovimiento(token1, mqttService)
                    "sensorvibracion" -> mostrarSensorVibracion(token1, mqttService)
                    "sensornivelagua" -> mostrarSensorNivelAgua(token1, mqttService)
                    "sensorpresion" -> mostrarSensorPresion(token1, mqttService)
                    "sensorapertura" -> mostrarSensorApertura(token1, mqttService)
                    "sensorcalidadaire" -> mostrarSensorCalidadAire(token1, mqttService)
                    "actuadorvalvula" -> mostrarActuadorValvula(token1, mqttService)
                    "cerraduraelectronica" -> mostrarActuadorCerradura(token1, mqttService)
                    "controladoriluminacion" -> mostrarControladorIluminacion(token1, mqttService)
                    "controladorclima" -> mostrarControladorClima(token1, token2, mqttService)
                    "medidorconsumoagua" -> mostrarConsumoAgua(token1, mqttService)
                    "medidorgas" -> mostrarConsumoGas(token1, mqttService)
                }
            }
        }

    }
}

@Composable
fun mostrarSensorTemperatura(token1: String, token2: String, mqttService: MqttService) {

    var grados by remember { mutableDoubleStateOf(0.0) }
    var humedad by remember { mutableDoubleStateOf(0.0) }

    mqttService.subscribe(token1) {
        grados = it.toDouble()
    }

    mqttService.subscribe(token2) {
        humedad = it.toDouble()
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 6.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Column(
            modifier = Modifier
                .background(Color.Blue, shape = RoundedCornerShape(bottomStart = 8.dp))
                .padding(8.dp)
                .weight(1f)
                .height(50.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("${grados} Cº", color = Color.White, fontWeight = FontWeight.Medium)
        }
        Column(
            modifier = Modifier
                .background(Color.Red, shape = RoundedCornerShape(bottomEnd = 8.dp))
                .padding(8.dp)
                .weight(1f)
                .height(50.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("${humedad} %", color = Color.White, fontWeight = FontWeight.Medium)
        }

    }
}


@Composable
fun mostrarSensorMovimiento(token: String, mqttService: MqttService) {

    var estado by remember { mutableStateOf("") }

    mqttService.subscribe(token) {
        estado = it
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 6.dp),

        ) {
        if (estado.toBoolean()) {
            Column(
                modifier = Modifier
                    .background(
                        Color.Green,
                        shape = RoundedCornerShape(bottomEnd = 8.dp, bottomStart = 8.dp)
                    )
                    .padding(8.dp)
                    .weight(1f)
                    .height(50.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    "Se ha detectado movimiento",
                    color = Color.White,
                    fontWeight = FontWeight.Medium
                )
            }
        } else {
            Column(
                modifier = Modifier
                    .background(
                        Color.Red,
                        shape = RoundedCornerShape(bottomEnd = 8.dp, bottomStart = 8.dp)
                    )
                    .padding(8.dp)
                    .weight(1f)
                    .height(50.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    "No se ha detectado movimiento",
                    color = Color.White,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

@Composable
fun mostrarSensorVibracion(token: String, mqttService: MqttService) {
    var estado by remember { mutableStateOf("") }

    mqttService.subscribe(token) {
        estado = it
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 6.dp),

        ) {
        if (estado.toBoolean()) {
            Column(
                modifier = Modifier
                    .background(
                        Color.Green,
                        shape = RoundedCornerShape(bottomEnd = 8.dp, bottomStart = 8.dp)
                    )
                    .padding(8.dp)
                    .weight(1f)
                    .height(50.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    "Se han detectado vibraciones",
                    color = Color.White,
                    fontWeight = FontWeight.Medium
                )
            }
        } else {
            Column(
                modifier = Modifier
                    .background(
                        Color.Red,
                        shape = RoundedCornerShape(bottomEnd = 8.dp, bottomStart = 8.dp)
                    )
                    .padding(8.dp)
                    .weight(1f)
                    .height(50.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    "No se han detectado vibraciones",
                    color = Color.White,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

@Composable
fun mostrarSensorNivelAgua(token: String, mqttService: MqttService) {
    var litros by remember { mutableDoubleStateOf(0.0) }

    mqttService.subscribe(token) {
        litros = it.toDouble()
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 6.dp),
    ) {
        Column(
            modifier = Modifier
                .background(
                    Color.Blue,
                    shape = RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp)
                )
                .padding(8.dp)
                .weight(1f)
                .height(50.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("${litros} L", color = Color.White, fontWeight = FontWeight.Medium)
        }

    }
}

@Composable
fun mostrarSensorLuz(token: String, mqttService: MqttService) {

    var encendido by remember { mutableStateOf("") }

    mqttService.subscribe(token) {
        encendido = it
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 6.dp),
    ) {
        if (encendido.toBoolean()) {
            Column(
                modifier = Modifier
                    .background(
                        Color.Green,
                        shape = RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp)
                    )
                    .padding(8.dp)
                    .weight(1f)
                    .height(50.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text("Se han detectado luz", color = Color.White, fontWeight = FontWeight.Medium)
            }
        } else {
            Column(
                modifier = Modifier
                    .background(
                        Color.Red,
                        shape = RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp)
                    )
                    .padding(8.dp)
                    .weight(1f)
                    .height(50.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text("No se han detectado luz", color = Color.White, fontWeight = FontWeight.Medium)
            }
        }

    }
}

@Composable
fun mostrarSensorPresion(token: String, mqttService: MqttService) {

    var presion by remember { mutableDoubleStateOf(0.0) }

    mqttService.subscribe(token) {
        presion = it.toDouble()
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 6.dp),
    ) {
        Column(
            modifier = Modifier
                .background(
                    Color.Gray,
                    shape = RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp)
                )
                .padding(8.dp)
                .weight(1f)
                .height(50.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("${presion} Pa", color = Color.White, fontWeight = FontWeight.Medium)
        }

    }
}

@Composable
fun mostrarSensorApertura(token: String, mqttService: MqttService) {

    var estado by remember { mutableStateOf("") }

    mqttService.subscribe(token) {
        estado = it
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 6.dp),

        ) {
        if (estado.toBoolean()) {
            Column(
                modifier = Modifier
                    .background(
                        Color.Green,
                        shape = RoundedCornerShape(bottomEnd = 8.dp, bottomStart = 8.dp)
                    )
                    .padding(8.dp)
                    .weight(1f)
                    .height(50.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text("La puerta está abierta", color = Color.White, fontWeight = FontWeight.Medium)
            }
        } else {
            Column(
                modifier = Modifier
                    .background(
                        Color.Red,
                        shape = RoundedCornerShape(bottomEnd = 8.dp, bottomStart = 8.dp)
                    )
                    .padding(8.dp)
                    .weight(1f)
                    .height(50.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text("La puerta está cerrada", color = Color.White, fontWeight = FontWeight.Medium)
            }
        }
    }
}

@Composable
fun mostrarSensorCalidadAire(token: String, mqttService: MqttService) {

    var tipoCalidad by remember { mutableStateOf("") }

    mqttService.subscribe(token) {
        tipoCalidad = it
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 6.dp),

        ) {

        var colorbackground = when (tipoCalidad) {
            "Bueno" -> Color.Green
            "Moderado" -> Color.Yellow
            "Desfavorable" -> Naranja
            "Dañinoso" -> Color.Red
            "Muy dañino" -> Purple40
            else -> Color.White
        }

        Column(
            modifier = Modifier
                .background(
                    colorbackground,
                    shape = RoundedCornerShape(bottomEnd = 8.dp, bottomStart = 8.dp)
                )
                .padding(8.dp)
                .weight(1f)
                .height(50.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            when (tipoCalidad) {
                "Bueno" -> Text(
                    "La calidad del aire es buena",
                    color = Color.White,
                    fontWeight = FontWeight.Medium
                )

                "Moderado" -> Text(
                    "La calidad del aire es moderada",
                    color = Color.White,
                    fontWeight = FontWeight.Medium
                )

                "Desfavorable" -> Text(
                    "La calidad del aire es desfavorable",
                    color = Color.White,
                    fontWeight = FontWeight.Medium
                )

                "Dañinoso" -> Text(
                    "La calidad del aire es dañina",
                    color = Color.White,
                    fontWeight = FontWeight.Medium
                )

                "Muy dañino" -> Text(
                    "La calidad del aire es muy dañina",
                    color = Color.White,
                    fontWeight = FontWeight.Medium
                )

                else -> Text(
                    "No se ha detectado la calidad del aire",
                    color = Color.Red,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

@Composable
fun mostrarActuadorValvula(token: String, mqttService: MqttService) {

    var estado by remember { mutableStateOf("") }

    mqttService.subscribe(token) {
        estado = it
    }

    var estadoActuador by remember { mutableStateOf(estado.toBoolean()) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 6.dp),

        ) {
        Column(
            modifier = Modifier
                .background(
                    Color.Black,
                    shape = RoundedCornerShape(bottomEnd = 8.dp, bottomStart = 8.dp)
                )
                .padding(8.dp)
                .weight(1f)
                .height(50.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,


            ) {
            Switch(
                checked = estadoActuador,
                onCheckedChange = { estadoActuador = !estadoActuador },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.White,
                    checkedTrackColor = Color.Green,
                    uncheckedThumbColor = Color.White,
                    uncheckedTrackColor = Color.Red
                )
            )
        }
    }
}

@Composable
fun mostrarActuadorCerradura(token: String, mqttService: MqttService) {
    var estado by remember { mutableStateOf("") }

    mqttService.subscribe(token) {
        estado = it
    }

    var estadoCerradura by remember { mutableStateOf(estado.toBoolean()) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 6.dp),

        ) {
        Column(
            modifier = Modifier
                .background(
                    Color.Black,
                    shape = RoundedCornerShape(bottomEnd = 8.dp, bottomStart = 8.dp)
                )
                .padding(8.dp)
                .weight(1f)
                .height(50.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,


            ) {
            Switch(
                checked = estadoCerradura,
                onCheckedChange = { estadoCerradura = !estadoCerradura },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.White,
                    checkedTrackColor = Color.Green,
                    uncheckedThumbColor = Color.White,
                    uncheckedTrackColor = Color.Red
                )
            )
        }
    }
}

@Composable
fun mostrarControladorIluminacion(token: String, mqttService: MqttService) {
    var estado by remember { mutableStateOf("") }

    mqttService.subscribe(token) {
        estado = it
    }

    var estadoiluminacion by remember { mutableStateOf(estado.toBoolean()) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 6.dp),

        ) {
        Column(
            modifier = Modifier
                .background(
                    Color.Black,
                    shape = RoundedCornerShape(bottomEnd = 8.dp, bottomStart = 8.dp)
                )
                .padding(8.dp)
                .weight(1f)
                .height(50.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,


            ) {
            Switch(
                checked = estadoiluminacion,
                onCheckedChange = { estadoiluminacion = !estadoiluminacion },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.White,
                    checkedTrackColor = Color.Green,
                    uncheckedThumbColor = Color.White,
                    uncheckedTrackColor = Color.Red
                )
            )
        }
    }
}

@Composable
fun mostrarControladorClima(token1: String, token2: String, mqttService: MqttService) {
    var grados by remember { mutableDoubleStateOf(0.0) }
    var humedad by remember { mutableDoubleStateOf(0.0) }

    mqttService.subscribe(token1) {
        grados = it.toDouble()
    }

    mqttService.subscribe(token2) {
        humedad = it.toDouble()
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 6.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Column(
            modifier = Modifier
                .background(Color.Blue, shape = RoundedCornerShape(bottomStart = 8.dp))
                .padding(8.dp)
                .weight(1f)
                .height(50.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text("${grados} Cº", color = Color.White, fontWeight = FontWeight.Medium)
                }
            }
        }
        Column(
            modifier = Modifier
                .background(Color.Red, shape = RoundedCornerShape(bottomEnd = 8.dp))
                .padding(8.dp)
                .weight(1f)
                .height(50.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text("${humedad} %", color = Color.White, fontWeight = FontWeight.Medium)
                }
            }
        }
    }
}

@Composable
fun mostrarConsumoAgua(token: String, mqttService: MqttService) {
    var litros by remember { mutableDoubleStateOf(0.0) }


    mqttService.subscribe(token) {
        litros = it.toDouble()
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 6.dp),
    ) {
        Column(
            modifier = Modifier
                .background(
                    Color.Blue,
                    shape = RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp)
                )
                .padding(8.dp)
                .weight(1f)
                .height(50.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("${litros} L", color = Color.White, fontWeight = FontWeight.Medium)
        }

    }
}

@Composable
fun mostrarConsumoGas(token: String, mqttService: MqttService) {

    var m3toString by remember { mutableDoubleStateOf(0.0) }

    mqttService.subscribe(token) {
        m3toString = it.toDouble()
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 6.dp),
    ) {
        Column(
            modifier = Modifier
                .background(
                    Color.Gray,
                    shape = RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp)
                )
                .padding(8.dp)
                .weight(1f)
                .height(50.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("${m3toString} m3", color = Color.White, fontWeight = FontWeight.Medium)
        }

    }
}

@Composable
fun mostrarEliminar(
    tipoDispositivo: String,
    id: String,
    inicioViewModel: InicioViewModel,
    onDismiss: () -> Unit,
    navigateToInicio: () -> Unit
) {

    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text("Eliminar dispositivo") },
        text = { Text("¿Estás seguro de que deseas eliminar el dispositivo '${tipoDispositivo}'?") },
        confirmButton = {
            Text(
                "Confirmar",
                modifier = Modifier.clickable {
                    onDismiss()
                    inicioViewModel.deleteDispositivoById(id, tipoDispositivo)
                    navigateToInicio()
                },
                color = MaterialTheme.colorScheme.primary
            )
        },
        dismissButton = {
            Text(
                "Cancelar",
                modifier = Modifier.clickable { onDismiss() },
                color = MaterialTheme.colorScheme.secondary
            )
        }
    )
}



