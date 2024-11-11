package com.proyectomoviles.dispositivos

class SensorTemperatura(
    nombre: String,
    tipo: String,
    ubicacion: String,
    imagen: String,
    val grados: Double,
    val humedad: Double,
): Dispositivo(nombre, tipo, ubicacion, imagen) {

}

class SensorMovimiento(
    nombre: String,
    tipo: String,
    ubicacion: String,
    imagen: String,
    val estado: Boolean
): Dispositivo(nombre, tipo, ubicacion, imagen) {

}
