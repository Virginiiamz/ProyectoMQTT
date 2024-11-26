package com.proyectomoviles.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
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

@Composable
fun Prueba(tipoDispositivo: String, navigateToInicio: () -> Unit) {
    Column {
        Button(
            onClick = {navigateToInicio()}
        ) {
            Text("Inicio")
        }
    }
}

@Composable
fun ConfiguracionSensorTemperatura(navigateToInicio: () -> Unit) {
    var nombre by remember { mutableStateOf("") }
    var ubicacion by remember { mutableStateOf("") }
    var grados by remember { mutableStateOf(0.00) }
    var humedad by remember { mutableStateOf(0.00) }

    Column (
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
        val sensorNuevo= SensorTemperatura(nombre, "Sensor de Temperatura", ubicacion, 0, grados, humedad)
        Button(
            onClick = {navigateToInicio()},
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Actualizar")
        }
    }
}

@Composable
fun ConfiguracionScreen(
    tipoDispositivo: String,
    dispositivo: Dispositivo?,
    navigateToInicio: () -> Unit
) {
    Scaffold(
        topBar = {
            //TopAppBar me da errores así que lo hago así
//            ComoTopAppBarSinTopAppBar(dispositivo, onBackPressed = {  })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {

            if (tipoDispositivo == "Sensor Temperatura"){
                ConfiguracionSensorTemperatura(navigateToInicio)
            } else if (tipoDispositivo == "Sensor de luz"){
                ConfiguracionSensorLuz(navigateToInicio)
            } else if (tipoDispositivo == "Sensor Movimiento"){
                ConfiguracionSensorMovimiento(navigateToInicio)
            } else if (tipoDispositivo == "Sensor Vibración"){
                ConfiguracionSensorVibracion(navigateToInicio)
            } else if (tipoDispositivo == "Sensor Nivel de Agua"){
                ConfiguracionSensorNivelAgua(navigateToInicio)
            } else if (tipoDispositivo == "Sensor de Presión"){
                ConfiguracionSensorPresion(navigateToInicio)
            } else if (tipoDispositivo == "Sensor de Apertura"){
                ConfiguracionSensorApertura(navigateToInicio)
            } else if (tipoDispositivo == "Sensor de Calidad del Aire"){
                ConfiguracionSensorCalidadAire(navigateToInicio)
            } else if (tipoDispositivo == "Actuador Valvula"){
                ConfiguracionActuadorValvula(navigateToInicio)
            } else if(tipoDispositivo=="Cerradura Electrónica"){
                ConfiguracionCerraduraElectronica(navigateToInicio)
            } else if (tipoDispositivo == "Controlador Iluminación") {
                ConfiguracionControladorIluminacion(navigateToInicio)
            }else if (tipoDispositivo == "Controlador Clima"){
                ConfiguracionControladorClima(navigateToInicio)
            } else if (tipoDispositivo == "Medidor de Consumo de Agua"){
                ConfiguracionMedidorConsumoAgua(navigateToInicio)
            } else if (tipoDispositivo == "Medidor de gas"){
                ConfiguracionMedidorGas(navigateToInicio)
            } else {
                Text(text = "Configuración no disponible para este dispositivo")

            }
        }
    }
}

//@Composable
//fun ComoTopAppBarSinTopAppBar(dispositivo: Dispositivo, onBackPressed: () -> Unit) {
//    // TopAppBar me odia así que lo hago con box y row os vale?
//    Box(
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(90.dp)
//            .background(MaterialTheme.colorScheme.primary)
//            .padding(16.dp)
//
//    ) {
//        Row(
//            modifier = Modifier.align(Alignment.CenterStart)
//        ) {
//            IconButton(onClick = onBackPressed) {
//                Icon(
//                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
//                    contentDescription = "Atrás",
//                    tint = MaterialTheme.colorScheme.onPrimary
//                )
//            }
//            Text(
//                text = "Configuración de ${dispositivo.nombre}",
//                style = MaterialTheme.typography.titleLarge,
//                color = MaterialTheme.colorScheme.onPrimary,
//                modifier = Modifier.align(Alignment.CenterVertically)
//            )
//        }
//    }
//}


//SENSORES:

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
        Button(
            onClick = {navigateToInicio()},
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
    )  {
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
        Button(
            onClick = {navigateToInicio()},
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
    )  {
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
        Button(
            onClick = {navigateToInicio()},
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
    )  {
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
        Button(
            onClick = {navigateToInicio()},
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
    )  {
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
        Button(
            onClick = {navigateToInicio()},
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
    )  {
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
        Button(
            onClick = {navigateToInicio()},
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

        Button(
            onClick = {navigateToInicio()},
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Actualizar")
        }
    }
}
//
//@Composable
//fun ConfiguracionSensorGas(navigateToInicio: () -> Unit) {
//    var nombre by remember { mutableStateOf("") }
//    var ubicacion by remember { mutableStateOf("") }
//    var co2 by remember { mutableStateOf(0.00) }
//    var ch4 by remember { mutableStateOf(0.00) }
//
//    Column(
//        modifier = Modifier.fillMaxSize(),
//        horizontalAlignment = Alignment.CenterHorizontally
//    )  {
//        Text(text = "Configuración del Sensor de Gas")
//        Spacer(modifier = Modifier.height(8.dp))
//
//        OutlinedTextField(
//            modifier = Modifier.fillMaxWidth(),
//            value = nombre,
//            onValueChange = { nombre = it },
//            label = { Text("Nombre") }
//        )
//        OutlinedTextField(
//            value = ubicacion,
//            modifier = Modifier.fillMaxWidth(),
//            onValueChange = { ubicacion = it },
//            label = { Text("Ubicacion") }
//        )
//        Row(
//            modifier = Modifier.fillMaxSize(),
//            verticalAlignment = Alignment.CenterVertically,
//            horizontalArrangement = Arrangement.Center
//        ) {
//            OutlinedTextField(
//                value = co2.toString(),
//                onValueChange = { co2 = it.toDouble() },
//                label = { Text("Concentracion de CO2") }
//            )
//            OutlinedTextField(
//                value = ch4.toString(),
//                onValueChange = { ch4 = it.toDouble() },
//                label = { Text("Concentracion de CH4") }
//            )
//        }
//
//        Button(
//            onClick = {navigateToInicio()},
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            Text("Actualizar")
//        }
//    }
//}



//ACTUADORES:

@Composable
fun ConfiguracionActuadorValvula(navigateToInicio: () -> Unit) {
    var nombre by remember { mutableStateOf("") }
    var ubicacion by remember { mutableStateOf("") }
    var estado by remember { mutableStateOf(false) }

        Column (
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

            Button(
                onClick = {navigateToInicio()},
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

    Column (
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

        Button(
            onClick = {navigateToInicio()},
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
    )  {
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
        Button(
            onClick = {navigateToInicio()},
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Actualizar")
        }
    }
}

//MOnitoreo:

@Composable
fun ConfiguracionControladorClima(navigateToInicio: () -> Unit) {
    var nombre by remember { mutableStateOf("") }
    var ubicacion by remember { mutableStateOf("") }
    var grados by remember { mutableStateOf(0.00) }
    var humedad by remember { mutableStateOf(0.00) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    )  {
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

        Button(
            onClick = {navigateToInicio()},
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
    )  {
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

        Button(
            onClick = {navigateToInicio()},
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
    )  {

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

        Button(
            onClick = {navigateToInicio()},
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Actualizar")
        }
    }
}


