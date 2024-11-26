package com.proyectomoviles.dispositivos

class ActuadorValvula(
    nombre: String,
    tipo: String,
    ubicacion: String,
    imagen: Int,
    var activo: Boolean
): Dispositivo(nombre, tipo, ubicacion, imagen){
}

class CerraduraElectronica(
    nombre: String,
    tipo: String,
    ubicacion: String,
    imagen: Int,
    var cerrado: Boolean
): Dispositivo(nombre, tipo, ubicacion, imagen){
}
class ControladorIluminacion(
    nombre: String,
    tipo: String,
    ubicacion: String,
    imagen: Int,
    var encendido: Boolean,
): Dispositivo(nombre, tipo, ubicacion,imagen) {
}

