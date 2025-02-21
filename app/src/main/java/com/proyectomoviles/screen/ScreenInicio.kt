package com.proyectomoviles.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.proyectomoviles.data.FirestoreManager
import com.proyectomoviles.data.RepositoryList
import com.proyectomoviles.data.TipoDispositivoCreado
import com.proyectomoviles.dispositivos.ActuadorValvula
import com.proyectomoviles.dispositivos.CerraduraElectronica
import com.proyectomoviles.dispositivos.ControladorClima
import com.proyectomoviles.dispositivos.ControladorIluminacion
import com.proyectomoviles.dispositivos.Dispositivo
import com.proyectomoviles.dispositivos.MedidorConsumoAgua
import com.proyectomoviles.dispositivos.MedidorGas
import com.proyectomoviles.dispositivos.SensorLuz
import com.proyectomoviles.dispositivos.SensorTemperatura
import com.proyectomoviles.funciones.mostrarActuadorCerradura
import com.proyectomoviles.funciones.mostrarActuadorValvula
import com.proyectomoviles.funciones.mostrarConsumoAgua
import com.proyectomoviles.funciones.mostrarConsumoGas
import com.proyectomoviles.funciones.mostrarControladorClima
import com.proyectomoviles.funciones.mostrarControladorIluminacion
import com.proyectomoviles.funciones.mostrarImagenByDispositivo
import com.proyectomoviles.funciones.mostrarInformacionDispositivos
import com.proyectomoviles.services.MqttService

