package com.proyectomoviles.dispositivos

class SensorLuz(
    nombre: String,
    tipo: String,
    ubicacion: String,
    imagen: Int,
    val estadoEncendido: Boolean
): Dispositivo(nombre, tipo, ubicacion, imagen){

}

class SensorTemperatura(
    nombre: String,
    tipo: String,
    ubicacion: String,
    imagen: Int,
    val grados: Double,
    val humedad: Double,
): Dispositivo(nombre, tipo, ubicacion, imagen) {

}

class SensorMovimiento(
    nombre: String,
    tipo: String,
    ubicacion: String,
    imagen: Int,
    val estado: Boolean
): Dispositivo(nombre, tipo, ubicacion, imagen){

}

class SensorVibracion(
    nombre: String,
    tipo: String,
    ubicacion: String,
    imagen: Int,
    val estado: Boolean
): Dispositivo(nombre, tipo, ubicacion, imagen){

}

class SensorNivelAgua(
    nombre: String,
    tipo: String,
    ubicacion: String,
    imagen: Int,
    val litros: Double
): Dispositivo(nombre, tipo, ubicacion, imagen){

}
class SensorPresion(
    nombre: String,
    tipo: String,
    ubicacion: String,
    imagen: Int,
    val presion: Double
): Dispositivo(nombre, tipo, ubicacion, imagen){
    init {
        dispositivosCreados.add(this)
    }
}

class SensorApertura(
    nombre: String,
    tipo: String,
    ubicacion: String,
    imagen: Int,
    val estado: Boolean
): Dispositivo(nombre, tipo, ubicacion, imagen){
    init {
        dispositivosCreados.add(this)
    }
}

class SensorCalidadAire(
    nombre: String,
    tipo: String,
    ubicacion: String,
    imagen: Int,
    val ICA: String
): Dispositivo(nombre, tipo, ubicacion, imagen){
    init {
        dispositivosCreados.add(this)
    }
}




