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
import com.proyectomoviles.dispositivos.SensorGas
import com.proyectomoviles.dispositivos.SensorLuz
import com.proyectomoviles.dispositivos.SensorMovimiento
import com.proyectomoviles.dispositivos.SensorNivelAgua
import com.proyectomoviles.dispositivos.SensorPresion
import com.proyectomoviles.dispositivos.SensorTemperatura
import com.proyectomoviles.dispositivos.SensorVibracion

@Composable
fun Prueba(tipoDispositivo: String, navigateToInicio: () -> Unit) {
    Column {
        Text(tipoDispositivo)
        ConfiguracionSensorTemperatura2(navigateToInicio)
        Button(
            onClick = {navigateToInicio()}
        ) {
            Text("Inicio")
        }
    }
}

@Composable
fun ConfiguracionSensorTemperatura2(navigateToInicio: () -> Unit) {
    var nombre by remember { mutableStateOf("") }
    var ubicacion by remember { mutableStateOf("") }
    var grados by remember { mutableStateOf(0.00) }
    var humedad by remember { mutableStateOf(0.00) }
//    var sensorNuevo by remember { mutableStateOf(emptyCl) }

    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
//        Image(
//            painter = painterResource(id = sensorTemperatura.imagen),
//            contentDescription = "Imagen del medidor de gas",
//            modifier = Modifier
//                .size(128.dp)
//                .padding(bottom = 16.dp)
//                .align(alignment = Alignment.CenterHorizontally)
//
//        )
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
    navController: NavController,
    dispositivo: Dispositivo
) {
    Scaffold(
        topBar = {
            //TopAppBar me da errores así que lo hago así
            ComoTopAppBarSinTopAppBar(dispositivo, onBackPressed = { navController.navigateUp() })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            when (dispositivo) {
                is ControladorIluminacion -> ConfiguracionControladorIluminacion(dispositivo)
                is SensorGas -> ConfiguracionSensorGas(dispositivo)
                is MedidorGas -> ConfiguracionMedidorGas(dispositivo)
                is  SensorLuz -> ConfiguracionSensorLuz(dispositivo)
                is  SensorTemperatura -> ConfiguracionSensorTemperatura(dispositivo)
                is  SensorMovimiento -> ConfiguracionSensorMovimiento(dispositivo)
                is  SensorVibracion -> ConfiguracionSensorVibracion(dispositivo)
                is  SensorNivelAgua -> ConfiguracionSensorNivelAgua(dispositivo)
                is  SensorPresion -> ConfiguracionSensorPresion(dispositivo)
                is  SensorApertura -> ConfiguracionSensorApertura(dispositivo)
                is  SensorCalidadAire -> ConfiguracionSensorCalidadAire(dispositivo)
                is  ActuadorValvula -> ConfiguracionActuadorValvula(dispositivo)
                is  CerraduraElectronica -> ConfiguracionCerraduraElectronica(dispositivo)
                is ControladorClima -> ConfiguracionControladorClima(dispositivo)
                is MedidorConsumoAgua -> ConfiguracionMedidorConsumoAgua(dispositivo)
                else -> Text(text = "Configuración no disponible para este dispositivo")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    navController.navigate("Inicio")
                },
                modifier = Modifier.fillMaxWidth(),

                ) {
                Text(text = "Aceptar", style = MaterialTheme.typography.titleLarge,
                )
            }
        }
    }
}

