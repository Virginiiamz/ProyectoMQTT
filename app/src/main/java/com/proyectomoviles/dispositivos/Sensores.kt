package com.proyectomoviles.dispositivos

class SensorLuz(
    nombre: String,
    tipo: String,
    ubicacion: String,
    val estadoEncendido: Boolean
): Dispositivo(nombre, tipo, ubicacion){

}

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
class SensorPresion(
    nombre: String,
    tipo: String,
    ubicacion: String,
    val presion: Double
): Dispositivo(nombre, tipo, ubicacion) {
    init {
        dispositivosCreados.add(this)
    }
}

class ActuadorValvula(
    nombre: String,
    tipo: String,
    ubicacion: String,
    val activo: Boolean
): Dispositivo(nombre, tipo, ubicacion) {
    init {
        dispositivosCreados.add(this)
    }
}

class SensorApertura(
    nombre: String,
    tipo: String,
    ubicacion: String,
    val estado: Boolean
): Dispositivo(nombre, tipo, ubicacion){
    init {
        dispositivosCreados.add(this)
    }
}

class SensorCalidadAire(
    nombre: String,
    tipo: String,
    ubicacion: String,
    val nivel: Double
): Dispositivo(nombre, tipo, ubicacion) {
    init {
        dispositivosCreados.add(this)
    }
}

class ControladorClima(
    nombre: String,
    tipo: String,
    ubicacion: String,
    val temperatura: Double,
    val humedad: Double,
): Dispositivo(nombre, tipo, ubicacion) {
    init {
        dispositivosCreados.add(this)
    }
}


