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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import androidx.compose.ui.graphics.Color
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
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
               .fillMaxSize()
               .padding(paddingValue),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
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
                        ) {
                            if (dispositivo is SensorTemperatura) {
                                mostrarIconoByDispositivo("AcUnit")
                            } else if (dispositivo is SensorMovimiento) {
                                mostrarIconoByDispositivo("Attribution")
                            }
                        }
                        Column(


                        )
                        {
                            if(dispositivo is SensorTemperatura){
                                Text(dispositivo.nombre)
                                Spacer(
                                    Modifier.height(1.dp)
                                )
                                Text(dispositivo.ubicacion)
                                Spacer(
                                    Modifier.height(1.dp)
                                )
//                                LazyVerticalGrid(
//                                    columns = GridCells.Fixed(2),
//                                ) {
//                                    item {
//                                        Text(
//                                            "Grados: ${dispositivo.grados} grados",
//                                            style = MaterialTheme.typography.bodyMedium
//                                        )
//                                    }
//                                    item {
//                                        Text(
//                                            "Humedad: ${dispositivo.humedad}%",
//                                            style = MaterialTheme.typography.bodyMedium
//                                        )
//                                    }
//
//                                }

                            }
                        }
                    }



//                    AsyncImage(
//                        model = "https://images.vexels.com/media/users/3/141334/isolated/lists/8ebf3c1ca1f27e55731bbcfcf209a3ea-icono-de-termometro.png",
//                        contentDescription = null,
//                        modifier = Modifier.padding(8.dp),
//                        placeholder = painterResource(R.drawable.placeholder),  // Imagen temporal
//                        error = painterResource(R.drawable.error),
//                    )
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