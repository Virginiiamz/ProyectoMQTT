package com.proyectomoviles.dispositivos

class Sensores(
    val temperatura: Temperatura,

) {
    data class Temperatura(
        val temperatura: Double,
        val humedad: Double,
    )
}