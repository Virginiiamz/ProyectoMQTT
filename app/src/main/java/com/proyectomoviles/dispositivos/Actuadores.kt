package com.proyectomoviles.dispositivos

data class ActuadorValvula(
    var id: String ?= null,
    val userId: String?,
    val nombre: String?,
    val tipo: String?,
    val ubicacion: String?,
    val imagen: Int?,
    var activo: Boolean?
)

data class CerraduraElectronica(
    var id: String ?= null,
    val userId: String?,
    val nombre: String?,
    val tipo: String?,
    val ubicacion: String?,
    val imagen: Int?,
    var cerrado: Boolean?
)
data class ControladorIluminacion(
    var id: String ?= null,
    val userId: String?,
    val nombre: String?,
    val tipo: String?,
    val ubicacion: String?,
    val imagen: Int?,
    var encendido: Boolean?,
)

