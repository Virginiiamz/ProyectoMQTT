package com.proyectomoviles.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ExitToApp
import androidx.compose.material3.*
import com.proyectomoviles.R
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.proyectomoviles.data.AuthManager
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
import com.proyectomoviles.dispositivos.SensorApertura
import com.proyectomoviles.dispositivos.SensorCalidadAire
import com.proyectomoviles.dispositivos.SensorLuz
import com.proyectomoviles.dispositivos.SensorMovimiento
import com.proyectomoviles.dispositivos.SensorNivelAgua
import com.proyectomoviles.dispositivos.SensorPresion
import com.proyectomoviles.dispositivos.SensorTemperatura
import com.proyectomoviles.dispositivos.SensorVibracion
import com.proyectomoviles.services.MqttService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.WebSocket

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConfiguracionScreen(
    tipoDispositivo: String,
    dispositivo: Dispositivo?,
    navigateToInicio: () -> Unit,
    onLogout: () -> Unit,
    mqttService: MqttService,
    auth: AuthManager,
    firestore: FirestoreManager
) {
    val factory = InicioViewModelFactory(firestore)
    val inicioViewModel = viewModel(InicioViewModel::class.java, factory = factory)
    val uiState by inicioViewModel.uiState.collectAsState()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Configuración") },
                actions = {
                    IconButton(onClick = onLogout) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Outlined.ExitToApp,
                            contentDescription = "Cerrar sesión"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {

            when (tipoDispositivo) {
                "Sensor Temperatura" -> {
                    ConfiguracionSensorTemperatura(
                        navigateToInicio,
                        auth,
                        inicioViewModel
                    )
                }

                "Sensor Luz" -> {
                    ConfiguracionSensorLuz(navigateToInicio, mqttService, auth, inicioViewModel)
                }

                "Sensor Movimiento" -> {
                    ConfiguracionSensorMovimiento(navigateToInicio, auth, inicioViewModel)
                }

                "Sensor Vibración" -> {
                    ConfiguracionSensorVibracion(navigateToInicio, auth, inicioViewModel)
                }

                "Sensor Nivel Agua" -> {
                    ConfiguracionSensorNivelAgua(navigateToInicio, auth, inicioViewModel)
                }

                "Sensor Presión" -> {
                    ConfiguracionSensorPresion(navigateToInicio, auth, inicioViewModel)
                }

                "Sensor Apertura" -> {
                    ConfiguracionSensorApertura(
                        navigateToInicio,
                        mqttService,
                        auth,
                        inicioViewModel
                    )
                }

                "Sensor Calidad Aire" -> {
                    ConfiguracionSensorCalidadAire(navigateToInicio, auth, inicioViewModel)
                }

                "Actuador Válvula" -> {
                    ConfiguracionActuadorValvula(
                        navigateToInicio,
                        mqttService,
                        auth,
                        inicioViewModel
                    )
                }

                "Cerradura Electrónica" -> {
                    ConfiguracionCerraduraElectronica(
                        navigateToInicio,
                        mqttService,
                        auth,
                        inicioViewModel
                    )
                }

                "Controlador Iluminación" -> {
                    ConfiguracionControladorIluminacion(
                        navigateToInicio,
                        mqttService,
                        auth,
                        inicioViewModel
                    )
                }

                "Controlador Clima" -> {
                    ConfiguracionControladorClima(
                        navigateToInicio,
                        auth,
                        inicioViewModel
                    )
                }

                "Medidor Consumo Agua" -> {
                    ConfiguracionMedidorConsumoAgua(
                        navigateToInicio,
                        auth,
                        inicioViewModel
                    )
                }

                "Medidor Gas" -> {
                    ConfiguracionMedidorGas(
                        navigateToInicio,
                        auth,
                        inicioViewModel
                    )
                }

                else -> {
                    Text(text = "Configuración no disponible para este dispositivo")

                }
            }
        }
    }
}

