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
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.text.style.TextAlign
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
import com.proyectomoviles.funciones.mostrarImagenByDispositivo
import okhttp3.internal.wait

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
                .padding(paddingValue)
                .width(500.dp),
            contentPadding = PaddingValues(1.dp),
            verticalArrangement = Arrangement.spacedBy(1.dp),
            horizontalArrangement = Arrangement.spacedBy(1.dp)
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
fun DispositivoCard(dispositivo: Dispositivo) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .padding(16.dp)
            .clickable { },
        elevation = CardDefaults.elevatedCardElevation(12.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
            contentColor = Color.Black
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            mostrarImagenByDispositivo(dispositivo)
            Spacer(modifier = Modifier.size(8.dp))
            Text(dispositivo.nombre, textAlign = TextAlign.Center)
        }
    }
}




fun listaElementos():List<Dispositivo>{
    val listaDispositivo = listOf(
        SensorTemperatura("Sensor temperatura", "Sensor", "Cocina", R.drawable.imgtermometro,0.00, 0.00),
        SensorMovimiento("Sensor movimiento", "Sensor", "Dormitorio",R.drawable.imgsensormovimiento, false),
        SensorVibracion("Sensor Vibración", "Sensor", "Cuarto de baño",R.drawable.imgsensorvibracion, false),
        SensorNivelAgua("Sensor nivel de agua", "Sensor", "Cocina", R.drawable.imgsensornivelagua,0.00),
        SensorLuz("Sensor de luz", "Sensor", "Pasillo", R.drawable.imgsensorluz, false),
        SensorPresion("Sensor de presión", "Sensor", "Cocina",R.drawable.imgsensorpresion, 0.00),
        SensorApertura("Sensor de apertura", "Sensor", "Cocina", R.drawable.imgsensorapertura, false),
        SensorCalidadAire("Sensor de calidad del aire", "Sensor", "Baño", R.drawable.imgsensorcalidadaire,""),
        ActuadorValvula("Actuador valvula", "Actuador", "Cocina", R.drawable.imgactuadorvalvula,false),
        ControladorClima("Controlador clima", "Monitoreo", "Cocina", R.drawable.imgcontroladorclima, 0.00, 0.00),
        MedidorConsumoAgua("Medidor de consumo de agua", "Monitoreo", "Baño", R.drawable.imgconsumoagua, 0.00),
        CerraduraElectronica("Cerradura electrónica", "Monitoreo", "Dormitorio", R.drawable.imgcerraduraelectronica, false)
    )
    return listaDispositivo
}