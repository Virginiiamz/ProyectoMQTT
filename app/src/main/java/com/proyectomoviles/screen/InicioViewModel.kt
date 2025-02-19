package com.proyectomoviles.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.proyectomoviles.data.FirestoreManager
import com.proyectomoviles.dispositivos.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.lang.Thread.State

class InicioViewModel(val firestoreManager: FirestoreManager): ViewModel() {

    val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState

//    private val _dispositivos = MutableStateFlow<List<Dispositivo>>(emptyList())
//    val dispositivos: StateFlow<List<Dispositivo>> = _dispositivos

    val dispositivo = listOf(
        firestoreManager.getSensorLuz(),
        firestoreManager.getSensorTemperatura(),
        firestoreManager.getSensorMovimiento(),
        firestoreManager.getSensorVibracion(),
        firestoreManager.getSensorNivelAgua(),
        firestoreManager.getSensorPresion(),
        firestoreManager.getSensorApertura(),
        firestoreManager.getSensorCalidadAire(),
        firestoreManager.getActuadorValvula(),
        firestoreManager.getCerraduraElectronica(),
        firestoreManager.getControladorIluminacion(),
        firestoreManager.getControladorClima(),
        firestoreManager.getMedidorConsumoAgua(),
        firestoreManager.getMedidorGas()
    )

    init {
//        fetchDispositivos()
        viewModelScope.launch {
            _uiState.update { it.copy() }
            dispositivo.map { dispositivo ->
                dispositivo.collect { tipoDispositivo ->
                    _uiState.update { uiState ->
                        uiState.copy(
                            dispositivos = tipoDispositivo
                        )
                    }
                }
            }
        }

    }

    fun addDispositivo(dispositivo: Dispositivo) {
        viewModelScope.launch {
            when (dispositivo) {
                is SensorLuz -> firestoreManager.addSensorLuz(dispositivo)
                is SensorTemperatura -> firestoreManager.addSensorTemperatura(dispositivo)
                is SensorMovimiento -> firestoreManager.addSensorMovimiento(dispositivo)
                is SensorVibracion -> firestoreManager.addSensorVibracion(dispositivo)
                is SensorNivelAgua -> firestoreManager.addSensorNivelAgua(dispositivo)
                is SensorPresion -> firestoreManager.addSensorPresion(dispositivo)
                is SensorApertura -> firestoreManager.addSensorApertura(dispositivo)
                is SensorCalidadAire -> firestoreManager.addSensorCalidadAire(dispositivo)
                is ActuadorValvula -> firestoreManager.addActuadorValvula(dispositivo)
                is CerraduraElectronica -> firestoreManager.addCerraduraElectronica(dispositivo)
                is ControladorIluminacion -> firestoreManager.addControladorIluminacion(dispositivo)
                is ControladorClima -> firestoreManager.addControladorClima(dispositivo)
                is MedidorConsumoAgua -> firestoreManager.addMedidorConsumoAgua(dispositivo)
                is MedidorGas -> firestoreManager.addMedidorGas(dispositivo)
            }
        }
    }

    fun updateDispositivo(dispositivo: Dispositivo) {
        viewModelScope.launch {
            when (dispositivo) {
                is SensorLuz -> firestoreManager.updateSensorLuz(dispositivo)
                is SensorTemperatura -> firestoreManager.updateSensorTemperatura(dispositivo)
                is SensorMovimiento -> firestoreManager.updateSensorMovimiento(dispositivo)
                is SensorVibracion -> firestoreManager.updateSensorVibracion(dispositivo)
                is SensorNivelAgua -> firestoreManager.updateSensorNivelAgua(dispositivo)
                is SensorPresion -> firestoreManager.updateSensorPresion(dispositivo)
                is SensorApertura -> firestoreManager.updateSensorApertura(dispositivo)
                is SensorCalidadAire -> firestoreManager.updateSensorCalidadAire(dispositivo)
                is ActuadorValvula -> firestoreManager.updateActuadorValvula(dispositivo)
                is CerraduraElectronica -> firestoreManager.updateCerraduraElectronica(dispositivo)
                is ControladorIluminacion -> firestoreManager.updateControladorIluminacion(dispositivo)
                is ControladorClima -> firestoreManager.updateControladorClima(dispositivo)
                is MedidorConsumoAgua -> firestoreManager.updateMedidorConsumoAgua(dispositivo)
                is MedidorGas -> firestoreManager.updateMedidorGas(dispositivo)
            }
        }
    }

    fun deleteDispositivoById(dispositivoId: String, tipoDispositivo: String) {
        viewModelScope.launch {
            when (tipoDispositivo) {
                "SensorLuz" -> firestoreManager.deleteSensorLuzById(dispositivoId)
                "SensorTemperatura" -> firestoreManager.deleteSensorTemperaturaById(dispositivoId)
                "SensorMovimiento" -> firestoreManager.deleteSensorMovimientoById(dispositivoId)
                "SensorVibracion" -> firestoreManager.deleteSensorVibracionById(dispositivoId)
                "SensorNivelAgua" -> firestoreManager.deleteSensorNivelAguaById(dispositivoId)
                "SensorPresion" -> firestoreManager.deleteSensorPresionById(dispositivoId)
                "SensorApertura" -> firestoreManager.deleteSensorAperturaById(dispositivoId)
                "SensorCalidadAire" -> firestoreManager.deleteSensorCalidadAireById(dispositivoId)
                "ActuadorValvula" -> firestoreManager.deleteActuadorValvulaById(dispositivoId)
                "CerraduraElectronica" -> firestoreManager.deleteCerraduraElectronicaById(dispositivoId)
                "ControladorIluminacion" -> firestoreManager.deleteControladorIluminacionById(dispositivoId)
                "ControladorClima" -> firestoreManager.deleteControladorClimaById(dispositivoId)
                "MedidorConsumoAgua" -> firestoreManager.deleteMedidorConsumoAguaById(dispositivoId)
                "MedidorGas" -> firestoreManager.deleteMedidorGasById(dispositivoId)
            }
        }
    }
}

data class UiState(
    val dispositivos: List<Dispositivo> = emptyList()
)

class InicioViewModelFactory(private val firestoreManager: FirestoreManager) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return InicioViewModel(firestoreManager) as T
    }
}