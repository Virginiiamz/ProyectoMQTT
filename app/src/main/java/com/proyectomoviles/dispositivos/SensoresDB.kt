package com.proyectomoviles.dispositivos

data class SensorLuzDB(
    var userId: String = "",
    val nombre: String = "",
    val token: String = "",
    val tipo: String = "",
    val ubicacion: String = "",
    val imagen: Int = 0,
    var estadoEncendido: Boolean = false
)// Añadir cuando adri haga DispositivosDB

data class SensorTemperaturaDB(
    var userId: String = "",
    val nombre: String = "",
    val token: String = "",
    val tipo: String = "",
    val ubicacion: String = "",
    val imagen: Int = 0,
    val grados: Double = 0.00,
    val humedad: Double = 0.00,
)

data class SensorMovimientoDB(
    var userId: String = "",
    val nombre: String = "",
    val token: String = "",
    val tipo: String = "",
    val ubicacion: String = "",
    val imagen: Int = 0,
    var estado: Boolean = false
)

data class SensorVibracionDB(
    var userId: String = "",
    val nombre: String = "",
    val token: String = "",
    val tipo: String = "",
    val ubicacion: String = "",
    val imagen: Int = 0,
    var estado: Boolean = false
)

data class SensorNivelAguaDB(
    var userId: String = "",
    val nombre: String = "",
    val token: String = "",
    val tipo: String = "",
    val ubicacion: String = "",
    val imagen: Int = 0,
    val litros: Double = 0.00
)
data class SensorPresionDB(
    var userId: String = "",
    val nombre: String = "",
    val token: String = "",
    val tipo: String = "",
    val ubicacion: String = "",
    val imagen: Int = 0,
    val presion: Double = 0.00
)

data class SensorAperturaDB(
    var userId: String = "",
    val nombre: String = "",
    val token: String = "",
    val tipo: String = "",
    val ubicacion: String = "",
    val imagen: Int = 0,
    var estado: Boolean = false
)

data class SensorCalidadAireDB(
    var userId: String = "",
    val nombre: String = "",
    val token: String = "",
    val tipo: String = "",
    val ubicacion: String = "",
    val imagen: Int = 0,
    val ICA: String = ""
)

