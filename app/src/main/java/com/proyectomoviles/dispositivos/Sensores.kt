package com.proyectomoviles.dispositivos

//Es una prueba, se utilizara mas tarde si eso
class Sensores(
    val temperatura: Temperatura ?= null,
    val movimiento: Movimiento? = null,
) {
    data class Temperatura(
        val nombre: String,
        val grados: Double,
        val humedad: Double,
    )

    data class Movimiento(
        val nombre: String,
        val hayMovimiento: Boolean
    )
}