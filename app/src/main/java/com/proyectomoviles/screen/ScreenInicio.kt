package com.proyectomoviles.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.proyectomoviles.R
import com.proyectomoviles.dispositivos.Dispositivo
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
import kotlinx.serialization.Serializable


@Serializable
object Inicio

@Composable
fun InicioScreen(navigateToElementos: () -> Unit) {
    val listaDispositivo = listOf(
        SensorTemperatura("Sensor temperatura", "Sensor", "Cocina", 20.5, 45.9),
        SensorMovimiento("Sensor movimiento", "Sensor", "Dormitorio", false),
        SensorTemperatura("Sensor temperatura", "Sensor", "Salón", 17.0, 22.3),
        SensorVibracion("Sensor Vibración", "Sensor", "Cuarto de baño", false),
        SensorNivelAgua("Sensor nivel de agua", "Sensor", "Cocina", 10.3),
        SensorLuz("Sensor de luz", "Sensor", "Pasillo", false),
        SensorPresion("Sensor de presión", "Sensor", "Cocina", 10.3),
        SensorApertura("Sensor de apertura", "Sensor", "Cocina", false),
        SensorCalidadAire("Sensor de calidad del aire", "Sensor", "Baño", "Desfavorable")
    )

    var contadorSensor = 0
    var contadorActuador = 0
    var contadorMonitoreo = 0

    listaDispositivo.forEach { dispositivo ->
        if (dispositivo.tipo == "Sensor") {
            contadorSensor++
        }
        if (dispositivo.tipo == "Actuador") {
            contadorActuador++
        }
        if (dispositivo.tipo == "Monitoreo") {
            contadorMonitoreo++
        }
    }


    Scaffold(
        floatingActionButton = {
            MyFloatingActionButton(navigateToElementos)
        },
        floatingActionButtonPosition = FabPosition.Start
    ) { paddingValue ->
        if (listaDispositivo.isEmpty()) {
            Text("No hay dispositivos vinculados.")
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(1),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValue)
                    .padding(6.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),

                ) {
                item {
                    if (contadorSensor > 0) {
                        Text("Sensores", modifier = Modifier.padding(top = 16.dp, bottom = 6.dp), fontWeight = FontWeight.Bold, fontSize = 24.sp)
                    }
                }

                items(listaDispositivo) { dispositivo ->
                    Column(
                        modifier = Modifier
                            .width(400.dp)
                            .border(
                                border = BorderStroke(2.dp, Color.LightGray),
                                shape = RoundedCornerShape(8.dp),
                            ),


                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.padding(6.dp)
                        ) {
                            if (dispositivo is SensorTemperatura) {
                                mostrarImagenByDispositivo("imgtemperatura")
                            } else if (dispositivo is SensorMovimiento) {
                                mostrarImagenByDispositivo("imgmovimiento")
                            } else if (dispositivo is SensorVibracion) {
                                mostrarImagenByDispositivo("imgvibracion")
                            } else if (dispositivo is SensorNivelAgua) {
                                mostrarImagenByDispositivo("imgnivelagua")
                            } else if (dispositivo is SensorLuz) {
                                mostrarImagenByDispositivo("imgluz")
                            } else if (dispositivo is SensorPresion) {
                                mostrarImagenByDispositivo("imgpresion")
                            } else if (dispositivo is SensorApertura) {
                                mostrarImagenByDispositivo("imgapertura")
                            } else if (dispositivo is SensorCalidadAire) {
                                mostrarImagenByDispositivo("imgcalidadaire")
                            }
                        }
                        Column()
                        {
                            mostrarDispositivo(dispositivo)
                        }
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(60.dp).fillMaxWidth())
                }

                item {
                    if (contadorActuador > 0) {
                        Text("Actuadores", modifier = Modifier.padding(top = 16.dp, bottom = 6.dp), fontWeight = FontWeight.Bold, fontSize = 24.sp)
                    }
                }

                item {
                    if (contadorMonitoreo > 0) {
                        if (contadorMonitoreo > 0) {
                            Text("Monitoreo y control complejo", modifier = Modifier.padding(top = 16.dp, bottom = 6.dp), fontWeight = FontWeight.Bold, fontSize = 24.sp)
                        }
                    }
                }
            }
        }

    }

}


