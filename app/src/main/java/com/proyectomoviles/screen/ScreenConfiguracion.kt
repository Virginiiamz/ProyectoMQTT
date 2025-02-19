package com.proyectomoviles.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import com.proyectomoviles.R
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.proyectomoviles.data.AuthManager
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

@Composable
fun ConfiguracionScreen(
    tipoDispositivo: String,
    dispositivo: Dispositivo?,
    navigateToInicio: () -> Unit,
    mqttService: MqttService,
    auth: AuthManager
) {
    Scaffold(
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {

            if (tipoDispositivo == "Sensor Temperatura") {
                ConfiguracionSensorTemperatura(navigateToInicio, auth)
            } else if (tipoDispositivo == "Sensor de luz") {
                ConfiguracionSensorLuz(navigateToInicio, mqttService, auth)
            } else if (tipoDispositivo == "Sensor Movimiento") {
                ConfiguracionSensorMovimiento(navigateToInicio, auth)
            } else if (tipoDispositivo == "Sensor Vibración") {
                ConfiguracionSensorVibracion(navigateToInicio, auth)
            } else if (tipoDispositivo == "Sensor Nivel de Agua") {
                ConfiguracionSensorNivelAgua(navigateToInicio, auth)
            } else if (tipoDispositivo == "Sensor de Presión") {
                ConfiguracionSensorPresion(navigateToInicio, auth)
            } else if (tipoDispositivo == "Sensor de Apertura") {
                ConfiguracionSensorApertura(navigateToInicio, mqttService, auth)
            } else if (tipoDispositivo == "Sensor de Calidad del Aire") {
                ConfiguracionSensorCalidadAire(navigateToInicio, auth)
            } else if (tipoDispositivo == "Actuador Valvula") {
                ConfiguracionActuadorValvula(navigateToInicio, mqttService, auth)
            } else if (tipoDispositivo == "Cerradura Electrónica") {
                ConfiguracionCerraduraElectronica(navigateToInicio, mqttService, auth)
            } else if (tipoDispositivo == "Controlador Iluminación") {
                ConfiguracionControladorIluminacion(navigateToInicio, mqttService, auth)
            } else if (tipoDispositivo == "Controlador Clima") {
                ConfiguracionControladorClima(navigateToInicio, auth)
            } else if (tipoDispositivo == "Medidor de Consumo de Agua") {
                ConfiguracionMedidorConsumoAgua(navigateToInicio, auth)
            } else if (tipoDispositivo == "Medidor de gas") {
                ConfiguracionMedidorGas(navigateToInicio, auth)
            } else {
                Text(text = "Configuración no disponible para este dispositivo")

            }
        }
    }
}

//SENSORES:
@Composable
fun ConfiguracionSensorTemperatura(navigateToInicio: () -> Unit, auth: AuthManager) {
    var nombre by remember { mutableStateOf("") }
    var ubicacion by remember { mutableStateOf("") }
    var grados by rememberSaveable { mutableStateOf(0.00) }
    var humedad by rememberSaveable { mutableStateOf(0.00) }

    TipoDispositivoCreado.tipoDispositivoCreado = "sensortemperatura"

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
            value = ubicacion,
            onValueChange = { ubicacion = it },
            label = { Text("Ubicacion") }
        )

        val sensor = SensorTemperatura(
            "",
            userId = auth.getCurrentUser()?.uid,
            nombre,
            "Sensor",
            ubicacion,
            R.drawable.imgsensortermometro,
            grados,
            humedad
        )
        Button(
            onClick = {
                navigateToInicio()
                RepositoryList.addDispositivos(null)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Actualizar")
        }
    }
}

@Composable
fun ConfiguracionSensorLuz(navigateToInicio: () -> Unit, mqttService: MqttService, auth: AuthManager) {
    var nombre by remember { mutableStateOf("") }
    var ubicacion by remember { mutableStateOf("") }
    var encendido by rememberSaveable { mutableStateOf(false) }

    TipoDispositivoCreado.tipoDispositivoCreado = "sensorluz"

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
            "",
            userId = auth.getCurrentUser()?.uid,
            nombre,
            "Sensor",
            ubicacion,
            R.drawable.imgsensorluz,
            encendido
        )
        Button(
            onClick = {
                navigateToInicio()
                RepositoryList.addDispositivos(null)
                mqttService.publish("sensorluz", encendido.toString())
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Actualizar")
        }
    }
}

@Composable
fun ConfiguracionSensorMovimiento(navigateToInicio: () -> Unit, auth: AuthManager) {
    var nombre by remember { mutableStateOf("") }
    var ubicacion by remember { mutableStateOf("") }
    var estado by remember { mutableStateOf(false) }

    TipoDispositivoCreado.tipoDispositivoCreado = "sensormovimiento"
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
            value = ubicacion,
            onValueChange = { ubicacion = it },
            label = { Text("Ubicacion") }
        )
        val sensor =
            SensorMovimiento(
                "",
                userId = auth.getCurrentUser()?.uid,
                nombre,
                "Sensor",
                ubicacion,
                R.drawable.imgsensormovimiento,
                estado
            )
        Button(
            onClick = {
                navigateToInicio()
                RepositoryList.addDispositivos(null)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Actualizar")
        }
    }
}