//SENSORES:
@Composable
fun ConfiguracionSensorTemperatura(
    navigateToInicio: () -> Unit,
    auth: AuthManager,
    inicioViewModel: InicioViewModel
) {
    var nombre by remember { mutableStateOf("") }
    var tokenGrados by remember { mutableStateOf("") }
    var tokenHumedad by remember { mutableStateOf("") }
    var ubicacion by remember { mutableStateOf("") }
    var grados by rememberSaveable { mutableStateOf(0.00) }
    var humedad by rememberSaveable { mutableStateOf(0.00) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = "Configuración del Sensor de Temperatura")
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") }
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = tokenGrados,
            onValueChange = { tokenGrados = it },
            label = { Text("Token Grados") }
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = tokenHumedad,
            onValueChange = { tokenHumedad = it },
            label = { Text("Token Humedad") }
        )
        OutlinedTextField(

            modifier = Modifier.fillMaxWidth(),
            value = ubicacion,
            onValueChange = { ubicacion = it },
            label = { Text("Ubicacion") }
        )
        val sensor = SensorTemperatura(
            id = null,
            userId = auth.getCurrentUser()?.uid,
            nombre,
            tokenGrados,
            tokenHumedad,
            "SensorTemperatura",
            ubicacion,
            R.drawable.imgsensortermometro,
            grados,
            humedad
        )
        Button(
            onClick = {
                inicioViewModel.addSensorTemperatura(sensor)
                navigateToInicio()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Actualizar")
        }
    }
}

@Composable
fun ConfiguracionSensorLuz(
    navigateToInicio: () -> Unit,
    mqttService: MqttService,
    auth: AuthManager,
    inicioViewModel: InicioViewModel
) {
    var nombre by remember { mutableStateOf("") }
    var token by remember { mutableStateOf("") }
    var ubicacion by remember { mutableStateOf("") }
    var encendido by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Configuración del Sensor de Luz")
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") }
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = token,
            onValueChange = { token = it },
            label = { Text("Token") }
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = ubicacion,
            onValueChange = { ubicacion = it },
            label = { Text("Ubicacion") }
        )
        Text("Estado de encendido:")
        Switch(
            checked = encendido,
            onCheckedChange = { encendido = !encendido }
        )
        val sensor = SensorLuz(
            null,
            userId = auth.getCurrentUser()?.uid,
            nombre,
            token,
            "SensorLuz",
            ubicacion,
            R.drawable.imgsensorluz,
            encendido
        )

        Button(
            onClick = {
                mqttService.publish("sensorluz", encendido.toString())
                inicioViewModel.addSensorLuz(sensor)
                navigateToInicio()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Actualizar")
        }
    }
}

@Composable
fun ConfiguracionSensorMovimiento(
    navigateToInicio: () -> Unit,
    auth: AuthManager,
    inicioViewModel: InicioViewModel
) {
    var nombre by remember { mutableStateOf("") }
    var token by remember { mutableStateOf("") }
    var ubicacion by remember { mutableStateOf("") }
    var estado by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Configuración del Sensor de Movimiento")
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") }
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = token,
            onValueChange = { token = it },
            label = { Text("Token") }
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = ubicacion,
            onValueChange = { ubicacion = it },
            label = { Text("Ubicacion") }
        )
        val sensor =
            SensorMovimiento(
                null,
                userId = auth.getCurrentUser()?.uid,
                nombre,
                token,
                "SensorMovimiento",
                ubicacion,
                R.drawable.imgsensormovimiento,
                estado
            )
        Button(
            onClick = {
                inicioViewModel.addSensorMovimiento(
                    sensor
                )
                navigateToInicio()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Actualizar")
        }
    }
}

@Composable
fun ConfiguracionSensorVibracion(
    navigateToInicio: () -> Unit,
    auth: AuthManager,
    inicioViewModel: InicioViewModel
) {
    var nombre by remember { mutableStateOf("") }
    var token by remember { mutableStateOf("") }
    var ubicacion by remember { mutableStateOf("") }
    var estado by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Configuración del Sensor de Vibración")
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") }
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = token,
            onValueChange = { token = it },
            label = { Text("Token") }
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = ubicacion,
            onValueChange = { ubicacion = it },
            label = { Text("Ubicacion") }
        )
        val sensor =
            SensorVibracion(
                null,
                userId = auth.getCurrentUser()?.uid,
                nombre,
                token,
                "SensoVibracionr",
                ubicacion,
                R.drawable.imgsensorvibracion,
                estado
            )
        Button(
            onClick = {
                inicioViewModel.addSensorVibracion(
                    sensor
                )
                navigateToInicio()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Actualizar")
        }
    }
}

