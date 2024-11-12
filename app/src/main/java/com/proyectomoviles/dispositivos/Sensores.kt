package com.proyectomoviles.dispositivos

class SensorTemperatura(
    nombre: String,
    tipo: String,
    ubicacion: String,
    val grados: Double,
    val humedad: Double,
): Dispositivo(nombre, tipo, ubicacion) {

}

class SensorMovimiento(
    nombre: String,
    tipo: String,
    ubicacion: String,
    val estado: Boolean
): Dispositivo(nombre, tipo, ubicacion) {

}

class SensorVibracion(
    nombre: String,
    tipo: String,
    ubicacion: String,
    val estado: Boolean
): Dispositivo(nombre, tipo, ubicacion) {

}

class SensorNivelAgua(
    nombre: String,
    tipo: String,
    ubicacion: String,
    val litros: Double
): Dispositivo(nombre, tipo, ubicacion) {

}