@Composable
fun ComoTopAppBarSinTopAppBar(dispositivo: Dispositivo, onBackPressed: () -> Unit) {
    // TopAppBar me odia así que lo hago con box y row os vale?
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
            .background(MaterialTheme.colorScheme.primary)
            .padding(16.dp)

    ) {
        Row(
            modifier = Modifier.align(Alignment.CenterStart)
        ) {
            IconButton(onClick = onBackPressed) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Atrás",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
            Text(
                text = "Configuración de ${dispositivo.nombre}",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
    }
}


//SENSORES:

@Composable
fun ConfiguracionSensorLuz(sensorLuz: SensorLuz) {
    var nombre by remember { mutableStateOf(sensorLuz.nombre) }
    var ubicacion by remember { mutableStateOf(sensorLuz.ubicacion) }
    var encendido by remember { mutableStateOf(sensorLuz.estadoEncendido) }
    var sensorNuevo by remember { mutableStateOf(sensorLuz) }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = sensorLuz.imagen),
            contentDescription = "Imagen del sensor de luz",
            modifier = Modifier
                .size(128.dp)
                .padding(bottom = 16.dp)
                .align(alignment = Alignment.CenterHorizontally)

        )
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
       sensorNuevo= SensorLuz(nombre, "Sensor de Luz", ubicacion, sensorLuz.imagen, encendido)
        Button(
            onClick = {},
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Actualizar")
        }
    }
}
@Composable
fun ConfiguracionSensorTemperatura(sensorTemperatura: SensorTemperatura) {
    var nombre by remember { mutableStateOf(sensorTemperatura.nombre) }
    var ubicacion by remember { mutableStateOf(sensorTemperatura.ubicacion) }
    var grados by remember { mutableStateOf(sensorTemperatura.grados) }
    var humedad by remember { mutableStateOf(sensorTemperatura.humedad) }
    var sensorNuevo by remember { mutableStateOf(sensorTemperatura) }

    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = sensorTemperatura.imagen),
            contentDescription = "Imagen del medidor de gas",
            modifier = Modifier
                .size(128.dp)
                .padding(bottom = 16.dp)
                .align(alignment = Alignment.CenterHorizontally)

        )
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
        sensorNuevo= SensorTemperatura(nombre, "Sensor de Temperatura", ubicacion, sensorTemperatura.imagen, grados, humedad)
        Button(
            onClick = {},
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Actualizar")
        }
    }
}
@Composable
fun ConfiguracionSensorMovimiento(sensorMovimiento: SensorMovimiento) {
    var nombre by remember { mutableStateOf(sensorMovimiento.nombre) }
    var ubicacion by remember { mutableStateOf(sensorMovimiento.ubicacion) }
    var estado by remember { mutableStateOf(sensorMovimiento.estado) }
    var sensorNuevo by remember { mutableStateOf(sensorMovimiento) }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    )  {
        Image(
            painter = painterResource(id = sensorMovimiento.imagen),
            contentDescription = "Imagen del medidor de gas",
            modifier = Modifier
                .size(128.dp)
                .padding(bottom = 16.dp)
                .align(alignment = Alignment.CenterHorizontally)

        )
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
        sensorNuevo= SensorMovimiento(nombre, "Sensor de Movimiento", ubicacion, sensorMovimiento.imagen, estado)
        Button(
            onClick = {},
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Actualizar")
        }
    }
}
@Composable
fun ConfiguracionSensorVibracion(sensorVibracion: SensorVibracion) {
    var nombre by remember { mutableStateOf(sensorVibracion.nombre) }
    var ubicacion by remember { mutableStateOf(sensorVibracion.ubicacion) }
    var estado by remember { mutableStateOf(sensorVibracion.estado) }
    var sensorNuevo by remember { mutableStateOf(sensorVibracion) }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    )  {
        Image(
            painter = painterResource(id = sensorVibracion.imagen),
            contentDescription = "Imagen del medidor de gas",
            modifier = Modifier
                .size(128.dp)
                .padding(bottom = 16.dp)
                .align(alignment = Alignment.CenterHorizontally)

        )
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
        sensorNuevo= SensorVibracion(nombre, "Sensor de Vibracion", ubicacion, sensorVibracion.imagen, estado)
        Button(
            onClick = {},
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Actualizar")
        }
    }
}

@Composable
fun ConfiguracionSensorNivelAgua(sensorNivelAgua: SensorNivelAgua) {
    var nombre by remember { mutableStateOf(sensorNivelAgua.nombre) }
    var ubicacion by remember { mutableStateOf(sensorNivelAgua.ubicacion) }
    var litros by remember { mutableStateOf(sensorNivelAgua.litros) }
    var sensorNuevo by remember { mutableStateOf(sensorNivelAgua) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    )  {
        Image(
            painter = painterResource(id = sensorNivelAgua.imagen),
            contentDescription = "Imagen del medidor de gas",
            modifier = Modifier
                .size(128.dp)
                .padding(bottom = 16.dp)
                .align(alignment = Alignment.CenterHorizontally)

        )
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
        sensorNuevo= SensorNivelAgua(nombre, "Sensor de Nivel de Agua", ubicacion, sensorNivelAgua.imagen, litros)
        Button(
            onClick = {},
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Actualizar")
        }
    }
}

@Composable
fun ConfiguracionSensorPresion(sensorPresion: SensorPresion) {
    var nombre by remember { mutableStateOf(sensorPresion.nombre) }
    var ubicacion by remember { mutableStateOf(sensorPresion.ubicacion) }
    var presion by remember { mutableStateOf(sensorPresion.presion) }
    var sensorNuevo by remember { mutableStateOf(sensorPresion) }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    )  {
        Image(
            painter = painterResource(id = sensorPresion.imagen),
            contentDescription = "Imagen del medidor de gas",
            modifier = Modifier
                .size(128.dp)
                .padding(bottom = 16.dp)
                .align(alignment = Alignment.CenterHorizontally)

        )
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
        sensorNuevo= SensorPresion(nombre, "Sensor de Presion", ubicacion, sensorPresion.imagen, presion)
        Button(
            onClick = {},
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Actualizar")
        }
    }
}

