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
import com.proyectomoviles.R
import com.proyectomoviles.dispositivos.Dispositivo
import com.proyectomoviles.dispositivos.SensorLuz
import com.proyectomoviles.dispositivos.SensorMovimiento
import com.proyectomoviles.dispositivos.SensorNivelAgua
import com.proyectomoviles.dispositivos.SensorTemperatura
import com.proyectomoviles.dispositivos.SensorVibracion
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
        SensorLuz("Sensor de luz", "Sensor", "Pasillo", false)
    )


    Scaffold(
        floatingActionButton = {
            MyFloatingActionButton(navigateToElementos)
        },
        floatingActionButtonPosition = FabPosition.Start
    ) {
        paddingValue ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
               .fillMaxSize()
               .padding(paddingValue)
               .padding(6.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),

        ) {
            if (listaDispositivo.isEmpty()) {
                item {
                    Text("No hay dispositivos vinculados.")
                }
            } else {
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
                            }
                        }
                        Column()
                        {
                            mostrarDispositivo(dispositivo)
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
        onClick = {navigateToElementos()},
        containerColor = MaterialTheme.colorScheme.secondary
    ) {
        Icon(imageVector = Icons.Filled.Add, contentDescription = null)
    }
}

@Composable
fun mostrarImagenByDispositivo(nombreImagen: String) {

    when(nombreImagen) {
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
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 6.dp),

    ){
        if (sensorMov.estado) {
            Column(
                modifier = Modifier
                    .background(Color.Green, shape = RoundedCornerShape(bottomEnd = 8.dp, bottomStart = 8.dp))
                    .padding(8.dp)
                    .weight(1f)
                    .height(50.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text("Se ha detectado movimiento", color = Color.White, fontWeight = FontWeight.Medium)
            }
        } else {
            Column(
                modifier = Modifier
                    .background(Color.Red, shape = RoundedCornerShape(bottomEnd = 8.dp, bottomStart = 8.dp))
                    .padding(8.dp)
                    .weight(1f)
                    .height(50.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text("No se ha detectado movimiento", color = Color.White, fontWeight = FontWeight.Medium)
            }
        }
    }
}

@Composable
fun mostrarSensorVibracion(sensorVib: SensorVibracion) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 6.dp),

        ){
        if (sensorVib.estado) {
            Column(
                modifier = Modifier
                    .background(Color.Green, shape = RoundedCornerShape(bottomEnd = 8.dp, bottomStart = 8.dp))
                    .padding(8.dp)
                    .weight(1f)
                    .height(50.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text("Se han detectado vibraciones", color = Color.White, fontWeight = FontWeight.Medium)
            }
        } else {
            Column(
                modifier = Modifier
                    .background(Color.Red, shape = RoundedCornerShape(bottomEnd = 8.dp, bottomStart = 8.dp))
                    .padding(8.dp)
                    .weight(1f)
                    .height(50.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text("No se han detectado vibraciones", color = Color.White, fontWeight = FontWeight.Medium)
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
                .background(Color.Blue, shape = RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp))
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
fun mostrarSensorLuz(sensorLuz: SensorLuz){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 6.dp),
    ) {
        if (sensorLuz.estadoEncendido){
            Column(
                modifier = Modifier
                    .background(Color.Green, shape = RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp))
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
                    .background(Color.Red, shape = RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp))
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