@Composable
fun InicioScreen(
    navigateToElementos: () -> Unit,
    navigateToInicio: () -> Unit,
    mqttService: MqttService,
    firestoreManager: FirestoreManager
) {
    val factory = InicioViewModelFactory(firestoreManager)
    val inicioViewModel = viewModel(InicioViewModel::class.java, factory = factory)
    val uiState by inicioViewModel.uiState.collectAsState()

    var valor1 by rememberSaveable { mutableStateOf("") }
    var valor2 by rememberSaveable { mutableStateOf("") }

    Scaffold(
        floatingActionButton = {
            MyFloatingActionButton(navigateToElementos)
        },
        floatingActionButtonPosition = FabPosition.Start
    ) { paddingValue ->
        if (uiState.sensorPresion.isNotEmpty() || uiState.sensorApertura.isNotEmpty() || uiState.sensorTemperatura.isNotEmpty() || uiState.sensorLuz.isNotEmpty() || uiState.sensorMovimiento.isNotEmpty() || uiState.sensorVibracion.isNotEmpty() || uiState.sensorNivelAgua.isNotEmpty() || uiState.sensorCalidadAire.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier.padding(top = 40.dp)
            ) {
                item {
                    Text("Sensores")
                }

                items(uiState.sensorTemperatura) { dispositivo ->
                    mqttService.subscribe("grados") {
                        valor1 = it
                    }

                    mqttService.subscribe("humedad") {
                        valor2 = it
                    }
                    dispositivo.id?.let { id ->
                        dispositivo.nombre?.let { nombre ->
                            dispositivo.imagen?.let { imagen ->
                                dispositivo.ubicacion?.let { ubicacion ->
                                    mostrarInformacionDispositivos(
                                        id, "sensortemperatura", nombre,
                                        imagen, ubicacion, valor1, valor2, inicioViewModel, navigateToInicio
                                    )
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                }

                items(uiState.sensorLuz) { dispositivo ->
                    mqttService.subscribe("sensorluz") {
                        valor1 = it
                        valor2 = ""
                    }

                    dispositivo.id?.let { id ->
                        dispositivo.nombre?.let { nombre ->
                            dispositivo.imagen?.let { imagen ->
                                dispositivo.ubicacion?.let { ubicacion ->
                                    mostrarInformacionDispositivos(
                                        id, "sensorluz", nombre,
                                        imagen, ubicacion, valor1, valor2, inicioViewModel, navigateToInicio
                                    )
                                }
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }

                items(uiState.sensorMovimiento) { dispositivo ->
                    mqttService.subscribe("sensormovimiento") {
                        valor1 = it
                        valor2 = ""
                    }

                    dispositivo.id?.let { id ->
                        dispositivo.nombre?.let { nombre ->
                            dispositivo.imagen?.let { imagen ->
                                dispositivo.ubicacion?.let { ubicacion ->
                                    mostrarInformacionDispositivos(
                                        id, "sensormovimiento", nombre,
                                        imagen, ubicacion, valor1, valor2, inicioViewModel, navigateToInicio
                                    )
                                }
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }

                items(uiState.sensorVibracion) { dispositivo ->
                    mqttService.subscribe("sensorvibracion") {
                        valor1 = it
                        valor2 = ""
                    }

                    dispositivo.id?.let { id ->
                        dispositivo.nombre?.let { nombre ->
                            dispositivo.imagen?.let { imagen ->
                                dispositivo.ubicacion?.let { ubicacion ->
                                    mostrarInformacionDispositivos(
                                        id, "sensorvibracion", nombre,
                                        imagen, ubicacion, valor1, valor2, inicioViewModel, navigateToInicio
                                    )
                                }
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }

                items(uiState.sensorNivelAgua) { dispositivo ->
                    mqttService.subscribe("sensornivelagua") {
                        valor1 = it
                        valor2 = ""
                    }

                    dispositivo.id?.let { id ->
                        dispositivo.nombre?.let { nombre ->
                            dispositivo.imagen?.let { imagen ->
                                dispositivo.ubicacion?.let { ubicacion ->
                                    mostrarInformacionDispositivos(
                                        id, "sensornivelagua", nombre,
                                        imagen, ubicacion, valor1, valor2, inicioViewModel, navigateToInicio
                                    )
                                }
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }
                items(uiState.sensorPresion) { dispositivo ->
                    mqttService.subscribe("sensorpresion") {
                        valor1 = it
                        valor2 = ""
                    }

                    dispositivo.id?.let { id ->
                        dispositivo.nombre?.let { nombre ->
                            dispositivo.imagen?.let { imagen ->
                                dispositivo.ubicacion?.let { ubicacion ->
                                    mostrarInformacionDispositivos(
                                        id, "sensorpresion", nombre,
                                        imagen, ubicacion, valor1, valor2, inicioViewModel, navigateToInicio
                                    )
                                }
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }
                items(uiState.sensorApertura) { dispositivo ->
                    mqttService.subscribe("sensorapertura") {
                        valor1 = it
                        valor2 = ""
                    }

                    dispositivo.id?.let { id ->
                        dispositivo.nombre?.let { nombre ->
                            dispositivo.imagen?.let { imagen ->
                                dispositivo.ubicacion?.let { ubicacion ->
                                    mostrarInformacionDispositivos(
                                        id, "sensorapertura", nombre,
                                        imagen, ubicacion, valor1, valor2, inicioViewModel, navigateToInicio
                                    )
                                }
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }

                items(uiState.sensorCalidadAire) { dispositivo ->
                    mqttService.subscribe("sensorcalidadaire") {
                        valor1 = it
                        valor2 = ""
                    }

                    dispositivo.id?.let { id ->
                        dispositivo.nombre?.let { nombre ->
                            dispositivo.imagen?.let { imagen ->
                                dispositivo.ubicacion?.let { ubicacion ->
                                    mostrarInformacionDispositivos(
                                        id, "sensorcalidadaire", nombre,
                                        imagen, ubicacion, valor1, valor2, inicioViewModel, navigateToInicio
                                    )
                                }
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }

                items(uiState.actuadorValvula) { dispositivo ->
                    mqttService.subscribe("actuadorvalvula") {
                        valor1 = it
                        valor2 = ""
                    }

                    dispositivo.id?.let { id ->
                        dispositivo.nombre?.let { nombre ->
                            dispositivo.imagen?.let { imagen ->
                                dispositivo.ubicacion?.let { ubicacion ->
                                    mostrarInformacionDispositivos(
                                        id, "actuadorvalvula", nombre,
                                        imagen, ubicacion, valor1, valor2, inicioViewModel, navigateToInicio
                                    )
                                }
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }

                items(uiState.cerraduraElectronica) { dispositivo ->
                    mqttService.subscribe("cerraduraelectronica") {
                        valor1 = it
                        valor2 = ""
                    }

                    dispositivo.id?.let { id ->
                        dispositivo.nombre?.let { nombre ->
                            dispositivo.imagen?.let { imagen ->
                                dispositivo.ubicacion?.let { ubicacion ->
                                    mostrarInformacionDispositivos(
                                        id, "cerraduraelectronica", nombre,
                                        imagen, ubicacion, valor1, valor2, inicioViewModel, navigateToInicio
                                    )
                                }
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }

                items(uiState.controladorIluminacion) { dispositivo ->
                    mqttService.subscribe("controladoriluminacion") {
                        valor1 = it
                        valor2 = ""
                    }

                    dispositivo.id?.let { id ->
                        dispositivo.nombre?.let { nombre ->
                            dispositivo.imagen?.let { imagen ->
                                dispositivo.ubicacion?.let { ubicacion ->
                                    mostrarInformacionDispositivos(
                                        id, "controladoriluminacion", nombre,
                                        imagen, ubicacion, valor1, valor2, inicioViewModel, navigateToInicio
                                    )
                                }
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(1),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValue)
                    .padding(6.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),

                ) {
                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp)
                            .padding(16.dp),
                        elevation = CardDefaults.elevatedCardElevation(12.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.LightGray,
                            contentColor = Color.Black
                        ),
                    ) {
                        Row(
                            modifier = Modifier.fillMaxSize(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text("No hay dispositivos vinculados.", textAlign = TextAlign.Center)
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