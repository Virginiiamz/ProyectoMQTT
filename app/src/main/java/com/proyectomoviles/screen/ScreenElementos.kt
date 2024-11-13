package com.proyectomoviles.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.proyectomoviles.dispositivos.Dispositivo
import kotlinx.serialization.Serializable

@Serializable
object Elementos

@Composable
fun ElementosScreen() {

    Scaffold(
        floatingActionButton = { },
    ) { paddingValue ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValue),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            if (Dispositivo.dispositivosCreados.isEmpty()) {
                item {
                    Text("No hay dispositivos vinculados")
                }
            } else {
                items(Dispositivo.dispositivosCreados) { dispositivo ->
                    DispositivoButton(dispositivo.nombre)
                }
            }
        }
    }
}

@Composable
fun DispositivoButton(nombre: String) {
    Button(
        onClick = {  },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        contentPadding = PaddingValues(vertical = 8.dp)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
/*            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_sample_icon), // Cambia 'ic_sample_icon' por el ID de tu icono
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )*/
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = nombre)
        }
    }
}
