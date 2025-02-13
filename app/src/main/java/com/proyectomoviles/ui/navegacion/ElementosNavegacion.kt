package com.proyectomoviles.ui.navegacion

import com.proyectomoviles.dispositivos.Dispositivo
import kotlinx.serialization.Serializable

@Serializable
object Login

@Serializable
object SignUp

@Serializable
object ForgotPassword

@Serializable
object Inicio

@Serializable
object Elementos

@Serializable
data class ConfigurarDispositivos(val tipo: String)