@Composable
fun ConfiguracionSensorVibracion(navigateToInicio: () -> Unit, auth: AuthManager) {
    var nombre by remember { mutableStateOf("") }
    var ubicacion by remember { mutableStateOf("") }
    var estado by remember { mutableStateOf(false) }

    TipoDispositivoCreado.tipoDispositivoCreado = "sensorvibracion"
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
            value = ubicacion,
            onValueChange = { ubicacion = it },
            label = { Text("Ubicacion") }
        )
        val sensor =
            SensorVibracion(
                "",
                userId = auth.getCurrentUser()?.uid,
                nombre,
                "Sensor",
                ubicacion,
                R.drawable.imgsensorvibracion,
                estado
            )
        Button(
            onClick = {
                navigateToInicio()
                RepositoryList.addDispositivos(null)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Actualizar")
        }
    }
}

@Composable
fun ConfiguracionSensorNivelAgua(navigateToInicio: () -> Unit, auth: AuthManager) {
    var nombre by remember { mutableStateOf("") }
    var ubicacion by remember { mutableStateOf("") }
    var litros by remember { mutableStateOf(0.00) }

    TipoDispositivoCreado.tipoDispositivoCreado = "sensornivelagua"

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
            value = ubicacion,
            onValueChange = { ubicacion = it },
            label = { Text("Ubicacion") }
        )
        val sensor =
            SensorNivelAgua(
                "",
                userId = auth.getCurrentUser()?.uid,
                nombre,
                "Sensor",
                ubicacion,
                R.drawable.imgsensornivelagua,
                litros
            )
        Button(
            onClick = {
                navigateToInicio()
                RepositoryList.addDispositivos(null)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Actualizar")
        }
    }
}

@Composable
fun ConfiguracionSensorPresion(navigateToInicio: () -> Unit, auth: AuthManager) {
    var nombre by remember { mutableStateOf("") }
    var ubicacion by remember { mutableStateOf("") }
    var presion by remember { mutableStateOf(0.00) }

    TipoDispositivoCreado.tipoDispositivoCreado = "sensorpresion"

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
            value = ubicacion,
            onValueChange = { ubicacion = it },
            label = { Text("Ubicacion") }
        )
        val sensor =
            SensorPresion(
                "",
                userId = auth.getCurrentUser()?.uid,
                nombre,
                "Sensor",
                ubicacion,
                R.drawable.imgsensorpresion,
                presion
            )
        Button(
            onClick = {
                navigateToInicio()
                RepositoryList.addDispositivos(null)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Actualizar")
        }
    }
}

@Composable
fun ConfiguracionSensorApertura(navigateToInicio: () -> Unit, mqttService: MqttService, auth: AuthManager) {
    var nombre by remember { mutableStateOf("") }
    var ubicacion by remember { mutableStateOf("") }
    var estado by rememberSaveable { mutableStateOf(false) }

    TipoDispositivoCreado.tipoDispositivoCreado = "sensorapertura"

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
                "",
                userId = auth.getCurrentUser()?.uid,
                nombre,
                "Sensor",
                ubicacion,
                R.drawable.imgsensorapertura,
                estado
            )
        Button(
            onClick = {
                navigateToInicio()
                RepositoryList.addDispositivos(null)
                mqttService.publish("sensorapertura", estado.toString())
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Actualizar")
        }
    }
}

@Composable
fun ConfiguracionSensorCalidadAire(navigateToInicio: () -> Unit, auth: AuthManager) {
    var nombre by remember { mutableStateOf("") }
    var ubicacion by remember { mutableStateOf("") }
    var calidad by remember { mutableStateOf("") }

    TipoDispositivoCreado.tipoDispositivoCreado = "sensorcalidadaire"

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
            value = ubicacion,
            onValueChange = { ubicacion = it },
            label = { Text("Ubicacion") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        val sensor =
            SensorCalidadAire(
                "",
                userId = auth.getCurrentUser()?.uid,
                nombre,
                "Sensor",
                ubicacion,
                R.drawable.imgsensorcalidadaire,
                calidad
            )
        Button(
            onClick = {
                navigateToInicio()
                RepositoryList.addDispositivos(null)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Actualizar")
        }
    }
}

//ACTUADORES:

@Composable
fun ConfiguracionActuadorValvula(navigateToInicio: () -> Unit, mqttService: MqttService, auth: AuthManager) {
    var nombre by remember { mutableStateOf("") }
    var ubicacion by remember { mutableStateOf("") }
    var estado by rememberSaveable { mutableStateOf(false) }

    TipoDispositivoCreado.tipoDispositivoCreado = "actuadorvalvula"

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
                "",
                userId = auth.getCurrentUser()?.uid,
                nombre,
                "Actuador",
                ubicacion,
                R.drawable.imgactuadorvalvula,
                estado
            )
        Button(
            onClick = {
                navigateToInicio()
                RepositoryList.addDispositivos(null)
                mqttService.publish("actuadorvalvula", estado.toString())
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Actualizar")
        }
    }
}