@Composable
fun ConfiguracionSensorNivelAgua(
    navigateToInicio: () -> Unit,
    auth: AuthManager,
    inicioViewModel: InicioViewModel
) {
    var nombre by remember { mutableStateOf("") }
    var token by remember { mutableStateOf("") }
    var ubicacion by remember { mutableStateOf("") }
    var litros by remember { mutableStateOf(0.00) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Configuración del Sensor de Nivel de Agua")
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") }
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = token,
            onValueChange = { token = it },
            label = { Text("Token") }
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = ubicacion,
            onValueChange = { ubicacion = it },
            label = { Text("Ubicacion") }
        )
        val sensor =
            SensorNivelAgua(
                null,
                userId = auth.getCurrentUser()?.uid,
                nombre,
                token,
                "SensorNivelAgua",
                ubicacion,
                R.drawable.imgsensornivelagua,
                litros
            )
        Button(
            onClick = {
                inicioViewModel.addSensorNivelAgua(
                    sensor
                )
                navigateToInicio()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Actualizar")
        }
    }
}

@Composable
fun ConfiguracionSensorPresion(
    navigateToInicio: () -> Unit,
    auth: AuthManager,
    inicioViewModel: InicioViewModel
) {
    var nombre by remember { mutableStateOf("") }
    var token by remember { mutableStateOf("") }
    var ubicacion by remember { mutableStateOf("") }
    var presion by remember { mutableStateOf(0.00) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Configuración del Sensor de Presión")
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") }
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = token,
            onValueChange = { token = it },
            label = { Text("Token") }
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = ubicacion,
            onValueChange = { ubicacion = it },
            label = { Text("Ubicacion") }
        )
        val sensor =
            SensorPresion(
                null, // tiene que ser nulo
                userId = auth.getCurrentUser()?.uid,
                nombre,
                token,
                "SensorPresion",
                ubicacion,
                R.drawable.imgsensorpresion,
                presion
            )
        Button(
            onClick = {
                inicioViewModel.addSensorPresion(sensor)
                navigateToInicio()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Actualizar")
        }
    }
}

@Composable
fun ConfiguracionSensorApertura(
    navigateToInicio: () -> Unit,
    mqttService: MqttService,
    auth: AuthManager,
    inicioViewModel: InicioViewModel
) {
    var nombre by remember { mutableStateOf("") }
    var token by remember { mutableStateOf("") }
    var ubicacion by remember { mutableStateOf("") }
    var estado by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Configuración del Sensor de Apertura")
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") }
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = token,
            onValueChange = { token = it },
            label = { Text("Token") }
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = ubicacion,
            onValueChange = { ubicacion = it },
            label = { Text("Ubicacion") }
        )
        Text("Estado:")
        Switch(
            checked = estado,
            onCheckedChange = { estado = !estado }
        )
        val sensor =
            SensorApertura(
                null,
                userId = auth.getCurrentUser()?.uid,
                nombre,
                token,
                "SensorApertura",
                ubicacion,
                R.drawable.imgsensorapertura,
                estado
            )
        Button(
            onClick = {
                mqttService.publish("sensorapertura", estado.toString())
                inicioViewModel.addSensorApertura(sensor)
                navigateToInicio()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Actualizar")
        }
    }
}

