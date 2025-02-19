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
import com.proyectomoviles.dispositivos.ControladorIluminacion
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
import com.proyectomoviles.funciones.mostrarImagenByDispositivo
import okhttp3.internal.wait

@Composable
fun ElementosScreen(onNavigateToConfiguracion: (Dispositivo) -> Unit) {
    val dispositivos = listaElementos()

    val dispositivoImagenMap = mapOf(
        "Sensor de temperatura" to R.drawable.imgsensortermometro,
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
                    DispositivoCard(dispositivo){
                        onNavigateToConfiguracion(dispositivo)
                    }

                }
            }
        }
    }
}


@Composable
fun DispositivoCard(dispositivo: Dispositivo, onNavigateToConfiguracion: (String) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .padding(16.dp)
            .clickable { onNavigateToConfiguracion(dispositivo.nombre)},
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
        SensorTemperatura(null ,null,"Sensor Temperatura", "Sensor", "Cocina", R.drawable.imgsensortermometro,0.00, 0.00),
        SensorMovimiento(null ,null,"Sensor Movimiento", "Sensor", "Dormitorio",R.drawable.imgsensormovimiento, false),
        SensorVibracion(null ,null,"Sensor Vibración", "Sensor", "Cuarto de baño",R.drawable.imgsensorvibracion, false),
        SensorNivelAgua(null ,null,"Sensor Nivel de Agua", "Sensor", "Cocina", R.drawable.imgsensornivelagua,0.00),
        SensorLuz(null ,null,"Sensor de luz", "Sensor", "Pasillo", R.drawable.imgsensorluz, false),
        SensorPresion(null ,null,"Sensor de Presión", "Sensor", "Cocina",R.drawable.imgsensorpresion, 0.00),
        SensorApertura(null ,null,"Sensor de Apertura", "Sensor", "Cocina", R.drawable.imgsensorapertura, false),
        SensorCalidadAire(null ,null,"Sensor de Calidad del Aire", "Sensor", "Baño", R.drawable.imgsensorcalidadaire,""),
        ActuadorValvula(null ,null,"Actuador Valvula", "Actuador", "Cocina", R.drawable.imgactuadorvalvula,false),
        ControladorClima(null ,null,"Controlador Clima", "Monitoreo", "Cocina", R.drawable.imgcontroladorclima, 0.00, 0.00),
        MedidorConsumoAgua(null ,null,"Medidor de Consumo de Agua", "Monitoreo", "Baño", R.drawable.imgconsumoagua, 0.00),
        CerraduraElectronica(null ,null,"Cerradura Electrónica", "Monitoreo", "Dormitorio", R.drawable.imgcerraduraelectronica, false),
        MedidorGas(null ,null,"Medidor de gas", "Monitoreo", "Baño", R.drawable.imgconsumogas, 0.00),
        ControladorIluminacion(null ,null,"Controlador Iluminación", "Actuador", "", R.drawable.imgcontroladorluz, false),
    )
    return listaDispositivo
}