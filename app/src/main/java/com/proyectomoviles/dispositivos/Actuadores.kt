package com.proyectomoviles.dispositivos

class ActuadorValvula(
    nombre: String,
    tipo: String,
    ubicacion: String,
    var activo: Boolean
): Dispositivo(nombre, tipo, ubicacion) {
    init {
        dispositivosCreados.add(this)
    }
}


class CerraduraElectronica(
    nombre: String,
    tipo: String,
    ubicacion: String,
    var cerrado: Boolean
): Dispositivo(nombre, tipo, ubicacion){
    init {
        dispositivosCreados.add(this)
    }
}