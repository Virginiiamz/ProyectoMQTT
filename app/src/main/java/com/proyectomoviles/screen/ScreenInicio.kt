package com.proyectomoviles.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.proyectomoviles.dispositivos.Temperatura


@Composable
fun InicioScreen() {
    val temperatura = Temperatura("Cocina",20.5, 88.9, "Sensor de temperatura", "Sensor1")


    Scaffold(
        floatingActionButton = {
            MyFloatingActionButton()
        },
        floatingActionButtonPosition = FabPosition.Start
    ) {
        paddingValue ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValue)
        ) {

//            if (dispositivos.listaDispositivos.isEmpty()) {
//                item {
//                    Text("No hay dispositivos vinculados")
//                }
//            } else {
//                items(dispositivos.listaDispositivos) { item ->
//                    Text(item.toString())
//                }
//            }

        }
        Text(temperatura.toString())
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