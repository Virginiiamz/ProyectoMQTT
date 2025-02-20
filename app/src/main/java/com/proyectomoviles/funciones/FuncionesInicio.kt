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
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import com.proyectomoviles.ui.theme.Naranja
import com.proyectomoviles.ui.theme.Purple40

@Composable
fun mostrarInformacionDispositivos(tipoDispositivo: String, nombre: String, imagen: Int, ubicacion: String, valor1: String, valor2: String) {

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
//                    .clickable {
//                        showDialog.value = true
//                    }
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
                    "sensortemperatura" -> mostrarSensorTemperatura(valor1, valor2)
                    "sensorluz" -> mostrarSensorLuz(valor1)
//                    is SensorMovimiento -> mostrarSensorMovimiento(dispositivo)
//                    is SensorVibracion -> mostrarSensorVibracion(dispositivo)
//                    is SensorNivelAgua -> mostrarSensorNivelAgua(dispositivo)
//                    is SensorLuz -> mostrarSensorLuz(dispositivo, valor1)
//                    is SensorPresion -> mostrarSensorPresion(dispositivo)
//                    is SensorApertura -> mostrarSensorApertura(dispositivo)
//                    is SensorCalidadAire -> mostrarSensorCalidadAire(dispositivo)
                }
            }
        }

    }
}

@Composable
fun mostrarSensorTemperatura(grados: String, humedad: String) {
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
fun mostrarSensorMovimiento(sensorMov: SensorMovimiento) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 6.dp),

        ) {
//        if (sensorMov.estado) {
//            Column(
//                modifier = Modifier
//                    .background(
//                        Color.Green,
//                        shape = RoundedCornerShape(bottomEnd = 8.dp, bottomStart = 8.dp)
//                    )
//                    .padding(8.dp)
//                    .weight(1f)
//                    .height(50.dp),
//                horizontalAlignment = Alignment.CenterHorizontally,
//                verticalArrangement = Arrangement.Center
//            ) {
//                Text(
//                    "Se ha detectado movimiento",
//                    color = Color.White,
//                    fontWeight = FontWeight.Medium
//                )
//            }
//        } else {
//            Column(
//                modifier = Modifier
//                    .background(
//                        Color.Red,
//                        shape = RoundedCornerShape(bottomEnd = 8.dp, bottomStart = 8.dp)
//                    )
//                    .padding(8.dp)
//                    .weight(1f)
//                    .height(50.dp),
//                horizontalAlignment = Alignment.CenterHorizontally,
//                verticalArrangement = Arrangement.Center
//            ) {
//                Text(
//                    "No se ha detectado movimiento",
//                    color = Color.White,
//                    fontWeight = FontWeight.Medium
//                )
//            }
//        }
    }
}

@Composable
fun mostrarSensorVibracion(sensorVib: SensorVibracion) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 6.dp),

        ) {
//        if (sensorVib.estado) {
//            Column(
//                modifier = Modifier
//                    .background(
//                        Color.Green,
//                        shape = RoundedCornerShape(bottomEnd = 8.dp, bottomStart = 8.dp)
//                    )
//                    .padding(8.dp)
//                    .weight(1f)
//                    .height(50.dp),
//                horizontalAlignment = Alignment.CenterHorizontally,
//                verticalArrangement = Arrangement.Center
//            ) {
//                Text(
//                    "Se han detectado vibraciones",
//                    color = Color.White,
//                    fontWeight = FontWeight.Medium
//                )
//            }
//        } else {
//            Column(
//                modifier = Modifier
//                    .background(
//                        Color.Red,
//                        shape = RoundedCornerShape(bottomEnd = 8.dp, bottomStart = 8.dp)
//                    )
//                    .padding(8.dp)
//                    .weight(1f)
//                    .height(50.dp),
//                horizontalAlignment = Alignment.CenterHorizontally,
//                verticalArrangement = Arrangement.Center
//            ) {
//                Text(
//                    "No se han detectado vibraciones",
//                    color = Color.White,
//                    fontWeight = FontWeight.Medium
//                )
//            }
//        }
    }
}

@Composable
fun mostrarSensorNivelAgua(sensorNivAgua: SensorNivelAgua) {
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
            Text("${sensorNivAgua.litros} L", color = Color.White, fontWeight = FontWeight.Medium)
        }

    }
}

@Composable
fun mostrarSensorLuz(encendido: String) {
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
fun mostrarSensorPresion(sensorPresion: SensorPresion) {
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
            Text("${sensorPresion.presion} Pa", color = Color.White, fontWeight = FontWeight.Medium)
        }

    }
}