@Composable
fun ConfiguracionSensorApertura(sensorApertura: SensorApertura) {
    var nombre by remember { mutableStateOf(sensorApertura.nombre) }
    var ubicacion by remember { mutableStateOf(sensorApertura.ubicacion) }
    var estado by remember { mutableStateOf(sensorApertura.estado) }
    var sensorNuevo by remember { mutableStateOf(sensorApertura) }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    )  {
        Image(
            painter = painterResource(id = sensorApertura.imagen),
            contentDescription = "Imagen del medidor de gas",
            modifier = Modifier
                .size(128.dp)
                .padding(bottom = 16.dp)
                .align(alignment = Alignment.CenterHorizontally)

        )
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
        sensorNuevo= SensorApertura(nombre, "Sensor de Vibracion", ubicacion, sensorApertura.imagen, estado)
        Button(
            onClick = {},
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Actualizar")
        }
    }
}

@Composable
fun ConfiguracionSensorCalidadAire(sensorCalidadAire: SensorCalidadAire) {
    var nombre by remember { mutableStateOf(sensorCalidadAire.nombre) }
    var ubicacion by remember { mutableStateOf(sensorCalidadAire.ubicacion) }
    var calidad by remember { mutableStateOf(sensorCalidadAire.ICA) }
    var sensorNuevo by remember { mutableStateOf(sensorCalidadAire) }


    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = sensorCalidadAire.imagen),
            contentDescription = "Imagen del sensor de calidad del aire",
            modifier = Modifier
                .size(128.dp)
                .padding(bottom = 16.dp)
                .align(alignment = Alignment.CenterHorizontally)
        )

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

        sensorNuevo= SensorCalidadAire(nombre, "Sensor de Calidad del Aire", ubicacion, sensorCalidadAire.imagen, calidad)
        Button(
            onClick = {},
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Actualizar")
        }
    }
}

@Composable
fun ConfiguracionSensorGas(sensorGas: SensorGas) {
    var nombre by remember { mutableStateOf(sensorGas.nombre) }
    var ubicacion by remember { mutableStateOf(sensorGas.ubicacion) }
    var co2 by remember { mutableStateOf(sensorGas.concentracionCO2) }
    var ch4 by remember { mutableStateOf(sensorGas.concentracionCH4) }
    var sensorNuevo by remember { mutableStateOf(sensorGas) }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    )  {
        Image(
            painter = painterResource(id = sensorGas.imagen),
            contentDescription = "Imagen del medidor de gas",
            modifier = Modifier
                .size(128.dp)
                .padding(bottom = 16.dp)
                .align(alignment = Alignment.CenterHorizontally)

        )
        Text(text = "Configuración del Sensor de Gas")
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") }
        )
        OutlinedTextField(
            value = ubicacion,
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { ubicacion = it },
            label = { Text("Ubicacion") }
        )
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            OutlinedTextField(
                value = co2.toString(),
                onValueChange = { co2 = it.toDouble() },
                label = { Text("Concentracion de CO2") }
            )
            OutlinedTextField(
                value = ch4.toString(),
                onValueChange = { ch4 = it.toDouble() },
                label = { Text("Concentracion de CH4") }
            )
        }

        sensorNuevo= SensorGas(nombre, "Sensor de Gas", ubicacion, sensorGas.imagen, co2, ch4)
        Button(
            onClick = {},
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Actualizar")
        }
    }
}



//ACTUADORES:

@Composable
fun ConfiguracionActuadorValvula(actuadorValvula: ActuadorValvula) {
    var nombre by remember { mutableStateOf(actuadorValvula.nombre) }
    var ubicacion by remember { mutableStateOf(actuadorValvula.ubicacion) }
    var estado by remember { mutableStateOf(actuadorValvula.activo) }
    var actuadorNuevo by remember { mutableStateOf(actuadorValvula) }
        Column (
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = actuadorValvula.imagen),
                contentDescription = "Imagen del medidor de gas",
                modifier = Modifier
                    .size(128.dp)
                    .padding(bottom = 16.dp)
                    .align(alignment = Alignment.CenterHorizontally)

            )
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
            actuadorNuevo= ActuadorValvula(nombre, "Actuador de Válvula", ubicacion, actuadorValvula.imagen, estado)

            Button(
                onClick = {},
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Actualizar")
            }
        }
}

