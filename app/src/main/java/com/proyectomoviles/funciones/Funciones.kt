package com.proyectomoviles.funciones

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun mostrarImagenByDispositivo(dispositivo: Any) {
    Image(
        painterResource(id = dispositivo.imagen),
        contentDescription = dispositivo.nombre,
        modifier = Modifier.padding(top = 6.dp, bottom = 6.dp)

    )
}