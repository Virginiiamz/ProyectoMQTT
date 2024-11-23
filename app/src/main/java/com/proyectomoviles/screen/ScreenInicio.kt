package com.proyectomoviles.screen

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
import com.proyectomoviles.funciones.mostrarActuadorCerradura
import com.proyectomoviles.funciones.mostrarActuadorValvula
import com.proyectomoviles.funciones.mostrarControladorClima
import com.proyectomoviles.funciones.mostrarControladorIluminacion
import com.proyectomoviles.funciones.mostrarImagenByDispositivo
import com.proyectomoviles.funciones.mostrarInformacionDispositivos
import com.proyectomoviles.funciones.mostrarSensorApertura
import com.proyectomoviles.funciones.mostrarSensorCalidadAire
import com.proyectomoviles.funciones.mostrarSensorLuz
import com.proyectomoviles.funciones.mostrarSensorMovimiento
import com.proyectomoviles.funciones.mostrarSensorNivelAgua
import com.proyectomoviles.funciones.mostrarSensorPresion
import com.proyectomoviles.funciones.mostrarSensorTemperatura
import com.proyectomoviles.funciones.mostrarSensorVibracion

@Composable
fun InicioScreen(navigateToElementos: () -> Unit) {
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
        CerraduraElectronica("Cerradura electrónica", "Actuador", "Dormitorio", R.drawable.imgcerraduraelectronica, false),
        ControladorIluminacion("Controlador de iluminación", "Actuador", "Pasillo", R.drawable.imgcontroladorluz, false),
        MedidorGas("Medidor de gas", "Monitoreo", "Baño", R.drawable.imgconsumogas, 10.3)
    )

    var contadorSensor = 0
    var contadorActuador = 0
    var contadorMonitoreo = 0
    var listaSensores: List<Dispositivo> = mutableListOf()
    var listaActuadores: List<Dispositivo> = mutableListOf()
    var listaMonitoreo: List<Dispositivo> = mutableListOf()

    listaDispositivo.forEach { dispositivo ->
        if (dispositivo.tipo == "Sensor") {
            contadorSensor++
            listaSensores += dispositivo
        }
        if (dispositivo.tipo == "Actuador") {
            contadorActuador++
            listaActuadores += dispositivo
        }
        if (dispositivo.tipo == "Monitoreo") {
            contadorMonitoreo++
            listaMonitoreo += dispositivo
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
                if (contadorSensor > 0) {
                    item {
                        Text(
                            "Sensores",
                            modifier = Modifier.padding(top = 16.dp, bottom = 6.dp),
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp
                        )
                    }
                    items(listaSensores) { dispositivo ->
                        CargarSensores(dispositivo)
                    }

                    if (contadorMonitoreo == 0 && contadorActuador == 0) {
                        item {
                            Spacer(
                                modifier = Modifier
                                    .height(60.dp)
                                    .fillMaxWidth()
                            )
                        }
                    }
                }

                if (contadorActuador > 0) {
                    item {
                        Text(
                            "Actuadores",
                            modifier = Modifier.padding(top = 16.dp, bottom = 6.dp),
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp
                        )
                    }
                    items(listaActuadores) { dispositivo ->
                        CargarActuadores(dispositivo)
                    }

                    if (contadorSensor == 0 && contadorMonitoreo == 0) {
                        item {
                            Spacer(
                                modifier = Modifier
                                    .height(60.dp)
                                    .fillMaxWidth()
                            )
                        }
                    }
                }

                if (contadorMonitoreo > 0) {
                    item {
                        Text(
                            "Monitoreo y control complejo",
                            modifier = Modifier.padding(top = 16.dp, bottom = 6.dp),
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp
                        )
                    }
                    items(listaMonitoreo) { dispositivo ->
                        CargarMonitoreo(dispositivo)
                    }

                    if (contadorSensor == 0 && contadorActuador == 0) {
                        item {
                            Spacer(
                                modifier = Modifier
                                    .height(60.dp)
                                    .fillMaxWidth()
                            )
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
fun CargarSensores(dispositivo: Dispositivo) {
    Column(
        modifier = Modifier
            .width(400.dp)
            .border(
                border = BorderStroke(2.dp, Color.LightGray),
                shape = RoundedCornerShape(8.dp),
            ),


        horizontalAlignment = Alignment.CenterHorizontally
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
                    mostrarImagenByDispositivo(dispositivo)
                }
            }
            Column(
                modifier = Modifier.weight(1f).height(125.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                mostrarInformacionDispositivos(dispositivo)
            }
        }

        Column()
        {
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
    }
}

@Composable
fun CargarActuadores(dispositivo: Dispositivo) {
    Column(
        modifier = Modifier
            .width(400.dp)
            .border(
                border = BorderStroke(2.dp, Color.LightGray),
                shape = RoundedCornerShape(8.dp),
            ),


        horizontalAlignment = Alignment.CenterHorizontally
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
                    mostrarImagenByDispositivo(dispositivo)
                }
            }
            Column(
                modifier = Modifier.weight(1f).height(125.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                mostrarInformacionDispositivos(dispositivo)
            }
        }

        Column()
        {
            when (dispositivo) {
                is ActuadorValvula -> mostrarActuadorValvula(dispositivo)
                is CerraduraElectronica -> mostrarActuadorCerradura(dispositivo)
                is ControladorIluminacion -> mostrarControladorIluminacion(dispositivo)
            }
        }
    }
}

@Composable
fun CargarMonitoreo(dispositivo: Dispositivo) {
    Column(
        modifier = Modifier
            .width(400.dp)
            .border(
                border = BorderStroke(2.dp, Color.LightGray),
                shape = RoundedCornerShape(8.dp),
            ),


        horizontalAlignment = Alignment.CenterHorizontally
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
                    mostrarImagenByDispositivo(dispositivo)
                }
            }
            Column(
                modifier = Modifier.weight(1f).height(125.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                mostrarInformacionDispositivos(dispositivo)
            }
        }

        Column()
        {
            when (dispositivo) {
                is ControladorClima -> mostrarControladorClima(dispositivo)

            }
        }
    }
}


