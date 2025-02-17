package com.proyectomoviles.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
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
    mqttService: MqttService
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
                ConfiguracionSensorTemperatura(navigateToInicio, mqttService)
            } else if (tipoDispositivo == "Sensor de luz") {
                ConfiguracionSensorLuz(navigateToInicio)
            } else if (tipoDispositivo == "Sensor Movimiento") {
                ConfiguracionSensorMovimiento(navigateToInicio)
            } else if (tipoDispositivo == "Sensor Vibración") {
                ConfiguracionSensorVibracion(navigateToInicio)
            } else if (tipoDispositivo == "Sensor Nivel de Agua") {
                ConfiguracionSensorNivelAgua(navigateToInicio)
            } else if (tipoDispositivo == "Sensor de Presión") {
                ConfiguracionSensorPresion(navigateToInicio)
            } else if (tipoDispositivo == "Sensor de Apertura") {
                ConfiguracionSensorApertura(navigateToInicio)
            } else if (tipoDispositivo == "Sensor de Calidad del Aire") {
                ConfiguracionSensorCalidadAire(navigateToInicio)
            } else if (tipoDispositivo == "Actuador Valvula") {
                ConfiguracionActuadorValvula(navigateToInicio)
            } else if (tipoDispositivo == "Cerradura Electrónica") {
                ConfiguracionCerraduraElectronica(navigateToInicio)
            } else if (tipoDispositivo == "Controlador Iluminación") {
                ConfiguracionControladorIluminacion(navigateToInicio)
            } else if (tipoDispositivo == "Controlador Clima") {
                ConfiguracionControladorClima(navigateToInicio)
            } else if (tipoDispositivo == "Medidor de Consumo de Agua") {
                ConfiguracionMedidorConsumoAgua(navigateToInicio)
            } else if (tipoDispositivo == "Medidor de gas") {
                ConfiguracionMedidorGas(navigateToInicio)
            } else {
                Text(text = "Configuración no disponible para este dispositivo")

            }
        }
    }
}

//SENSORES:
@Composable
fun ConfiguracionSensorTemperatura(navigateToInicio: () -> Unit, mqttService: MqttService) {
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

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = grados.toString(),
            onValueChange = { grados = it.toDouble() },
            label = { Text("Grados") }
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = humedad.toString(),
            onValueChange = { humedad = it.toDouble() },
            label = { Text("Humedad") }
        )
        val sensor = SensorTemperatura(
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
                RepositoryList.addDispositivos(sensor)
                mqttService.publish("grados", grados.toString())
                mqttService.publish("humedad", humedad.toString())
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Actualizar")
        }
    }
}

@Composable
fun ConfiguracionSensorLuz(navigateToInicio: () -> Unit) {
    var nombre by remember { mutableStateOf("") }
    var ubicacion by remember { mutableStateOf("") }
    var encendido by remember { mutableStateOf(false) }
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
        val sensor = SensorLuz(nombre, "Sensor", ubicacion, R.drawable.imgsensorluz, encendido)
        Button(
            onClick = {
                navigateToInicio()
                RepositoryList.addDispositivos(sensor)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Actualizar")
        }
    }
}

@Composable
fun ConfiguracionSensorMovimiento(navigateToInicio: () -> Unit) {
    var nombre by remember { mutableStateOf("") }
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
            value = ubicacion,
            onValueChange = { ubicacion = it },
            label = { Text("Ubicacion") }
        )
        Text("Estado de encendido:")
        Switch(
            checked = estado,
            onCheckedChange = { estado = !estado }
        )
        val sensor =
            SensorMovimiento(nombre, "Sensor", ubicacion, R.drawable.imgsensormovimiento, estado)
        Button(
            onClick = {
                navigateToInicio()
                RepositoryList.addDispositivos(sensor)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Actualizar")
        }
    }
}

@Composable
fun ConfiguracionSensorVibracion(navigateToInicio: () -> Unit) {
    var nombre by remember { mutableStateOf("") }
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
            value = ubicacion,
            onValueChange = { ubicacion = it },
            label = { Text("Ubicacion") }
        )
        Text("Estado de encendido:")
        Switch(
            checked = estado,
            onCheckedChange = { estado = !estado }
        )
        val sensor =
            SensorVibracion(nombre, "Sensor", ubicacion, R.drawable.imgsensorvibracion, estado)
        Button(
            onClick = {
                navigateToInicio()
                RepositoryList.addDispositivos(sensor)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Actualizar")
        }
    }
}

@Composable
fun ConfiguracionSensorNivelAgua(navigateToInicio: () -> Unit) {
    var nombre by remember { mutableStateOf("") }
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
            value = ubicacion,
            onValueChange = { ubicacion = it },
            label = { Text("Ubicacion") }
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = litros.toString(),
            onValueChange = { litros = it.toDouble() },
            label = { Text("Litros") }
        )
        val sensor =
            SensorNivelAgua(nombre, "Sensor", ubicacion, R.drawable.imgsensornivelagua, litros)
        Button(
            onClick = {
                navigateToInicio()
                RepositoryList.addDispositivos(sensor)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Actualizar")
        }
    }
}

@Composable
fun ConfiguracionSensorPresion(navigateToInicio: () -> Unit) {
    var nombre by remember { mutableStateOf("") }
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
            value = ubicacion,
            onValueChange = { ubicacion = it },
            label = { Text("Ubicacion") }
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = presion.toString(),
            onValueChange = { presion = it.toDouble() },
            label = { Text("Litros") }
        )
        val sensor =
            SensorPresion(nombre, "Sensor", ubicacion, R.drawable.imgsensorpresion, presion)
        Button(
            onClick = {
                navigateToInicio()
                RepositoryList.addDispositivos(sensor)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Actualizar")
        }
    }
}

