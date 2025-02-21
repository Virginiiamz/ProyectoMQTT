package com.proyectomoviles.dispositivos

data class SensorLuz(
    var id: String ?= null,
    var userId: String?,
    val nombre: String?,
    val tipo: String?,
    val ubicacion: String?,
    val imagen: Int?,
    var estadoEncendido: Boolean?
): Dispositivo()

 data class SensorTemperatura(
     var id: String ?= null,
     var userId: String?,
     val nombre: String,
     val tipo: String?,
     val ubicacion: String?,
     val imagen: Int?,
    val grados: Double?,
    val humedad: Double?,
): Dispositivo()

data class SensorMovimiento(
    var id: String ?= null,
    var userId: String?,
    val nombre: String?,
    val tipo: String?,
    val ubicacion: String?,
    val imagen: Int?,
    var estado: Boolean?
): Dispositivo()

data class SensorVibracion(
    var id: String ?= null,
    var userId: String?,
    val nombre: String?,
    val tipo: String?,
    val ubicacion: String?,
    val imagen: Int?,
    var estado: Boolean?
): Dispositivo()

data class SensorNivelAgua(
    var id: String ?= null,
    var userId: String?,
    val nombre: String?,
    val tipo: String?,
    val ubicacion: String?,
    val imagen: Int?,
    val litros: Double?
): Dispositivo()

data class SensorPresion(
    var id: String ?= null,
    var userId: String?,
    val nombre: String?,
    val tipo: String?,
    val ubicacion: String?,
    val imagen: Int?,
    val presion: Double?
): Dispositivo()

data class SensorApertura(
    var id: String ?= null,
    var userId: String?,
    val nombre: String?,
    val tipo: String?,
    val ubicacion: String?,
    val imagen: Int?,
    var estado: Boolean?
): Dispositivo()

data class SensorCalidadAire(
    var id: String ?= null,
    var userId: String?,
    val nombre: String?,
    val tipo: String?,
    val ubicacion: String?,
    val imagen: Int?,
    val ICA: String?
): Dispositivo()

