package com.proyectomoviles.dispositivos

data class ControladorClimaDB(
    var userId: String = "",
    val nombre: String = "",
    val token: String = "",
    val tipo: String = "",
    val ubicacion: String = "",
    val imagen: Int = 0,
    val temperatura: Double = 0.0,
    val humedad: Double = 0.0,
)

data class MedidorConsumoAguaDB(
    var userId: String = "",
    val nombre: String = "",
    val token: String = "",
    val tipo: String = "",
    val ubicacion: String = "",
    val imagen: Int = 0,
    val litros: Double = 0.0,
)

data class MedidorGasDB(
    var userId: String = "",
    val nombre: String = "",
    val token: String = "",
    val tipo: String = "",
    val ubicacion: String = "",
    val imagen: Int = 0,
    var metroscubicos: Double = 0.0,
)