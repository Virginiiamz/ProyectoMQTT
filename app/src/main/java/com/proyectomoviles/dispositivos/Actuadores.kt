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