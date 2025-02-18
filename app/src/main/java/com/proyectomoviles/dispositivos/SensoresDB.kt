package com.proyectomoviles.dispositivos

data class SensorLuzDB(
    var idDispositivo: String = "",
    val nombre: String = "",
    val tipo: String = "",
    val ubicacion: String = "",
    val imagen: Int = 0,
    var estadoEncendido: Boolean = false
)// Añadir cuando adri haga DispositivosDB

data class SensorTemperaturaDB(
    var idDispositivo: String = "",
    val nombre: String = "",
    val tipo: String = "",
    val ubicacion: String = "",
    val imagen: Int = 0,
    val grados: Double = 0.00,
    val humedad: Double = 0.00,
)

data class SensorMovimientoDB(
    var idDispositivo: String = "",
    val nombre: String = "",
    val tipo: String = "",
    val ubicacion: String = "",
    val imagen: Int = 0,
    var estado: Boolean = false
)

data class SensorVibracionDB(
    var idDispositivo: String = "",
    val nombre: String = "",
    val tipo: String = "",
    val ubicacion: String = "",
    val imagen: Int = 0,
    var estado: Boolean = false
)

data class SensorNivelAguaDB(
    var idDispositivo: String = "",
    val nombre: String = "",
    val tipo: String = "",
    val ubicacion: String = "",
    val imagen: Int = 0,
    val litros: Double = 0.00
)
data class SensorPresionDB(
    var idDispositivo: String = "",
    val nombre: String = "",
    val tipo: String = "",
    val ubicacion: String = "",
    val imagen: Int = 0,
    val presion: Double = 0.00
)

data class SensorAperturaDB(
    var idDispositivo: String = "",
    val nombre: String = "",
    val tipo: String = "",
    val ubicacion: String = "",
    val imagen: Int = 0,
    var estado: Boolean = false
)

data class SensorCalidadAireDB(
    var idDispositivo: String = "",
    val nombre: String = "",
    val tipo: String = "",
    val ubicacion: String = "",
    val imagen: Int = 0,
    val ICA: String = ""
)

