package com.proyectomoviles.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.proyectomoviles.dispositivos.Dispositivo
import kotlinx.serialization.Serializable
import com.proyectomoviles.R
import com.proyectomoviles.dispositivos.ActuadorValvula
import com.proyectomoviles.dispositivos.SensorApertura
import com.proyectomoviles.dispositivos.SensorCalidadAire
import com.proyectomoviles.dispositivos.SensorLuz
import com.proyectomoviles.dispositivos.SensorMovimiento
import com.proyectomoviles.dispositivos.SensorNivelAgua
import com.proyectomoviles.dispositivos.SensorPresion
import com.proyectomoviles.dispositivos.SensorTemperatura
import com.proyectomoviles.dispositivos.SensorVibracion

@Serializable
object Elementos

@Composable
fun ElementosScreen() {
    val dispositivos = listaElementos()

    val dispositivoImagenMap = mapOf(
        "Sensor de temperatura" to R.drawable.imgtermometro,
        "Sensor de luz" to R.drawable.imgsensorluz,
        "Sensor de movimiento" to R.drawable.imgsensormovimiento,
        "Sensor de vibracion" to R.drawable.imgsensorvibracion
    )

    Scaffold(
        floatingActionButton = { },
    ) { paddingValue ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValue),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            if (dispositivos.isEmpty()) {
                item {
                    Text("No hay dispositivos vinculados")
                }
            } else {
                items(dispositivos) { dispositivo ->
                    //val imageRes = dispositivoImagenMap[dispositivo] ?: R.drawable.error
                    DispositivoButton(nombre = dispositivo.nombre)
                }
            }
        }
    }
}


@Composable
fun DispositivoButton(nombre: String) {
    Button(
        onClick = { },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        contentPadding = PaddingValues(vertical = 8.dp)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            /*androidx.compose.foundation.Image(
                painter = painterResource(id = imageRes),
                contentDescription = "Icono de $nombre",
                modifier = Modifier.size(48.dp)
            )*/
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = nombre)
        }
    }
}


fun listaElementos():List<Dispositivo>{
    val listaDispositivo = listOf(
        SensorTemperatura("Sensor temperatura", "Sensor", "Cocina", 20.5, 45.9),
        SensorMovimiento("Sensor movimiento", "Sensor", "Dormitorio", false),
        SensorTemperatura("Sensor temperatura", "Sensor", "Salón", 17.0, 22.3),
        SensorVibracion("Sensor Vibración", "Sensor", "Cuarto de baño", false),
        SensorNivelAgua("Sensor nivel de agua", "Sensor", "Cocina", 10.3),
        SensorLuz("Sensor de luz", "Sensor", "Pasillo", false),
        SensorPresion("Sensor de presión", "Sensor", "Cocina", 10.3),
        SensorApertura("Sensor de apertura", "Sensor", "Cocina", false),
        SensorCalidadAire("Sensor de calidad del aire", "Sensor", "Baño", "Desfavorable"),
        ActuadorValvula("Actuador valvula", "Actuador", "Cocina", true),
    )
    return listaDispositivo
}