@Composable
fun ConfiguracionSensorCalidadAire(
    navigateToInicio: () -> Unit,
    auth: AuthManager,
    inicioViewModel: InicioViewModel
) {
    var nombre by remember { mutableStateOf("") }
    var token by remember { mutableStateOf("") }
    var ubicacion by remember { mutableStateOf("") }
    var calidad by remember { mutableStateOf("") }


    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = "Configuración del Sensor de Calidad del Aire")
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") }
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = token,
            onValueChange = { token = it },
            label = { Text("Token") }
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = ubicacion,
            onValueChange = { ubicacion = it },
            label = { Text("Ubicacion") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        val sensor =
            SensorCalidadAire(
                null,
                userId = auth.getCurrentUser()?.uid,
                nombre,
                token,
                "SensorCalidadAire",
                ubicacion,
                R.drawable.imgsensorcalidadaire,
                calidad
            )
        Button(
            onClick = {

                inicioViewModel.addSensorCalidadAire(
                    sensor
                )
                navigateToInicio()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Actualizar")
        }
    }
}

//
////ACTUADORES:
//
@Composable
fun ConfiguracionActuadorValvula(
    navigateToInicio: () -> Unit,
    mqttService: MqttService,
    auth: AuthManager,
    inicioViewModel: InicioViewModel
) {
    var nombre by remember { mutableStateOf("") }
    var token by remember { mutableStateOf("") }
    var ubicacion by remember { mutableStateOf("") }
    var estado by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Configuración del Actuador de Válvula")
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") }
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = token,
            onValueChange = { token = it },
            label = { Text("Token") }
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = ubicacion,
            onValueChange = { ubicacion = it },
            label = { Text("Ubicacion") }
        )
        Text("Activo:")
        Switch(
            checked = estado,
            onCheckedChange = { estado = !estado }
        )
        val actuador =
            ActuadorValvula(
                null,
                userId = auth.getCurrentUser()?.uid,
                nombre,
                token,
                "ActuadorValvula",
                ubicacion,
                R.drawable.imgactuadorvalvula,
                estado
            )
        Button(
            onClick = {
                mqttService.publish("actuadorvalvula", estado.toString())
                inicioViewModel.addActuadorValvula(
                    actuador
                )
                navigateToInicio()


            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Actualizar")
        }
    }
}

@Composable
fun ConfiguracionCerraduraElectronica(
    navigateToInicio: () -> Unit,
    mqttService: MqttService,
    auth: AuthManager,
    inicioViewModel: InicioViewModel
) {
    var nombre by remember { mutableStateOf("") }
    var token by remember { mutableStateOf("") }
    var ubicacion by remember { mutableStateOf("") }
    var estado by rememberSaveable { mutableStateOf(false) }


    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Configuración de la Cerradura Electrónica")
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") }
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = token,
            onValueChange = { token = it },
            label = { Text("Token") }
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = ubicacion,
            onValueChange = { ubicacion = it },
            label = { Text("Ubicacion") }
        )
        Text("Activo:")
        Switch(
            checked = estado,
            onCheckedChange = { estado = !estado }
        )
        val actuador = CerraduraElectronica(
            null,
            userId = auth.getCurrentUser()?.uid,
            nombre,
            token,
            "Actuador",
            ubicacion,
            R.drawable.imgcerraduraelectronica,
            estado
        )
        Button(
            onClick = {
                mqttService.publish("cerraduraelectronica", estado.toString())

                inicioViewModel.addActuadorCerraduraElectronica(actuador)
                navigateToInicio()

            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Actualizar")
        }
    }
}

@Composable
fun ConfiguracionControladorIluminacion(
    navigateToInicio: () -> Unit,
    mqttService: MqttService,
    auth: AuthManager,
    inicioViewModel: InicioViewModel
) {
    var nombre by remember { mutableStateOf("") }
    var token by remember { mutableStateOf("") }
    var ubicacion by remember { mutableStateOf("") }
    var estado by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Configuración del Controlador de Iluminación")
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") }
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = token,
            onValueChange = { token = it },
            label = { Text("Token") }
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = ubicacion,
            onValueChange = { ubicacion = it },
            label = { Text("Ubicacion") }
        )
        Text("Activo:")
        Switch(
            checked = estado,
            onCheckedChange = { estado = !estado }
        )
        val actuador = ControladorIluminacion(
            null,
            userId = auth.getCurrentUser()?.uid,
            nombre,
            token,
            "Actuador",
            ubicacion,
            R.drawable.imgcontroladorluz,
            estado
        )
        Button(
            onClick = {
                mqttService.publish("controladoriluminacion", estado.toString())
                inicioViewModel.addActuadorControladorIluminacion(actuador)
                navigateToInicio()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Actualizar")
        }
    }
}

