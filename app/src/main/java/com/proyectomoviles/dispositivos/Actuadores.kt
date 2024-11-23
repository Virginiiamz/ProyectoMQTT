package com.proyectomoviles.dispositivos

class ActuadorValvula(
    nombre: String,
    tipo: String,
    ubicacion: String,
    imagen: Int,
    var activo: Boolean
): Dispositivo(nombre, tipo, ubicacion, imagen){
    init {
        dispositivosCreados.add(this)
    }
}

class CerraduraElectronica(
    nombre: String,
    tipo: String,
    ubicacion: String,
    imagen: Int,
    var cerrado: Boolean
): Dispositivo(nombre, tipo, ubicacion, imagen){
    init {
        dispositivosCreados.add(this)
    }
}
class ControladorIluminacion(
    nombre: String,
    tipo: String,
    ubicacion: String,
    imagen: Int,
    var encendido: Boolean,
): Dispositivo(nombre, tipo, ubicacion,imagen) {
    init {
        dispositivosCreados.add(this)
    }
}

