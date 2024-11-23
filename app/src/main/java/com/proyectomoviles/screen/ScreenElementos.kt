package com.proyectomoviles.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.proyectomoviles.dispositivos.Dispositivo
import kotlinx.serialization.Serializable
import com.proyectomoviles.R
import com.proyectomoviles.dispositivos.ActuadorValvula
import com.proyectomoviles.dispositivos.CerraduraElectronica
import com.proyectomoviles.dispositivos.ControladorClima
import com.proyectomoviles.dispositivos.MedidorConsumoAgua
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
                    DispositivoCard(dispositivo)
                }
            }
        }
    }
}


@Composable
fun DispositivoCard(dispositivo : Dispositivo) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable {  },
        elevation = CardDefaults.elevatedCardElevation(12.dp),
        border = BorderStroke(1.dp, Color.Black),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.LightGray,
            contentColor = Color.Blue
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(dispositivo.nombre)
            Spacer(modifier = Modifier.size(8.dp))
            Text("Descripcion")
        }
    }
}


fun listaElementos():List<Dispositivo>{
    val listaDispositivo = listOf(
        SensorTemperatura("Sensor temperatura", "Sensor", "Cocina", R.drawable.imgtermometro,20.5, 45.9),
        SensorMovimiento("Sensor movimiento", "Sensor", "Dormitorio",R.drawable.imgsensormovimiento, false),
        SensorVibracion("Sensor Vibración", "Sensor", "Cuarto de baño",R.drawable.imgsensorvibracion, false),
        SensorNivelAgua("Sensor nivel de agua", "Sensor", "Cocina", R.drawable.imgsensornivelagua,10.3),
        SensorLuz("Sensor de luz", "Sensor", "Pasillo", R.drawable.imgsensorluz, false),
        SensorPresion("Sensor de presión", "Sensor", "Cocina",R.drawable.imgsensorpresion, 10.3),
        SensorApertura("Sensor de apertura", "Sensor", "Cocina", R.drawable.imgsensorapertura, false),
        SensorCalidadAire("Sensor de calidad del aire", "Sensor", "Baño", R.drawable.imgsensorcalidadaire,"Desfavorable"),
        ActuadorValvula("Actuador valvula", "Actuador", "Cocina", R.drawable.imgactuadorvalvula,true),
        ControladorClima("Controlador clima", "Monitoreo", "Cocina", R.drawable.imgcontroladorclima, 20.5, 45.9),
        MedidorConsumoAgua("Medidor de consumo de agua", "Monitoreo", "Baño", R.drawable.imgconsumoagua, 10.3),
        CerraduraElectronica("Cerradura electrónica", "Monitoreo", "Dormitorio", R.drawable.imgcerraduraelectronica, false)
    )
    return listaDispositivo
}