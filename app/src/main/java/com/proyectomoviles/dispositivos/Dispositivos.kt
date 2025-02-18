package com.proyectomoviles.dispositivos

import kotlinx.serialization.Serializable

@Serializable
open class Dispositivo(
    var id: String ?= null,
    val nombre : String,
    val tipo: String,
    val ubicacion: String,
    var imagen: Int
) {
}