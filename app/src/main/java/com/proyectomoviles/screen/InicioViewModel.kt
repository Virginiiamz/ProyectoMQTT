package com.proyectomoviles.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.proyectomoviles.data.FirestoreManager
import com.proyectomoviles.dispositivos.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class InicioViewModel(val firestoreManager: FirestoreManager) : ViewModel() {

    val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState

    init {
        viewModelScope.launch {
            _uiState.update { it.copy() }
            firestoreManager.getSensorTemperatura().collect { sensorTemperatura ->
                firestoreManager.getSensorLuz().collect { sensorLuz ->
                    firestoreManager.getSensorMovimiento().collect { sensorMovimiento ->
                        firestoreManager.getSensorVibracion().collect { sensorVibracion ->
                            firestoreManager.getSensorNivelAgua().collect { sensorNivelAgua ->
                                firestoreManager.getSensorPresion().collect { sensorPresion ->
                                    firestoreManager.getSensorApertura().collect { sensorApertura ->
                                        firestoreManager.getSensorCalidadAire()
                                            .collect { sensorCalidadAire ->
                                                firestoreManager.getActuadorValvula().collect { actuadorValvula ->
                                                    firestoreManager.getCerraduraElectronica().collect { cerraduraElectronica ->
                                                        firestoreManager.getControladorIluminacion().collect { controladorIluminacion ->
                                                            firestoreManager.getControladorClima().collect { controladorClima ->
                                                                firestoreManager.getMedidorConsumoAgua().collect { medidorConsumoAgua ->
                                                                     firestoreManager.getMedidorGas().collect { medidorGas ->
                                                                        _uiState.update { uiState ->
                                                                            uiState.copy(
                                                                                sensorTemperatura = sensorTemperatura,
                                                                                sensorLuz = sensorLuz,
                                                                                sensorMovimiento = sensorMovimiento,
                                                                                sensorVibracion = sensorVibracion,
                                                                                sensorNivelAgua = sensorNivelAgua,
                                                                                sensorPresion = sensorPresion,
                                                                                sensorApertura = sensorApertura,
                                                                                sensorCalidadAire = sensorCalidadAire,
                                                                                actuadorValvula = actuadorValvula,
                                                                                cerraduraElectronica = cerraduraElectronica,
                                                                                controladorIluminacion = controladorIluminacion,
                                                                                controladorClima = controladorClima,
                                                                                medidorConsumoAgua = medidorConsumoAgua,
                                                                                medidorGas = medidorGas,
                                                                                )
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                    }
                                }
                            }
                        }
                    }
                }

            }
        }
    }

    fun addSensorTemperatura(sensorTemperatura: SensorTemperatura) {
        viewModelScope.launch {
            firestoreManager.addSensorTemperatura(sensorTemperatura)
        }
    }

    fun addSensorLuz(sensorLuz: SensorLuz) {
        viewModelScope.launch {
            firestoreManager.addSensorLuz(sensorLuz)
        }
    }

    fun addSensorMovimiento(sensorMovimiento: SensorMovimiento) {
        viewModelScope.launch {
            firestoreManager.addSensorMovimiento(sensorMovimiento)
        }
    }

    fun addSensorVibracion(sensorVibracion: SensorVibracion) {
        viewModelScope.launch {
            firestoreManager.addSensorVibracion(sensorVibracion)
        }
    }

    fun addSensorNivelAgua(sensorNivelAgua: SensorNivelAgua) {
        viewModelScope.launch {
            firestoreManager.addSensorNivelAgua(sensorNivelAgua)
        }
    }

    fun addSensorPresion(sensorPresion: SensorPresion) {
        viewModelScope.launch {
            firestoreManager.addSensorPresion(sensorPresion)
        }
    }

    fun addSensorApertura(sensorApertura: SensorApertura) {
        viewModelScope.launch {
            firestoreManager.addSensorApertura(sensorApertura)
        }
    }

    fun addSensorCalidadAire(sensorCalidadAire: SensorCalidadAire) {
        viewModelScope.launch {
            firestoreManager.addSensorCalidadAire(sensorCalidadAire)
        }
    }


    fun addActuadorValvula(actuadorValvula: ActuadorValvula) {
        viewModelScope.launch {
            firestoreManager.addActuadorValvula(actuadorValvula)
        }
    }

    fun addActuadorCerraduraElectronica(actuadorCerraduraElectronica: CerraduraElectronica) {
        viewModelScope.launch {
            firestoreManager.addCerraduraElectronica(actuadorCerraduraElectronica)
        }
    }
    fun addActuadorControladorIluminacion(actuadorControladorIluminacion: ControladorIluminacion) {
        viewModelScope.launch {
            firestoreManager.addControladorIluminacion(actuadorControladorIluminacion)
        }
    }

    fun addControladorClima(controladorClima: ControladorClima) {
        viewModelScope.launch {
            firestoreManager.addControladorClima(controladorClima)
        }
    }

    fun addMedidorConsumoAgua(medidorConsumoAgua: MedidorConsumoAgua) {
        viewModelScope.launch {
            firestoreManager.addMedidorConsumoAgua(medidorConsumoAgua)
        }
    }

    fun addMedidorGas(medidorGas: MedidorGas) {
        viewModelScope.launch {
            firestoreManager.addMedidorGas(medidorGas)
        }
    }

    fun deleteDispositivoById(dispositivoId: String, tipoDispositivo: String) {
        viewModelScope.launch {
            when (tipoDispositivo) {
                "sensorluz" -> firestoreManager.deleteSensorLuzById(dispositivoId)
                "sensortemperatura" -> firestoreManager.deleteSensorTemperaturaById(dispositivoId)
                "sensormovimiento" -> firestoreManager.deleteSensorMovimientoById(dispositivoId)
                "sensorvibracion" -> firestoreManager.deleteSensorVibracionById(dispositivoId)
                "SensorNivelAgua" -> firestoreManager.deleteSensorNivelAguaById(dispositivoId)
                "SensorPresion" -> firestoreManager.deleteSensorPresionById(dispositivoId)
                "SensorApertura" -> firestoreManager.deleteSensorAperturaById(dispositivoId)
                "SensorCalidadAire" -> firestoreManager.deleteSensorCalidadAireById(dispositivoId)
                "ActuadorValvula" -> firestoreManager.deleteActuadorValvulaById(dispositivoId)
                "CerraduraElectronica" -> firestoreManager.deleteCerraduraElectronicaById(
                    dispositivoId
                )

                "ControladorIluminacion" -> firestoreManager.deleteControladorIluminacionById(
                    dispositivoId
                )

                "ControladorClima" -> firestoreManager.deleteControladorClimaById(dispositivoId)
                "MedidorConsumoAgua" -> firestoreManager.deleteMedidorConsumoAguaById(dispositivoId)
                "MedidorGas" -> firestoreManager.deleteMedidorGasById(dispositivoId)
            }
        }
    }
}

data class UiState(
    val sensorTemperatura: List<SensorTemperatura> = emptyList(),
    val sensorLuz: List<SensorLuz> = emptyList(),
    val sensorMovimiento: List<SensorMovimiento> = emptyList(),
    val sensorVibracion: List<SensorVibracion> = emptyList(),
    val sensorNivelAgua: List<SensorNivelAgua> = emptyList(),
    val sensorPresion: List<SensorPresion> = emptyList(),
    val sensorApertura: List<SensorApertura> = emptyList(),
    val sensorCalidadAire: List<SensorCalidadAire> = emptyList(),
    val actuadorValvula: List<ActuadorValvula> = emptyList(),
    val cerraduraElectronica: List<CerraduraElectronica> = emptyList(),
    val controladorIluminacion: List<ControladorIluminacion> = emptyList(),
    val controladorClima: List<ControladorClima> = emptyList(),
    val medidorConsumoAgua: List<MedidorConsumoAgua> = emptyList(),
    val medidorGas: List<MedidorGas> = emptyList()
)

class InicioViewModelFactory(private val firestoreManager: FirestoreManager) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return InicioViewModel(firestoreManager) as T
    }
}