package com.proyectomoviles.dispositivos

data class SensorLuz(
    var id: String ?= null,
    var userId: String?,
    val nombre: String?,
    val tipo: String?,
    val ubicacion: String?,
    val imagen: Int?,
    var estadoEncendido: Boolean?
)

 data class SensorTemperatura(
     var id: String ?= null,
     var userId: String?,
     val nombre: String?,
     val tipo: String?,
     val ubicacion: String?,
     val imagen: Int?,
    val grados: Double?,
    val humedad: Double?,
)

data class SensorMovimiento(
    var id: String ?= null,
    var userId: String?,
    val nombre: String?,
    val tipo: String?,
    val ubicacion: String?,
    val imagen: Int?,
    var estado: Boolean?
)

data class SensorVibracion(
    var id: String ?= null,
    var userId: String?,
    val nombre: String?,
    val tipo: String?,
    val ubicacion: String?,
    val imagen: Int?,
    var estado: Boolean?
)

data class SensorNivelAgua(
    var id: String ?= null,
    var userId: String?,
    val nombre: String?,
    val tipo: String?,
    val ubicacion: String?,
    val imagen: Int?,
    val litros: Double?
)
data class SensorPresion(
    var id: String ?= null,
    var userId: String?,
    val nombre: String?,
    val tipo: String?,
    val ubicacion: String?,
    val imagen: Int?,
    val presion: Double?
)

data class SensorApertura(
    var id: String ?= null,
    var userId: String?,
    val nombre: String?,
    val tipo: String?,
    val ubicacion: String?,
    val imagen: Int?,
    var estado: Boolean?
)

data class SensorCalidadAire(
    var id: String ?= null,
    var userId: String?,
    val nombre: String?,
    val tipo: String?,
    val ubicacion: String?,
    val imagen: Int?,
    val ICA: String?
)

