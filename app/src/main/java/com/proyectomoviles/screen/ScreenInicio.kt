package com.proyectomoviles.screen

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ExitToApp
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.proyectomoviles.R
import com.proyectomoviles.data.AuthManager
import com.proyectomoviles.data.FirestoreManager
import com.proyectomoviles.dispositivos.Dispositivo
import com.proyectomoviles.dispositivos.SensorTemperatura
import com.proyectomoviles.funciones.mostrarInformacionDispositivos
import com.proyectomoviles.services.MqttService

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InicioScreen(
    navigateToElementos: () -> Unit,
    navigateToInicio: () -> Unit,
    navigateToLogin: () -> Unit,
    mqttService: MqttService,
    firestoreManager: FirestoreManager,
    auth: AuthManager
) {
    val factory = InicioViewModelFactory(firestoreManager)
    val inicioViewModel = viewModel(InicioViewModel::class.java, factory = factory)
    val uiState by inicioViewModel.uiState.collectAsState()

    val user = auth.getCurrentUser()
    val context = LocalContext.current

    var contadorSensores by remember { mutableStateOf(0) }
    var contadorActuadores by remember { mutableStateOf(0) }
    var contadorMonitoreo by remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.clickable { /* Navegar a perfil si es necesario */ }
                    ) {
                        // Imagen de perfil
                        if (user?.photoUrl != null) {
                            AsyncImage(
                                model = ImageRequest.Builder(context)
                                    .data(user.photoUrl)
                                    .crossfade(true)
                                    .build(),
                                contentDescription = "Imagen de perfil",
                                modifier = Modifier
                                    .size(40.dp)
                                    .clip(CircleShape)
                                    .padding(end = 8.dp),
                                contentScale = ContentScale.Crop
                            )
                        } else {
                            Image(
                                painter = painterResource(R.drawable.ic_google),
                                contentDescription = "Foto de perfil por defecto",
                                modifier = Modifier
                                    .size(40.dp)
                                    .clip(CircleShape)
                                    .padding(end = 8.dp)
                            )
                        }

                        // Información del usuario
                        Column {
                            Text(
                                text = user?.displayName ?: "Anónimo",
                                style = MaterialTheme.typography.titleMedium,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                            Text(
                                text = user?.email ?: "Sin correo electrónico",
                                style = MaterialTheme.typography.bodySmall,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                ),
                actions = {
                    // Botón de cerrar sesión
                    IconButton(
                        onClick = { inicioViewModel.onLogoutSelected() }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Outlined.ExitToApp,
                            contentDescription = "Cerrar sesión"
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            MyFloatingActionButton(navigateToElementos)
        },
        floatingActionButtonPosition = FabPosition.Start
    ) { innerPadding ->
        // Envolvemos el contenido en un Box usando el innerPadding del Scaffold
        Box(modifier = Modifier.padding(innerPadding)) {
            if (uiState.sensorPresion.isNotEmpty() || uiState.sensorApertura.isNotEmpty() ||
                uiState.sensorTemperatura.isNotEmpty() || uiState.sensorLuz.isNotEmpty() ||
                uiState.sensorMovimiento.isNotEmpty() || uiState.sensorVibracion.isNotEmpty() ||
                uiState.sensorNivelAgua.isNotEmpty() || uiState.sensorCalidadAire.isNotEmpty() ||
                uiState.actuadorValvula.isNotEmpty() || uiState.cerraduraElectronica.isNotEmpty() ||
                uiState.controladorIluminacion.isNotEmpty() || uiState.medidorConsumoAgua.isNotEmpty() ||
                uiState.medidorGas.isNotEmpty() || uiState.controladorClima.isNotEmpty()
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    item {
                        Text(
                            "Sensores", style = TextStyle(
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold
                            ), modifier = Modifier.padding(top = 18.dp)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }

                    // Resto de los items de sensores (sin padding extra)
                    items(uiState.sensorTemperatura) { dispositivo ->
                        contadorSensores++

                        dispositivo.id?.let { id ->
                            dispositivo.nombre?.let { nombre ->
                                dispositivo.imagen?.let { imagen ->
                                    dispositivo.ubicacion?.let { ubicacion ->
                                        dispositivo.token?.let { token ->
                                            mostrarInformacionDispositivos(
                                                id, "sensortemperatura", nombre,
                                                imagen, ubicacion, token,
                                                inicioViewModel, mqttService, navigateToInicio
                                            )
                                        }

                                    }
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                    }

                    items(uiState.sensorLuz) { dispositivo ->
                        contadorSensores++

                        dispositivo.id?.let { id ->
                            dispositivo.nombre?.let { nombre ->
                                dispositivo.imagen?.let { imagen ->
                                    dispositivo.ubicacion?.let { ubicacion ->
                                        dispositivo.token?.let { token ->
                                            mostrarInformacionDispositivos(
                                                id, "sensorluz", nombre,
                                                imagen, ubicacion, token,
                                                inicioViewModel, mqttService, navigateToInicio
                                            )
                                        }

                                    }
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                    }

                    items(uiState.sensorMovimiento) { dispositivo ->
                        contadorSensores++

                        dispositivo.id?.let { id ->
                            dispositivo.nombre?.let { nombre ->
                                dispositivo.imagen?.let { imagen ->
                                    dispositivo.ubicacion?.let { ubicacion ->
                                        dispositivo.token?.let { token ->
                                            mostrarInformacionDispositivos(
                                                id, "sensormovimiento", nombre,
                                                imagen, ubicacion, token,
                                                inicioViewModel, mqttService, navigateToInicio
                                            )
                                        }

                                    }
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                    items(uiState.sensorVibracion) { dispositivo ->
                        contadorSensores++

                        dispositivo.id?.let { id ->
                            dispositivo.nombre?.let { nombre ->
                                dispositivo.imagen?.let { imagen ->
                                    dispositivo.ubicacion?.let { ubicacion ->
                                        dispositivo.token?.let { token ->
                                            mostrarInformacionDispositivos(
                                                id, "sensorvibracion", nombre,
                                                imagen, ubicacion, token,
                                                inicioViewModel, mqttService, navigateToInicio
                                            )
                                        }

                                    }
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                    }

                    items(uiState.sensorNivelAgua) { dispositivo ->
                        contadorSensores++

                        dispositivo.id?.let { id ->
                            dispositivo.nombre?.let { nombre ->
                                dispositivo.imagen?.let { imagen ->
                                    dispositivo.ubicacion?.let { ubicacion ->
                                        dispositivo.token?.let { token ->
                                            mostrarInformacionDispositivos(
                                                id, "sensornivelagua", nombre,
                                                imagen, ubicacion, token,
                                                inicioViewModel, mqttService, navigateToInicio
                                            )
                                        }

                                    }
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                    items(uiState.sensorPresion) { dispositivo ->
                        contadorSensores++

                        dispositivo.id?.let { id ->
                            dispositivo.nombre?.let { nombre ->
                                dispositivo.imagen?.let { imagen ->
                                    dispositivo.ubicacion?.let { ubicacion ->
                                        dispositivo.token?.let { token ->
                                            mostrarInformacionDispositivos(
                                                id, "sensorpresion", nombre,
                                                imagen, ubicacion, token,
                                                inicioViewModel, mqttService, navigateToInicio
                                            )
                                        }

                                    }
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                    items(uiState.sensorApertura) { dispositivo ->
                        contadorSensores++

                        dispositivo.id?.let { id ->
                            dispositivo.nombre?.let { nombre ->
                                dispositivo.imagen?.let { imagen ->
                                    dispositivo.ubicacion?.let { ubicacion ->
                                        dispositivo.token?.let { token ->
                                            mostrarInformacionDispositivos(
                                                id, "sensorapertura", nombre,
                                                imagen, ubicacion, token,
                                                inicioViewModel, mqttService, navigateToInicio
                                            )
                                        }

                                    }
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                    }

                    items(uiState.sensorCalidadAire) { dispositivo ->
                        contadorSensores++

                        dispositivo.id?.let { id ->
                            dispositivo.nombre?.let { nombre ->
                                dispositivo.imagen?.let { imagen ->
                                    dispositivo.ubicacion?.let { ubicacion ->
                                        dispositivo.token?.let { token ->
                                            mostrarInformacionDispositivos(
                                                id, "sensorcalidadaire", nombre,
                                                imagen, ubicacion, token,
                                                inicioViewModel, mqttService, navigateToInicio
                                            )
                                        }

                                    }
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                    }

                    item {
                        Text(
                            "Actuadores", style = TextStyle(
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold
                            ), modifier = Modifier.padding(top = 18.dp)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }

                    items(uiState.actuadorValvula) { dispositivo ->
                        contadorActuadores++

                        dispositivo.id?.let { id ->
                            dispositivo.nombre?.let { nombre ->
                                dispositivo.imagen?.let { imagen ->
                                    dispositivo.ubicacion?.let { ubicacion ->
                                        dispositivo.token?.let { token ->
                                            mostrarInformacionDispositivos(
                                                id, "actuadorvalvula", nombre,
                                                imagen, ubicacion, token,
                                                inicioViewModel, mqttService, navigateToInicio
                                            )
                                        }

                                    }
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                    }

                    items(uiState.cerraduraElectronica) { dispositivo ->
                        contadorActuadores++

                        dispositivo.id?.let { id ->
                            dispositivo.nombre?.let { nombre ->
                                dispositivo.imagen?.let { imagen ->
                                    dispositivo.ubicacion?.let { ubicacion ->
                                        dispositivo.token?.let { token ->
                                            mostrarInformacionDispositivos(
                                                id, "cerraduraelectronica", nombre,
                                                imagen, ubicacion, token,
                                                inicioViewModel, mqttService, navigateToInicio
                                            )
                                        }

                                    }
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                    }

                    items(uiState.controladorIluminacion) { dispositivo ->
                        contadorActuadores++

                        dispositivo.id?.let { id ->
                            dispositivo.nombre?.let { nombre ->
                                dispositivo.imagen?.let { imagen ->
                                    dispositivo.ubicacion?.let { ubicacion ->
                                        dispositivo.token?.let { token ->
                                            mostrarInformacionDispositivos(
                                                id, "controladoriluminacion", nombre,
                                                imagen, ubicacion, token,
                                                inicioViewModel, mqttService, navigateToInicio
                                            )
                                        }

                                    }
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                    }

                    item {
                        Text(
                            "Monitoreo", style = TextStyle(
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold
                            ), modifier = Modifier.padding(top = 18.dp)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                    items(uiState.controladorClima) { dispositivo ->
                        contadorMonitoreo++

                        dispositivo.id?.let { id ->
                            dispositivo.nombre?.let { nombre ->
                                dispositivo.imagen?.let { imagen ->
                                    dispositivo.ubicacion?.let { ubicacion ->
                                        dispositivo.token?.let { token ->
                                            mostrarInformacionDispositivos(
                                                id, "controladorclima", nombre,
                                                imagen, ubicacion, token,
                                                inicioViewModel, mqttService, navigateToInicio
                                            )
                                        }

                                    }
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                    items(uiState.medidorConsumoAgua) { dispositivo ->
                        contadorMonitoreo++

                        dispositivo.id?.let { id ->
                            dispositivo.nombre?.let { nombre ->
                                dispositivo.imagen?.let { imagen ->
                                    dispositivo.ubicacion?.let { ubicacion ->
                                        dispositivo.token?.let { token ->
                                            mostrarInformacionDispositivos(
                                                id, "medidorconsumoagua", nombre,
                                                imagen, ubicacion, token,
                                                inicioViewModel, mqttService, navigateToInicio
                                            )
                                        }

                                    }
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                    items(uiState.medidorGas) { dispositivo ->
                        contadorMonitoreo++

                        dispositivo.id?.let { id ->
                            dispositivo.nombre?.let { nombre ->
                                dispositivo.imagen?.let { imagen ->
                                    dispositivo.ubicacion?.let { ubicacion ->
                                        dispositivo.token?.let { token ->
                                            mostrarInformacionDispositivos(
                                                id, "medidorgas", nombre,
                                                imagen, ubicacion, token,
                                                inicioViewModel, mqttService, navigateToInicio
                                            )
                                        }

                                    }
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
        }
        if (contadorSensores == 0 && contadorMonitoreo == 0 && contadorActuadores == 0) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(1),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(6.dp, top = 60.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
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
                        )
                    ) {
                        Row(
                            modifier = Modifier.fillMaxSize(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                "No hay dispositivos vinculados.",
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
        }
    }

    // El AlertDialog se muestra por encima de todo cuando se activa
    if (uiState.showLogoutDialog) {
        AlertDialog(
            onDismissRequest = { inicioViewModel.dismissShowLogoutDialog() },
            title = { Text("Cerrar sesión") },
            text = { Text("¿Estás seguro de que quieres salir?") },
            confirmButton = {
                Button(
                    onClick = {
                        auth.signOut()
                        inicioViewModel.dismissShowLogoutDialog()
                        navigateToLogin()
                    }
                ) {
                    Text("Confirmar")
                }
            },
            dismissButton = {
                Button(
                    onClick = { inicioViewModel.dismissShowLogoutDialog() }
                ) {
                    Text("Cancelar")
                }
            }
        )
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