@Composable
fun ConfiguracionSensorApertura(navigateToInicio: () -> Unit) {
    var nombre by remember { mutableStateOf("") }
    var ubicacion by remember { mutableStateOf("") }
    var estado by remember { mutableStateOf(false) }

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
            SensorApertura(nombre, "Sensor", ubicacion, R.drawable.imgsensorapertura, estado)
        Button(
            onClick = {
                navigateToInicio()
                RepositoryList.addDispositivos(sensor)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Actualizar")
        }
    }
}

@Composable
fun ConfiguracionSensorCalidadAire(navigateToInicio: () -> Unit) {
    var nombre by remember { mutableStateOf("") }
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
            value = ubicacion,
            onValueChange = { ubicacion = it },
            label = { Text("Ubicacion") }
        )
        Text(text = "Índice de Calidad del Aire:")
        Spacer(modifier = Modifier.height(8.dp))

        // RadioButtons
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = calidad == "Favorable",
                onClick = { calidad = "Favorable" }
            )
            Text(
                text = "Favorable",
                modifier = Modifier.padding(start = 6.dp)
            )
        }

        Spacer(modifier = Modifier.height(7.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = calidad == "Desfavorable",
                onClick = { calidad = "Desfavorable" }
            )
            Text(
                text = "Desfavorable",
                modifier = Modifier.padding(start = 6.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
        val sensor =
            SensorCalidadAire(nombre, "Sensor", ubicacion, R.drawable.imgsensorcalidadaire, calidad)
        Button(
            onClick = {
                navigateToInicio()
                RepositoryList.addDispositivos(sensor)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Actualizar")
        }
    }
}

//ACTUADORES:

@Composable
fun ConfiguracionActuadorValvula(navigateToInicio: () -> Unit) {
    var nombre by remember { mutableStateOf("") }
    var ubicacion by remember { mutableStateOf("") }
    var estado by remember { mutableStateOf(false) }

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
            ActuadorValvula(nombre, "Actuador", ubicacion, R.drawable.imgactuadorvalvula, estado)
        Button(
            onClick = {
                navigateToInicio()
                RepositoryList.addDispositivos(actuador)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Actualizar")
        }
    }
}

@Composable
fun ConfiguracionCerraduraElectronica(navigateToInicio: () -> Unit) {
    var nombre by remember { mutableStateOf("") }
    var ubicacion by remember { mutableStateOf("") }
    var estado by remember { mutableStateOf(false) }

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
            nombre,
            "Actuador",
            ubicacion,
            R.drawable.imgcerraduraelectronica,
            estado
        )
        Button(
            onClick = {
                navigateToInicio()
                RepositoryList.addDispositivos(actuador)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Actualizar")
        }
    }
}

@Composable
fun ConfiguracionControladorIluminacion(navigateToInicio: () -> Unit) {
    var nombre by remember { mutableStateOf("") }
    var ubicacion by remember { mutableStateOf("") }
    var estado by remember { mutableStateOf(false) }
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
            nombre,
            "Actuador",
            ubicacion,
            R.drawable.imgcontroladorluz,
            estado
        )
        Button(
            onClick = {
                navigateToInicio()
                RepositoryList.addDispositivos(actuador)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Actualizar")
        }
    }
}

//Monitoreo:

@Composable
fun ConfiguracionControladorClima(navigateToInicio: () -> Unit) {
    var nombre by remember { mutableStateOf("") }
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
            value = ubicacion,
            onValueChange = { ubicacion = it },
            label = { Text("Ubicacion") }
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = grados.toString(),
            onValueChange = { grados = it.toDouble() },
            label = { Text("Grados Cº") }
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = humedad.toString(),
            onValueChange = { humedad = it.toDouble() },
            label = { Text("Humedad") }
        )
        val monitoreo = ControladorClima(
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
                RepositoryList.addDispositivos(monitoreo)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Actualizar")
        }
    }
}

@Composable
fun ConfiguracionMedidorConsumoAgua(navigateToInicio: () -> Unit) {
    var nombre by remember { mutableStateOf("") }
    var ubicacion by remember { mutableStateOf("") }
    var litros by remember { mutableStateOf(0.00) }
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

        Text(text = "Consumo actual: $litros L")

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = litros.toString(),
            onValueChange = { litros = it.toDouble() },
            label = { Text("Consumo de Agua (Litros)") }

        )
        val monitoreo =
            MedidorConsumoAgua(nombre, "Monitoreo", ubicacion, R.drawable.imgconsumoagua, litros)
        Button(
            onClick = {
                navigateToInicio()
                RepositoryList.addDispositivos(monitoreo)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Actualizar")
        }
    }
}

@Composable
fun ConfiguracionMedidorGas(navigateToInicio: () -> Unit) {
    var nombre by remember { mutableStateOf("") }
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
            value = ubicacion,
            onValueChange = { ubicacion = it },
            label = { Text("Ubicacion") }
        )
        OutlinedTextField(
            value = m3toString,
            onValueChange = { input ->
                // Validar que sea un número o si está vacío
                if (input.toFloatOrNull() != null || input.isEmpty()) {
                    m3toString = input
                }
            },
            label = { Text("Consumo (m³)") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )
        Spacer(modifier = Modifier.height(16.dp))
        val monitoreo = MedidorGas(
            nombre,
            "Monitoreo",
            ubicacion,
            R.drawable.imgconsumogas,
            m3toString.toDouble()
        )
        Button(
            onClick = {
                navigateToInicio()
                RepositoryList.addDispositivos(monitoreo)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Actualizar")
        }
    }
}


