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
    val listaDispositivo = RepositoryList.listaDispositivos as List<Dispositivo>
    val factory = InicioViewModelFactory(firestoreManager)
    val inicioViewModel = viewModel(InicioViewModel::class.java, factory = factory)
    val uiState by inicioViewModel.uiState.collectAsState()

    var contadorSensor = 0
    var contadorActuador = 0
    var contadorMonitoreo = 0
    var listaSensores: List<Dispositivo> = mutableListOf()
    var listaActuadores: List<Dispositivo> = mutableListOf()
    var listaMonitoreo: List<Dispositivo> = mutableListOf()

    var valor1 by rememberSaveable { mutableStateOf("") }
    var valor2 by rememberSaveable { mutableStateOf("") }

    LazyColumn(
        modifier = Modifier.padding(top = 40.dp)
    ) {
        item {
            Text(uiState.sensorTemperatura.toString())
        }

        items(uiState.sensorTemperatura) { dispositivo ->
            Text(dispositivo.toString())
            when (dispositivo) {
                is SensorTemperatura -> {
                    val valor1 = dispositivo.grados.toString()
                    val valor2 = dispositivo.humedad.toString()

                    dispositivo.nombre?.let {
                        dispositivo.imagen?.let { it1 ->
                            dispositivo.ubicacion?.let { it2 ->
                                mostrarInformacionDispositivos(
                                    "sensortemperatura",
                                    it,
                                    it1, it2, valor1, valor2
                                )
                            }
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
        item {
            Text(uiState.sensorLuz.toString())
        }

        items(uiState.sensorLuz) { dispositivo ->
            Text(dispositivo.toString())
            when (dispositivo) {
                is SensorLuz -> {
                    valor1 = dispositivo.estadoEncendido.toString()
                    valor2 = null.toString()

                    dispositivo.nombre?.let {
                        dispositivo.imagen?.let { it1 ->
                            dispositivo.ubicacion?.let { it2 ->
                                mostrarInformacionDispositivos(
                                    "sensorluz",
                                    it,
                                    it1, it2, valor1, valor2
                                )
                            }
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }

    when (TipoDispositivoCreado.tipoDispositivoCreado) {
        "sensortemperatura" -> {
//            mqttService.subscribe("") {
//                valor1 = it
//            }
//
//            mqttService.subscribe("") {
//                valor2 = it
//            }
        }

        "sensorluz" -> {
//            mqttService.subscribe("") {
//                valor1 = it
//            }
//            valor2 = null.toString()
        }

        "sensormovimiento" -> {
            mqttService.subscribe("") {
                valor1 = it
            }
            valor2 = null.toString()
        }

        "sensorvibracion" -> {
            mqttService.subscribe("") {
                valor1 = it
            }
            valor2 = null.toString()
        }

        "sensornivelagua" -> {
            mqttService.subscribe("") {
                valor1 = it
            }
            valor2 = null.toString()
        }

        "sensorpresion" -> {
            mqttService.subscribe("") {
                valor1 = it
            }
            valor2 = null.toString()
        }

        "sensorapertura" -> {
            mqttService.subscribe("sensorapertura") {
                valor1 = it
            }
            valor2 = null.toString()
        }

        "sensorcalidadaire" -> {
            mqttService.subscribe("") {
                valor1 = it
            }
            valor2 = null.toString()
        }

        "actuadorvalvula" -> {
            mqttService.subscribe("actuadorvalvula") {
                valor1 = it
            }
            valor2 = null.toString()
        }

        "cerraduraelectronica" -> {
            mqttService.subscribe("cerraduraelectronica") {
                valor1 = it
            }
            valor2 = null.toString()
        }

        "controladoriluminacion" -> {
            mqttService.subscribe("controladoriluminacion") {
                valor1 = it
            }
            valor2 = null.toString()
        }

        "controladorclima" -> {
            mqttService.subscribe("") {
                valor1 = it
            }
            mqttService.subscribe("") {
                valor2 = it
            }
        }

        "consumoagua" -> {
            mqttService.subscribe("") {
                valor1 = it
            }
            valor2 = null.toString()
        }

        "medidorgas" -> {
            mqttService.subscribe("") {
                valor1 = it
            }
            valor2 = null.toString()
        }
    }


    listaDispositivo.forEach { dispositivo ->
//        if (dispositivo.tipo == "Sensor") {
//            contadorSensor++
//            listaSensores += dispositivo
//        }
//        if (dispositivo.tipo == "Actuador") {
//            contadorActuador++
//            listaActuadores += dispositivo
//        }
//        if (dispositivo.tipo == "Monitoreo") {
//            contadorMonitoreo++
//            listaMonitoreo += dispositivo
//        }
    }


    Scaffold(
        floatingActionButton = {
            MyFloatingActionButton(navigateToElementos)
        },
        floatingActionButtonPosition = FabPosition.Start
    ) { paddingValue ->
        if (listaDispositivo.isEmpty()) {
//            Card(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(100.dp)
//                    .padding(16.dp),
//                elevation = CardDefaults.elevatedCardElevation(12.dp),
//                shape = RoundedCornerShape(8.dp),
//                colors = CardDefaults.cardColors(
//                    containerColor = Color.LightGray,
//                    contentColor = Color.Black
//                ),
//            ) {
//                Row(
//                    modifier = Modifier.fillMaxSize(),
//                    horizontalArrangement = Arrangement.Center,
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    Text("No hay dispositivos vinculados.", textAlign = TextAlign.Center)
//                }
//
//            }



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

                        CargarSensores(dispositivo, navigateToInicio, valor1, valor2)
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
                        CargarActuadores(dispositivo, navigateToInicio)
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
                        CargarMonitoreo(dispositivo, navigateToInicio)
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
fun CargarSensores(
    dispositivo: Any,
    navigateToInicio: () -> Unit,
    valor1: String,
    valor2: String
) {
    val showDialog = remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .width(400.dp)
            .border(
                border = BorderStroke(2.dp, Color.LightGray),
                shape = RoundedCornerShape(8.dp),
            )
    ) {
        // Icono de cruz gris
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(8.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Close, // Usa un ícono adecuado, como "Close"
                contentDescription = "Cerrar",
                tint = Color.Gray,
                modifier = Modifier
                    .size(24.dp)
                    .clickable {
                        showDialog.value = true
                    }
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(top = 8.dp)
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
                    modifier = Modifier
                        .weight(1f)
                        .height(125.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
//                    mostrarInformacionDispositivos(dispositivo)
                }
            }

            /*Column {
                when (dispositivo) {
                    is SensorTemperatura -> mostrarSensorTemperatura(dispositivo, valor1, valor2)
                    is SensorMovimiento -> mostrarSensorMovimiento(dispositivo)
                    is SensorVibracion -> mostrarSensorVibracion(dispositivo)
                    is SensorNivelAgua -> mostrarSensorNivelAgua(dispositivo)
                    is SensorLuz -> mostrarSensorLuz(dispositivo, valor1)
                    is SensorPresion -> mostrarSensorPresion(dispositivo)
                    is SensorApertura -> mostrarSensorApertura(dispositivo)
                    is SensorCalidadAire -> mostrarSensorCalidadAire(dispositivo)
                }
            }*/
        }
    }
    if (showDialog.value) {
//        AlertDialog(
//            onDismissRequest = { showDialog.value = false },
//            title = { Text("Eliminar dispositivo") },
//            text = { Text("¿Estás seguro de que deseas eliminar el dispositivo '${dispositivo}'?") },
//            confirmButton = {
//                Text(
//                    "Confirmar",
//                    modifier = Modifier.clickable {
//                        RepositoryList.removeDispositivos(dispositivo)
//                        showDialog.value = false
//                        navigateToInicio()
//                    },
//                    color = MaterialTheme.colorScheme.primary
//                )
//            },
//            dismissButton = {
//                Text(
//                    "Cancelar",
//                    modifier = Modifier.clickable { showDialog.value = false },
//                    color = MaterialTheme.colorScheme.secondary
//                )
//            }
//        )
    }

}


@Composable
fun CargarActuadores(dispositivo: Dispositivo, navigateToInicio: () -> Unit) {
    val showDialog = remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .width(400.dp)
            .border(
                border = BorderStroke(2.dp, Color.LightGray),
                shape = RoundedCornerShape(8.dp),
            )
    ) {
        // Icono de cruz gris
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(8.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Close, // Usa un ícono adecuado, como "Close"
                contentDescription = "Cerrar",
                tint = Color.Gray,
                modifier = Modifier
                    .size(24.dp)
                    .clickable {
                        showDialog.value = true
                    }
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(top = 8.dp)
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
                    modifier = Modifier
                        .weight(1f)
                        .height(125.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
//                    mostrarInformacionDispositivos(dispositivo)
                }
            }

//            Column {
//                when (dispositivo) {
//                    is ActuadorValvula -> mostrarActuadorValvula(dispositivo)
//                    is CerraduraElectronica -> mostrarActuadorCerradura(dispositivo)
//                    is ControladorIluminacion -> mostrarControladorIluminacion(dispositivo)
//                }
//            }
        }
    }
    if (showDialog.value) {
        AlertDialog(
            onDismissRequest = { showDialog.value = false },
            title = { Text("Eliminar dispositivo") },
            text = { Text("¿Estás seguro de que deseas eliminar el dispositivo '${dispositivo}'?") },
            confirmButton = {
                Text(
                    "Confirmar",
                    modifier = Modifier.clickable {
                        RepositoryList.removeDispositivos(dispositivo)
                        showDialog.value = false
                        navigateToInicio()
                    },
                    color = MaterialTheme.colorScheme.primary
                )
            },
            dismissButton = {
                Text(
                    "Cancelar",
                    modifier = Modifier.clickable { showDialog.value = false },
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        )
    }
}


@Composable
fun CargarMonitoreo(dispositivo: Dispositivo, navigateToInicio: () -> Unit) {
    val showDialog = remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .width(400.dp)
            .border(
                border = BorderStroke(2.dp, Color.LightGray),
                shape = RoundedCornerShape(8.dp),
            )
    ) {
        // Icono de cruz gris
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(8.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Close, // Usa un ícono adecuado, como "Close"
                contentDescription = "Cerrar",
                tint = Color.Gray,
                modifier = Modifier
                    .size(24.dp)
                    .clickable {
                        showDialog.value = true
                    }
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(top = 8.dp)
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
                    modifier = Modifier
                        .weight(1f)
                        .height(125.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
//                    mostrarInformacionDispositivos(dispositivo)
                }
            }

//            Column {
//                when (dispositivo) {
//                    is ControladorClima -> mostrarControladorClima(dispositivo)
//                    is MedidorConsumoAgua -> mostrarConsumoAgua(dispositivo)
//                    is MedidorGas -> mostrarConsumoGas(dispositivo)
//                }
//            }
        }
    }
    if (showDialog.value) {
        AlertDialog(
            onDismissRequest = { showDialog.value = false },
            title = { Text("Eliminar dispositivo") },
            text = { Text("¿Estás seguro de que deseas eliminar el dispositivo '${dispositivo}'?") },
            confirmButton = {
                Text(
                    "Confirmar",
                    modifier = Modifier.clickable {
                        RepositoryList.removeDispositivos(dispositivo)
                        showDialog.value = false
                        navigateToInicio()
                    },
                    color = MaterialTheme.colorScheme.primary
                )
            },
            dismissButton = {
                Text(
                    "Cancelar",
                    modifier = Modifier.clickable { showDialog.value = false },
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        )
    }
}