//Monitoreo:

@Composable
fun ConfiguracionControladorClima(
    navigateToInicio: () -> Unit,
    auth: AuthManager,
    inicioViewModel: InicioViewModel
) {
    var nombre by remember { mutableStateOf("") }
    var tokenGrados by remember { mutableStateOf("") }
    var tokenHumedad by remember { mutableStateOf("") }
    var ubicacion by remember { mutableStateOf("") }
    var grados by remember { mutableStateOf(0.00) }
    var humedad by remember { mutableStateOf(0.00) }


    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Configuración del Controlador de Clima")
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") }
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = tokenGrados,
            onValueChange = { tokenGrados = it },
            label = { Text("Token Grados") }
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = tokenHumedad,
            onValueChange = { tokenHumedad = it },
            label = { Text("Token Humedad") }
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = ubicacion,
            onValueChange = { ubicacion = it },
            label = { Text("Ubicacion") }
        )
        val monitoreo = ControladorClima(
            null,
            userId = auth.getCurrentUser()?.uid,
            nombre,
            tokenGrados,
            tokenHumedad,
            "Monitoreo",
            ubicacion,
            R.drawable.imgcontroladorclima,
            grados,
            humedad
        )
        Button(
            onClick = {
                inicioViewModel.addControladorClima(
                    monitoreo
                )
                navigateToInicio()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Actualizar")
        }
    }
}

@Composable
fun ConfiguracionMedidorConsumoAgua(
    navigateToInicio: () -> Unit,
    auth: AuthManager,
    inicioViewModel: InicioViewModel
) {
    var nombre by remember { mutableStateOf("") }
    var token by remember { mutableStateOf("") }
    var ubicacion by remember { mutableStateOf("") }
    var litros by rememberSaveable { mutableStateOf(0.00) }


    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Configuración del Medidor de Consumo de Agua")
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = token,
            onValueChange = { token = it },
            label = { Text("Token") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = ubicacion,
            onValueChange = { ubicacion = it },
            label = { Text("Ubicacion") }
        )
        Spacer(modifier = Modifier.height(8.dp))

        val monitoreo =
            MedidorConsumoAgua(
                null,
                userId = auth.getCurrentUser()?.uid,
                nombre,
                token,
                "Monitoreo",
                ubicacion,
                R.drawable.imgconsumoagua,
                litros
            )
        Button(
            onClick = {
                inicioViewModel.addMedidorConsumoAgua(
                    monitoreo
                )
                navigateToInicio()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Actualizar")
        }
    }
}

@Composable
fun ConfiguracionMedidorGas(
    navigateToInicio: () -> Unit,
    auth: AuthManager,
    inicioViewModel: InicioViewModel
) {
    var nombre by remember { mutableStateOf("") }
    var token by remember { mutableStateOf("") }
    var ubicacion by remember { mutableStateOf("") }
    var m3State by remember { mutableStateOf(0.00) }
    var m3toString by remember { mutableStateOf(m3State.toString()) } //Xq el OutlinedTextField necesita un string para q funcione


    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = "Configuración del Medidor de Gas")
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = token,
            onValueChange = { token = it },
            label = { Text("Token") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = ubicacion,
            onValueChange = { ubicacion = it },
            label = { Text("Ubicacion") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        val monitoreo = MedidorGas(
            null,
            userId = auth.getCurrentUser()?.uid,
            nombre,
            token,
            "Monitoreo",
            ubicacion,
            R.drawable.imgconsumogas,
            m3toString.toDouble()
        )
        Button(
            onClick = {
                inicioViewModel.addMedidorGas(
                    monitoreo
                )
                navigateToInicio()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Actualizar")
        }
    }
}


