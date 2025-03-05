package com.proyectomoviles.screen

import androidx.compose.foundation.Image
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ExitToApp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.proyectomoviles.R
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ElementosScreen(onNavigateToConfiguracion: (String) -> Unit,
                    onLogout: () -> Unit) {
    val dispositivos = listaElementos()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Elementos") },
                actions = {
                    IconButton(onClick = onLogout) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Outlined.ExitToApp,
                            contentDescription = "Cerrar sesión"
                        )
                    }
                }
            )
        },
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
            items(dispositivos) { dispositivo ->
                when(dispositivo){
                    is SensorTemperatura -> DispositivoCard("Sensor Temperatura",R.drawable.imgsensortermometro, onNavigateToConfiguracion)
                    is SensorMovimiento -> DispositivoCard("Sensor Movimiento", R.drawable.imgsensormovimiento, onNavigateToConfiguracion)
                    is SensorVibracion -> DispositivoCard("Sensor Vibración", R.drawable.imgsensorvibracion, onNavigateToConfiguracion)
                    is SensorNivelAgua -> DispositivoCard("Sensor Nivel Agua", R.drawable.imgsensornivelagua, onNavigateToConfiguracion)
                    is SensorLuz -> DispositivoCard("Sensor Luz", R.drawable.imgsensorluz, onNavigateToConfiguracion)
                    is SensorPresion -> DispositivoCard("Sensor Presión", R.drawable.imgsensorpresion, onNavigateToConfiguracion)
                    is SensorApertura -> DispositivoCard("Sensor Apertura", R.drawable.imgsensorapertura, onNavigateToConfiguracion)
                    is SensorCalidadAire -> DispositivoCard("Sensor Calidad Aire", R.drawable.imgsensorcalidadaire, onNavigateToConfiguracion)
                    is ActuadorValvula -> DispositivoCard("Actuador Válvula", R.drawable.imgactuadorvalvula, onNavigateToConfiguracion)
                    is ControladorClima -> DispositivoCard("Controlador Clima", R.drawable.imgcontroladorclima, onNavigateToConfiguracion)
                    is MedidorConsumoAgua -> DispositivoCard("Medidor Consumo Agua", R.drawable.imgconsumoagua , onNavigateToConfiguracion)
                    is CerraduraElectronica -> DispositivoCard("Cerradura Electrónica", R.drawable.imgcerraduraelectronica, onNavigateToConfiguracion)
                    is MedidorGas -> DispositivoCard("Medidor Gas", R.drawable.imgconsumogas, onNavigateToConfiguracion)
                    is ControladorIluminacion -> DispositivoCard("Controlador Iluminación", R.drawable.imgcontroladorluz, onNavigateToConfiguracion)
                    else -> {}
                }

            }
        }
    }
}


@Composable
fun DispositivoCard(nombreDispositivo: String, imagen: Int, onNavigateToConfiguracion: (String) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .padding(16.dp)
            .clickable { onNavigateToConfiguracion(nombreDispositivo)},
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
            Image(
                painterResource(id = imagen),
                contentDescription = nombreDispositivo,
                modifier = Modifier.padding(top = 6.dp, bottom = 6.dp)

            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(nombreDispositivo, textAlign = TextAlign.Center)
        }
    }
}




fun listaElementos(): List<Dispositivo> {
    val listaDispositivo = listOf(
        SensorTemperatura(null ,null,"Sensor Temperatura","", "Sensor", "", R.drawable.imgsensortermometro,0.00, 0.00),
        SensorMovimiento(null ,null,"Sensor Movimiento", "","Sensor", "",R.drawable.imgsensormovimiento, false),
        SensorVibracion(null ,null,"Sensor Vibración", "","Sensor", "",R.drawable.imgsensorvibracion, false),
        SensorNivelAgua(null ,null,"Sensor Nivel de Agua", "","Sensor", "", R.drawable.imgsensornivelagua,0.00),
        SensorLuz(null ,null,"Sensor de luz", "","Sensor", "", R.drawable.imgsensorluz, false),
        SensorPresion(null ,null,"Sensor de Presión", "","Sensor", "",R.drawable.imgsensorpresion, 0.00),
        SensorApertura(null ,null,"Sensor de Apertura", "","Sensor", "", R.drawable.imgsensorapertura, false),
        SensorCalidadAire(null ,null,"Sensor de Calidad del Aire","", "Sensor", "", R.drawable.imgsensorcalidadaire,""),
        ActuadorValvula(null ,null,"Actuador Valvula", "","Actuador", "", R.drawable.imgactuadorvalvula,false),
        ControladorClima(null ,null,"Controlador Clima", "","Monitoreo", "", R.drawable.imgcontroladorclima, 0.00, 0.00),
        MedidorConsumoAgua(null ,null,"Medidor de Consumo de Agua","", "Monitoreo", "", R.drawable.imgconsumoagua, 0.00),
        CerraduraElectronica(null ,null,"Cerradura Electrónica", "","Monitoreo", "", R.drawable.imgcerraduraelectronica, false),
        MedidorGas(null ,null,"Medidor de gas", "","Monitoreo", "", R.drawable.imgconsumogas, 0.00),
        ControladorIluminacion(null ,null,"Controlador Iluminación","", "Actuador", "", R.drawable.imgcontroladorluz, false),
    )
    return listaDispositivo
}