@Composable
fun mostrarSensorApertura(sensorApertura: SensorApertura) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 6.dp),

        ) {
//        if (sensorApertura.estado) {
//            Column(
//                modifier = Modifier
//                    .background(
//                        Color.Green,
//                        shape = RoundedCornerShape(bottomEnd = 8.dp, bottomStart = 8.dp)
//                    )
//                    .padding(8.dp)
//                    .weight(1f)
//                    .height(50.dp),
//                horizontalAlignment = Alignment.CenterHorizontally,
//                verticalArrangement = Arrangement.Center
//            ) {
//                Text("La puerta está abierta", color = Color.White, fontWeight = FontWeight.Medium)
//            }
//        } else {
//            Column(
//                modifier = Modifier
//                    .background(
//                        Color.Red,
//                        shape = RoundedCornerShape(bottomEnd = 8.dp, bottomStart = 8.dp)
//                    )
//                    .padding(8.dp)
//                    .weight(1f)
//                    .height(50.dp),
//                horizontalAlignment = Alignment.CenterHorizontally,
//                verticalArrangement = Arrangement.Center
//            ) {
//                Text("La puerta está cerrada", color = Color.White, fontWeight = FontWeight.Medium)
//            }
//        }
    }
}

@Composable
fun mostrarSensorCalidadAire(sensorCalAire: SensorCalidadAire) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 6.dp),

        ) {

        var colorbackground = when (sensorCalAire.ICA) {
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
            when (sensorCalAire.ICA) {
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
            }
        }
    }
}

@Composable
fun mostrarActuadorValvula(ActValvula: ActuadorValvula) {
    var estadoActuador by remember { mutableStateOf(ActValvula.activo) }
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
//            Switch(
//                checked = estadoActuador,
//                onCheckedChange = { estadoActuador = !estadoActuador },
//                colors = SwitchDefaults.colors(
//                    checkedThumbColor = Color.White,
//                    checkedTrackColor = Color.Green,
//                    uncheckedThumbColor = Color.White,
//                    uncheckedTrackColor = Color.Red
//                )
//            )
        }
    }
}

@Composable
fun mostrarActuadorCerradura(ActCerradura: CerraduraElectronica) {
    var estadoCerradura by remember { mutableStateOf(ActCerradura.cerrado) }
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
//            Switch(
//                checked = estadoCerradura,
//                onCheckedChange = { estadoCerradura = !estadoCerradura },
//                colors = SwitchDefaults.colors(
//                    checkedThumbColor = Color.White,
//                    checkedTrackColor = Color.Green,
//                    uncheckedThumbColor = Color.White,
//                    uncheckedTrackColor = Color.Red
//                )
//            )
        }
    }
}

@Composable
fun mostrarControladorIluminacion(ContIluminacion: ControladorIluminacion) {
    var estadoiluminacion by remember { mutableStateOf(ContIluminacion.encendido) }
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
//            Switch(
//                checked = estadoiluminacion,
//                onCheckedChange = { estadoiluminacion = !estadoiluminacion },
//                colors = SwitchDefaults.colors(
//                    checkedThumbColor = Color.White,
//                    checkedTrackColor = Color.Green,
//                    uncheckedThumbColor = Color.White,
//                    uncheckedTrackColor = Color.Red
//                )
//            )
        }
    }
}

@Composable
fun mostrarControladorClima(ContClima: ControladorClima) {
    var temperatura by remember { mutableStateOf(ContClima.temperatura) }
    var humedad by remember { mutableStateOf(ContClima.humedad) }

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
                    Text("${temperatura} Cº", color = Color.White, fontWeight = FontWeight.Medium)
                }
//                Column {
//                    Icon(
//                        imageVector = Icons.Filled.KeyboardArrowUp,
//                        contentDescription = null,
//                        tint = Color.White,
//                        modifier = Modifier
//                            .clickable {
//                                temperatura++
//                            }
//                    )
//                    Icon(
//                        imageVector = Icons.Filled.KeyboardArrowDown,
//                        contentDescription = null,
//                        tint = Color.White,
//                        modifier = Modifier
//                            .clickable {
//                                temperatura--
//                            }
//                    )
//                }
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
//                Column {
//                    Icon(
//                        imageVector = Icons.Filled.KeyboardArrowUp,
//                        contentDescription = null,
//                        tint = Color.White,
//                        modifier = Modifier
//                        .clickable {
//                            humedad++
//                        }
//                    )
//                    Icon(
//                        imageVector = Icons.Filled.KeyboardArrowDown,
//                        contentDescription = null,
//                        tint = Color.White,
//                        modifier = Modifier
//                            .clickable {
//                                humedad--
//                            }
//                    )
//                }
            }
        }
    }
}

@Composable
fun mostrarConsumoAgua(MedAgua: MedidorConsumoAgua) {
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
            Text("${MedAgua.litros} L", color = Color.White, fontWeight = FontWeight.Medium)
        }

    }
}

@Composable
fun mostrarConsumoGas(ConsGas: MedidorGas) {
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
            Text("${ConsGas.metroscubicos} m3", color = Color.White, fontWeight = FontWeight.Medium)
        }

    }
}




