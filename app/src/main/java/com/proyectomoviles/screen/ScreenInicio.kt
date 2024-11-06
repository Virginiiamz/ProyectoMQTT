package com.proyectomoviles.screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.proyectomoviles.dispositivos.Temperatura

@Composable
fun InicioScreen() {
    val temperatura = Temperatura(25.03, 75.05)
    Text(temperatura.toString())
}