@Composable
fun MyFloatingActionButton(navigateToElementos: () -> Unit) {
    FloatingActionButton(
        onClick = { navigateToElementos() },
        containerColor = MaterialTheme.colorScheme.secondary
    ) {
        Icon(imageVector = Icons.Filled.Add, contentDescription = null)
    }
}

@Composable
fun mostrarImagenByDispositivo(nombreImagen: String) {

    when (nombreImagen) {
        "imgtemperatura" -> {
            Image(
                painterResource(id = R.drawable.imgtermometro),
                contentDescription = "Sensor termometro"
            )
        }

        "imgmovimiento" -> {
            Image(
                painterResource(id = R.drawable.imgsensormovimiento),
                contentDescription = "Sensor movimiento"
            )
        }

        "imgvibracion" -> {
            Image(
                painterResource(id = R.drawable.imgsensorvibracion),
                contentDescription = "Sensor vibracion"
            )
        }

        "imgnivelagua" -> {
            Image(
                painterResource(id = R.drawable.imgsensornivelagua),
                contentDescription = "Sensor nivel de agua"
            )
        }

        "imgluz" -> {
            Image(
                painterResource(id = R.drawable.imgsensorluz),
                contentDescription = "Sensor de luz"
            )
        }

        "imgpresion" -> {
            Image(
                painterResource(id = R.drawable.imgsensorpresion),
                contentDescription = "Sensor de presión"
            )
        }

        "imgapertura" -> {
            Image(
                painterResource(id = R.drawable.imgsensorapertura),
                contentDescription = "Sensor de apertura"
            )
        }

        "imgcalidadaire" -> {
            Image(
                painterResource(id = R.drawable.imgsensorcalidadaire),
                contentDescription = "Sensor de calidad del aire"
            )
        }

        else -> ""
    }
}

@Composable
fun mostrarDispositivo(dispositivo: Dispositivo) {
    Text(dispositivo.nombre, modifier = Modifier.padding(start = 6.dp))
    Spacer(
        Modifier.height(1.dp)
    )
    Text(dispositivo.ubicacion, modifier = Modifier.padding(start = 6.dp))
    Spacer(
        Modifier.height(1.dp)
    )

    when (dispositivo) {
        is SensorTemperatura -> mostrarSensorTemperatura(dispositivo)
        is SensorMovimiento -> mostrarSensorMovimiento(dispositivo)
        is SensorVibracion -> mostrarSensorVibracion(dispositivo)
        is SensorNivelAgua -> mostrarSensorNivelAgua(dispositivo)
        is SensorLuz -> mostrarSensorLuz(dispositivo)
        is SensorPresion -> mostrarSensorPresion(dispositivo)
        is SensorApertura -> mostrarSensorApertura(dispositivo)
        is SensorCalidadAire -> mostrarSensorCalidadAire(dispositivo)
    }
}

@Composable
fun mostrarSensorTemperatura(sensorTemp: SensorTemperatura) {
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
            Text("${sensorTemp.grados} Cº", color = Color.White, fontWeight = FontWeight.Medium)
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
            Text("${sensorTemp.humedad} %", color = Color.White, fontWeight = FontWeight.Medium)
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
        if (sensorMov.estado) {
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
fun mostrarSensorVibracion(sensorVib: SensorVibracion) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 6.dp),

        ) {
        if (sensorVib.estado) {
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
fun mostrarSensorLuz(sensorLuz: SensorLuz) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 6.dp),
    ) {
        if (sensorLuz.estadoEncendido) {
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
        if (sensorApertura.estado) {
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