package com.proyectomoviles.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AcUnit
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Attribution
import androidx.compose.material.icons.filled.Help
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.proyectomoviles.dispositivos.Dispositivo
import com.proyectomoviles.dispositivos.SensorMovimiento
import com.proyectomoviles.dispositivos.SensorTemperatura


@Composable
fun InicioScreen() {
    val listaDispositivo = listOf(
        SensorTemperatura("Sensor temperatura", "Sensor", "Cocina", "", 20.5, 45.9),
        SensorMovimiento("Sensor movimiento", "Sensor", "Dormitorio", "", false),
        SensorTemperatura("Sensor temperatura", "Sensor", "Salón", "", 17.0, 22.3)
    )


    Scaffold(
        floatingActionButton = {
            MyFloatingActionButton()
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
                                mostrarIconoByDispositivo("AcUnit")
                            } else if (dispositivo is SensorMovimiento) {
                                mostrarIconoByDispositivo("Attribution")
                            }
                        }
                        Column()
                        {
                            if(dispositivo is SensorTemperatura){
                                mostrarSensorTemperatura(dispositivo)
                            }
                        }
                    }
                }
            }
        }
    }

}

@Composable
fun MyFloatingActionButton() {
    FloatingActionButton(
        onClick = {},
        containerColor = MaterialTheme.colorScheme.secondary
    ) {
        Icon(imageVector = Icons.Filled.Add, contentDescription = null)
    }
}

@Composable
fun mostrarIconoByDispositivo(icono: String) {
    val iconoSeleccionado = when(icono) {
        "AcUnit" -> Icons.Default.AcUnit
        "Attribution" -> Icons.Default.Attribution
        else -> Icons.Default.Help
    }

    Icon(
        imageVector = iconoSeleccionado,
        contentDescription = null,
        modifier = Modifier.size(60.dp)
    )
}

@Composable
fun mostrarSensorTemperatura(sensorTemp: SensorTemperatura) {
    Text(sensorTemp.nombre, modifier = Modifier.padding(start = 6.dp))
    Spacer(
        Modifier.height(1.dp)
    )
    Text(sensorTemp.ubicacion, modifier = Modifier.padding(start = 6.dp))
    Spacer(
        Modifier.height(1.dp)
    )
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
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("${sensorTemp.grados} Cº", color = Color.White, fontWeight = FontWeight.Medium)
        }
        Column(
            modifier = Modifier
                .background(Color.Red, shape = RoundedCornerShape(bottomEnd = 8.dp))
                .padding(8.dp)
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("${sensorTemp.humedad} %", color = Color.White, fontWeight = FontWeight.Medium)
        }
    }

}