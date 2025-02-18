package com.proyectomoviles.data

import com.proyectomoviles.dispositivos.Dispositivo

object RepositoryList {

    var listaDispositivos: MutableList<Dispositivo> = mutableListOf()

    fun addDispositivos(dispositivo: Dispositivo?) {
        if (dispositivo != null) {
            listaDispositivos.add(dispositivo)
        }
    }

    fun removeDispositivos(dispositivo: Dispositivo) {
        listaDispositivos.remove(dispositivo)
    }

}