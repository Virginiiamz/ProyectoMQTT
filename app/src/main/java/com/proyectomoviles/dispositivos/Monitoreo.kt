package com.proyectomoviles.dispositivos

class ControladorClima(
    nombre: String,
    tipo: String,
    ubicacion: String,
    imagen: Int,
    val temperatura: Double,
    val humedad: Double,
): Dispositivo(nombre, tipo, ubicacion, imagen){
}

class MedidorConsumoAgua(
    nombre: String,
    tipo: String,
    ubicacion: String,
    imagen: Int,
    val litros: Double
): Dispositivo(nombre, tipo, ubicacion, imagen){

}

class MedidorGas(
    nombre: String,
    tipo: String,
    ubicacion: String,
    imagen: Int,
    var metroscubicos: Double,
): Dispositivo(nombre, tipo, ubicacion, imagen) {
}
