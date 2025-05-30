package com.proyectomoviles.dispositivos

data class ControladorClima(
    var id: String? = null,
    var userId: String?,
    val nombre: String?,
    val tokenGrados: String?,
    val tokenHumedad: String?,
    val tipo: String?,
    val ubicacion: String?,
    val imagen: Int?,
    val temperatura: Double?,
    val humedad: Double?,
): Dispositivo()

data class MedidorConsumoAgua(
    var id: String? = null,
    var userId: String?,
    val nombre: String?,
    val token: String?,
    val tipo: String?,
    val ubicacion: String?,
    val imagen: Int?,
    val litros: Double?
): Dispositivo()

data class MedidorGas(
    var id: String? = null,
    var userId: String?,
    val nombre: String?,
    val token: String?,
    val tipo: String?,
    val ubicacion: String?,
    val imagen: Int?,
    var metroscubicos: Double?,
): Dispositivo()
