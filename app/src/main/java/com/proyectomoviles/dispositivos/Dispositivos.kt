package com.proyectomoviles.dispositivos


open class Dispositivo(
    val nombre : String,
    val tipo: String,
    val ubicacion: String,
) {
    companion object {
        val dispositivosCreados = mutableListOf<Dispositivo>()
    }

    init {
       dispositivosCreados.add(this)
    }
}