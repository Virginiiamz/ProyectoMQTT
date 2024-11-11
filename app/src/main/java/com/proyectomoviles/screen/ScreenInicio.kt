package com.proyectomoviles.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValue)
        ) {
            if (listaDispositivo.isEmpty()) {
                item {
                    Text("No hay dispositivos vinculados.")
                }
            } else {
                items(listaDispositivo) { dispositivo ->
                    Text(
                        text = when (dispositivo) {
                            is SensorTemperatura -> "Nombre: ${dispositivo.nombre}, Tipo: ${dispositivo.tipo}, Ubicación: ${dispositivo.ubicacion}, Temperatura: ${dispositivo.grados}, Humedad: ${dispositivo.humedad}"
                            is SensorMovimiento -> "Nombre: ${dispositivo.nombre}, Tipo: ${dispositivo.tipo}, Ubicación: ${dispositivo.ubicacion}, Detecta Movimiento: ${dispositivo.estado}"
                            else -> "Dispositivo desconocido"
                        },
                        modifier = Modifier.padding(8.dp)
                    )
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