@Composable
fun ConfiguracionCerraduraElectronica(navigateToInicio: () -> Unit, mqttService: MqttService, auth: AuthManager) {
    var nombre by remember { mutableStateOf("") }
    var ubicacion by remember { mutableStateOf("") }
    var estado by rememberSaveable { mutableStateOf(false) }

    TipoDispositivoCreado.tipoDispositivoCreado = "cerraduraelectronica"

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
            "",
            userId = auth.getCurrentUser()?.uid,
            nombre,
            "Actuador",
            ubicacion,
            R.drawable.imgcerraduraelectronica,
            estado
        )
        Button(
            onClick = {
                navigateToInicio()
                RepositoryList.addDispositivos(null)
                mqttService.publish("cerraduraelectronica", estado.toString())
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Actualizar")
        }
    }
}

@Composable
fun ConfiguracionControladorIluminacion(navigateToInicio: () -> Unit, mqttService: MqttService, auth: AuthManager) {
    var nombre by remember { mutableStateOf("") }
    var ubicacion by remember { mutableStateOf("") }
    var estado by rememberSaveable { mutableStateOf(false) }

    TipoDispositivoCreado.tipoDispositivoCreado = "controladoriluminacion"
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
            "",
            userId = auth.getCurrentUser()?.uid,
            nombre,
            "Actuador",
            ubicacion,
            R.drawable.imgcontroladorluz,
            estado
        )
        Button(
            onClick = {
                navigateToInicio()
                RepositoryList.addDispositivos(null)
                mqttService.publish("controladoriluminacion", estado.toString())
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Actualizar")
        }
    }
}

//Monitoreo:

@Composable
fun ConfiguracionControladorClima(navigateToInicio: () -> Unit, auth: AuthManager) {
    var nombre by remember { mutableStateOf("") }
    var ubicacion by remember { mutableStateOf("") }
    var grados by remember { mutableStateOf(0.00) }
    var humedad by remember { mutableStateOf(0.00) }

    TipoDispositivoCreado.tipoDispositivoCreado = "controladorclima"

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
            value = ubicacion,
            onValueChange = { ubicacion = it },
            label = { Text("Ubicacion") }
        )
        val monitoreo = ControladorClima(
            "",
            userId = auth.getCurrentUser()?.uid,
            nombre,
            "Monitoreo",
            ubicacion,
            R.drawable.imgcontroladorclima,
            grados,
            humedad
        )
        Button(
            onClick = {
                navigateToInicio()
                RepositoryList.addDispositivos(null)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Actualizar")
        }
    }
}

@Composable
fun ConfiguracionMedidorConsumoAgua(navigateToInicio: () -> Unit, auth: AuthManager) {
    var nombre by remember { mutableStateOf("") }
    var ubicacion by remember { mutableStateOf("") }
    var litros by rememberSaveable { mutableStateOf(0.00) }

    TipoDispositivoCreado.tipoDispositivoCreado = "consumoagua"

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
            value = ubicacion,
            onValueChange = { ubicacion = it },
            label = { Text("Ubicacion") }
        )
        Spacer(modifier = Modifier.height(8.dp))

        val monitoreo =
            MedidorConsumoAgua("",
                userId = auth.getCurrentUser()?.uid,
                nombre,
                "Monitoreo",
                ubicacion,
                R.drawable.imgconsumoagua,
                litros
            )
        Button(
            onClick = {
                navigateToInicio()
                RepositoryList.addDispositivos(null)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Actualizar")
        }
    }
}

@Composable
fun ConfiguracionMedidorGas(navigateToInicio: () -> Unit, auth: AuthManager) {
    var nombre by remember { mutableStateOf("") }
    var ubicacion by remember { mutableStateOf("") }
    var m3State by remember { mutableStateOf(0.00) }
    var m3toString by remember { mutableStateOf(m3State.toString()) } //Xq el OutlinedTextField necesita un string para q funcione

    TipoDispositivoCreado.tipoDispositivoCreado = "medidorgas"

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
            value = ubicacion,
            onValueChange = { ubicacion = it },
            label = { Text("Ubicacion") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        val monitoreo = MedidorGas(
            "",
            userId = auth.getCurrentUser()?.uid,
            nombre,
            "Monitoreo",
            ubicacion,
            R.drawable.imgconsumogas,
            m3toString.toDouble()
        )
        Button(
            onClick = {
                navigateToInicio()
                RepositoryList.addDispositivos(null)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Actualizar")
        }
    }
}


