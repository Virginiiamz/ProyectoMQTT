package com.proyectomoviles.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import com.proyectomoviles.dispositivos.ActuadorValvula
import com.proyectomoviles.dispositivos.Dispositivo
import com.proyectomoviles.dispositivos.SensorApertura
import com.proyectomoviles.dispositivos.SensorCalidadAire
import com.proyectomoviles.dispositivos.SensorLuz
import com.proyectomoviles.dispositivos.SensorMovimiento
import com.proyectomoviles.dispositivos.SensorNivelAgua
import com.proyectomoviles.dispositivos.SensorPresion
import com.proyectomoviles.dispositivos.SensorTemperatura
import com.proyectomoviles.dispositivos.SensorVibracion
import com.proyectomoviles.funciones.mostrarDispositivoActuadores
import com.proyectomoviles.funciones.mostrarDispositivoSensores
import com.proyectomoviles.funciones.mostrarImagenByDispositivo
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
        SensorCalidadAire("Sensor de calidad del aire", "Sensor", "Baño", "Desfavorable"),
        ActuadorValvula("Actuador valvula", "Actuador", "Cocina", true),
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
            listaSensores+=dispositivo
        }
        if (dispositivo.tipo == "Actuador") {
            contadorActuador++
            listaActuadores+=dispositivo
        }
        if (dispositivo.tipo == "Monitoreo") {
            contadorMonitoreo++
            listaMonitoreo+=dispositivo
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

                    item {
                        Spacer(
                            modifier = Modifier
                                .height(60.dp)
                                .fillMaxWidth()
                        )
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

                    item {
                        Spacer(
                            modifier = Modifier
                                .height(60.dp)
                                .fillMaxWidth()
                        )
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
                        CargarSensores(dispositivo)
                    }

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
            mostrarDispositivoSensores(dispositivo)
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
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.padding(6.dp)
        ) {
            if (dispositivo is ActuadorValvula) {
                mostrarImagenByDispositivo("imgactuadorvalvula")
            }
        }
        Column()
        {
            mostrarDispositivoActuadores(dispositivo)
        }
    }
}

