package com.proyectomoviles.dispositivos

data class ActuadorValvula(
    var id: String ?= null,
    val userId: String?,
    val nombre: String?,
    val token: String?,
    val tipo: String?,
    val ubicacion: String?,
    val imagen: Int?,
    var activo: Boolean?
): Dispositivo()

data class CerraduraElectronica(
    var id: String ?= null,
    val userId: String?,
    val nombre: String?,
    val token: String?,
    val tipo: String?,
    val ubicacion: String?,
    val imagen: Int?,
    var cerrado: Boolean?
): Dispositivo()
data class ControladorIluminacion(
    var id: String ?= null,
    val userId: String?,
    val nombre: String?,
    val token: String?,
    val tipo: String?,
    val ubicacion: String?,
    val imagen: Int?,
    var encendido: Boolean?,
): Dispositivo()

