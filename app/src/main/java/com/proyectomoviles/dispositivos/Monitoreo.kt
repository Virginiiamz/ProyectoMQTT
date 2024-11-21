package com.proyectomoviles.dispositivos

class ControladorClima(
    nombre: String,
    tipo: String,
    ubicacion: String,
    val temperatura: Double,
    val humedad: Double,
): Dispositivo(nombre, tipo, ubicacion) {
    init {
        dispositivosCreados.add(this)
    }
}