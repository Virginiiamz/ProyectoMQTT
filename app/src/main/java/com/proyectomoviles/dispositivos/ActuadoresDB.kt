package com.proyectomoviles.dispositivos

data class ActuadorValvulaDB(
    var userId: String = "",
    val nombre: String = "",
    val token: String = "",
    val tipo: String = "",
    val ubicacion: String = "",
    val imagen: Int = 0,
    var activo: Boolean = false
)

data class CerraduraElectronicaDB(
    var userId: String = "",
    val nombre: String = "",
    val token: String = "",
    val tipo: String = "",
    val ubicacion: String = "",
    val imagen: Int = 0,
    var cerrado: Boolean = false
)

data class ControladorIluminacionDB(
    var userId: String = "",
    val nombre: String = "",
    val token: String = "",
    val tipo: String = "",
    val ubicacion: String = "",
    val imagen: Int = 0,
    var encendido: Boolean = false
)