@Composable
fun ConfiguracionCerraduraElectronica(cerradura: CerraduraElectronica) {
    var nombre by remember { mutableStateOf(cerradura.nombre) }
    var ubicacion by remember { mutableStateOf(cerradura.ubicacion) }
    var estado by remember { mutableStateOf(cerradura.cerrado)}
    var actuadorNuevo by remember { mutableStateOf(cerradura) }
    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = cerradura.imagen),
            contentDescription = "Imagen del medidor de gas",
            modifier = Modifier
                .size(128.dp)
                .padding(bottom = 16.dp)
                .align(alignment = Alignment.CenterHorizontally)

        )
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
        actuadorNuevo= CerraduraElectronica(nombre, "Cerradura Electronica", ubicacion, cerradura.imagen, estado)
        Button(
            onClick = {},
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Actualizar")
        }
    }
}

@Composable
fun ConfiguracionControladorIluminacion(ControladorIluminacion: ControladorIluminacion) {
    var nombre by remember { mutableStateOf(ControladorIluminacion.nombre) }
    var ubicacion by remember { mutableStateOf(ControladorIluminacion.ubicacion) }
    var estado by remember { mutableStateOf(ControladorIluminacion.encendido)}
    var actuadorNuevo by remember { mutableStateOf(ControladorIluminacion) }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    )  {
        Image(
            painter = painterResource(id = ControladorIluminacion.imagen),
            contentDescription = "Imagen del medidor de gas",
            modifier = Modifier
                .size(128.dp)
                .padding(bottom = 16.dp)
                .align(alignment = Alignment.CenterHorizontally)

        )
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
        actuadorNuevo= ControladorIluminacion(nombre, "Controlador de Iluminación", ubicacion, ControladorIluminacion.imagen, estado)
        Button(
            onClick = {},
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Actualizar")
        }
    }
}

//MOnitoreo:

@Composable
fun ConfiguracionControladorClima(controlador: ControladorClima) {
    var nombre by remember { mutableStateOf(controlador.nombre) }
    var ubicacion by remember { mutableStateOf(controlador.ubicacion) }
    var grados by remember { mutableStateOf(controlador.temperatura) }
    var humedad by remember { mutableStateOf(controlador.humedad) }
    var monitorNuevo by remember { mutableStateOf(controlador) }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    )  {
        Image(
            painter = painterResource(id = controlador.imagen),
            contentDescription = "Imagen del controlador de clima",
            modifier = Modifier
                .size(128.dp)
                .padding(bottom = 16.dp)
                .align(alignment = Alignment.CenterHorizontally)

        )
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
        monitorNuevo= ControladorClima(nombre, "Controlador de Clima", ubicacion, controlador.imagen, grados, humedad)
        Button(
            onClick = {},
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Actualizar")
        }
    }
}

@Composable
fun ConfiguracionMedidorConsumoAgua(medidorCAgua: MedidorConsumoAgua) {
    var nombre by remember { mutableStateOf(medidorCAgua.nombre) }
    var ubicacion by remember { mutableStateOf(medidorCAgua.ubicacion) }
    var litros by remember { mutableStateOf(medidorCAgua.litros) }
    var monitorNuevo by remember { mutableStateOf(medidorCAgua) }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    )  {
        Image(
            painter = painterResource(id = medidorCAgua.imagen),
            contentDescription = "Imagen del medidor de gas",
            modifier = Modifier
                .size(128.dp)
                .padding(bottom = 16.dp)
                .align(alignment = Alignment.CenterHorizontally)

        )
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
        monitorNuevo= MedidorConsumoAgua(nombre, "Sensor de Nivel de Agua", ubicacion, medidorCAgua.imagen, litros)

        Button(
            onClick = {},
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Actualizar")
        }
    }
}

@Composable
fun ConfiguracionMedidorGas(medidorGas: MedidorGas) {
    var nombre by remember { mutableStateOf(medidorGas.nombre) }
    var ubicacion by remember { mutableStateOf(medidorGas.ubicacion) }
    var m3State by remember { mutableStateOf(medidorGas.metroscubicos) }
    var m3toString by remember { mutableStateOf(m3State.toString()) } //Xq el OutlinedTextField necesita un string para q funcione


    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    )  {
        Image(
            painter = painterResource(id = medidorGas.imagen),
            contentDescription = "Imagen del medidor de gas",
            modifier = Modifier
                .size(128.dp)
                .padding(bottom = 16.dp)
                .align(alignment = Alignment.CenterHorizontally)

        )
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

        Text(text = "Consumo actual: $m3State m³")
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = m3toString,
            onValueChange = { input ->
                // Validar que sea un número o si está vacío
                if (input.toFloatOrNull() != null || input.isEmpty()) {
                    m3toString = input
                }
            },
            label = { Text("Nuevo consumo (m³)") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val nuevoValor = m3toString.toFloatOrNull()
                if (nuevoValor != null) {
                    m3State = nuevoValor.toDouble()
                    medidorGas.metroscubicos = nuevoValor.toDouble()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Actualizar consumo")
        }
    }
}


