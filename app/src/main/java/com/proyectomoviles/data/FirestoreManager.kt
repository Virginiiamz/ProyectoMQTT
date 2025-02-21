package com.proyectomoviles.data

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.snapshots
import com.proyectomoviles.dispositivos.ActuadorValvula
import com.proyectomoviles.dispositivos.ActuadorValvulaDB
import com.proyectomoviles.dispositivos.CerraduraElectronica
import com.proyectomoviles.dispositivos.CerraduraElectronicaDB
import com.proyectomoviles.dispositivos.ControladorClima
import com.proyectomoviles.dispositivos.ControladorClimaDB
import com.proyectomoviles.dispositivos.ControladorIluminacion
import com.proyectomoviles.dispositivos.ControladorIluminacionDB
import com.proyectomoviles.dispositivos.Dispositivo
import com.proyectomoviles.dispositivos.MedidorConsumoAgua
import com.proyectomoviles.dispositivos.MedidorConsumoAguaDB
import com.proyectomoviles.dispositivos.MedidorGas
import com.proyectomoviles.dispositivos.MedidorGasDB
import com.proyectomoviles.dispositivos.SensorApertura
import com.proyectomoviles.dispositivos.SensorAperturaDB
import com.proyectomoviles.dispositivos.SensorCalidadAire
import com.proyectomoviles.dispositivos.SensorCalidadAireDB
import com.proyectomoviles.dispositivos.SensorLuz
import com.proyectomoviles.dispositivos.SensorLuzDB
import com.proyectomoviles.dispositivos.SensorMovimiento
import com.proyectomoviles.dispositivos.SensorMovimientoDB
import com.proyectomoviles.dispositivos.SensorNivelAgua
import com.proyectomoviles.dispositivos.SensorNivelAguaDB
import com.proyectomoviles.dispositivos.SensorPresion
import com.proyectomoviles.dispositivos.SensorPresionDB
import com.proyectomoviles.dispositivos.SensorTemperatura
import com.proyectomoviles.dispositivos.SensorTemperaturaDB
import com.proyectomoviles.dispositivos.SensorVibracion
import com.proyectomoviles.dispositivos.SensorVibracionDB
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await


class FirestoreManager(auth: AuthManager, context: android.content.Context) {
    private val firestore = FirebaseFirestore.getInstance()
    private val userId = auth.getCurrentUser()?.uid

    companion object{
        const val COLLECTION_SENSORES = "sensores"
        const val COLLECTION_ACTUADORES = "actuadores"
        const val COLLECTION_MONITOREO = "monitoreo"
        const val COLLECTION_SENSORTEMP = "sensores_temperatura"
        const val COLLECTION_SENSORLUZ = "sensores_luz"
        const val COLLECTION_SENSORMOVIMIENTO = "sensores_movimiento"
        const val COLLECTION_SENSORVIBRACION = "sensores_vibracion"
        const val COLLECTION_SENSORNIVELAGUA = "sensores_nivel_agua"
        const val COLLECTION_SENSORPRESION = "sensores_presion"
        const val COLLECTION_SENSORAPERTURA = "sensores_apertura"
        const val COLLECTION_SENSORCALIDADAIRE = "sensores_calidad_aire"
        const val COLLECTION_ACTUADORVALVULA = "actuadores_valvula"
        const val COLLECTION_ACTUADORCERRADURAELECTRONICA = "actuadores_cerradura_electronica"

    }

    // SENSORES
    fun getSensorLuz(): Flow<List<SensorLuz>> {
        return firestore.collection(COLLECTION_SENSORLUZ)
            .whereEqualTo("userId", userId)
            .snapshots()
            .map { qs ->
                qs.documents.mapNotNull { ds ->
                    ds.toObject(SensorLuzDB::class.java)?.let { sensorLuz ->
                        SensorLuz(
                            id = ds.id,
                            userId = sensorLuz.userId,
                            nombre = sensorLuz.nombre,
                            tipo = sensorLuz.tipo,
                            ubicacion = sensorLuz.ubicacion,
                            imagen = sensorLuz.imagen,
                            estadoEncendido = sensorLuz.estadoEncendido,
                        )
                    }
                }
            }
    }

    suspend fun addSensorLuz(sensorLuz: SensorLuz) {
        val db = FirebaseFirestore.getInstance()
        val sensoresRef = db.collection(COLLECTION_SENSORLUZ)

        val documentReference = sensoresRef.document()
        val sensorId = documentReference.id

        val sensorConId = sensorLuz.copy(id = sensorId)

        documentReference.set(sensorConId).await()
    }

    suspend fun deleteSensorLuzById(sensorLuzId: String) {
        firestore.collection(COLLECTION_SENSORLUZ).document(sensorLuzId).delete().await()
    }

    fun getSensorTemperatura(): Flow<List<SensorTemperatura>> {
        return firestore.collection(COLLECTION_SENSORTEMP)
            .whereEqualTo("userId", userId)
            .snapshots()
            .map { qs ->
                qs.documents.mapNotNull { ds ->
                    ds.toObject(SensorTemperaturaDB::class.java)?.let { sensorTemperatura ->
                        SensorTemperatura(
                            id = ds.id,
                            userId = sensorTemperatura.userId,
                            nombre = sensorTemperatura.nombre,
                            tipo = sensorTemperatura.tipo,
                            ubicacion = sensorTemperatura.ubicacion,
                            imagen = sensorTemperatura.imagen,
                            grados = sensorTemperatura.grados,
                            humedad = sensorTemperatura.humedad,
                        )
                    }
                }
            }
    }

    suspend fun addSensorTemperatura(sensorTemperatura: SensorTemperatura) {
        val db = FirebaseFirestore.getInstance()
        val sensoresRef = db.collection(COLLECTION_SENSORTEMP)

        val documentReference = sensoresRef.add(sensorTemperatura).await()
        val sensorId = documentReference.id

        documentReference.update("id", sensorId).await()
    }

    suspend fun deleteSensorTemperaturaById(sensorTemperaturaId: String) {
        firestore.collection(COLLECTION_SENSORTEMP).document(sensorTemperaturaId).delete().await()
    }

    fun getSensorMovimiento(): Flow<List<SensorMovimiento>> {
        return firestore.collection(COLLECTION_SENSORMOVIMIENTO)
            .whereEqualTo("userId", userId)
            .snapshots()
            .map { qs ->
                qs.documents.mapNotNull { ds ->
                    ds.toObject(SensorMovimientoDB::class.java)?.let { sensorMovimiento ->
                        SensorMovimiento(
                            id = ds.id,
                            userId = sensorMovimiento.userId,
                            nombre = sensorMovimiento.nombre,
                            tipo = sensorMovimiento.tipo,
                            ubicacion = sensorMovimiento.ubicacion,
                            imagen = sensorMovimiento.imagen,
                            estado = sensorMovimiento.estado,
                        )
                    }
                }
            }
    }

    suspend fun addSensorMovimiento(sensorMovimiento: SensorMovimiento) {
        val db = FirebaseFirestore.getInstance()
        val sensoresRef = db.collection(COLLECTION_SENSORMOVIMIENTO)

        val documentReference = sensoresRef.document()
        val sensorId = documentReference.id

        val sensorConId = sensorMovimiento.copy(id = sensorId)

        documentReference.set(sensorConId).await()
    }

    suspend fun deleteSensorMovimientoById(sensorMovimientoId: String) {
        firestore.collection(COLLECTION_SENSORMOVIMIENTO).document(sensorMovimientoId).delete().await()
    }

    fun getSensorVibracion(): Flow<List<SensorVibracion>> {
        return firestore.collection(COLLECTION_SENSORVIBRACION)
            .whereEqualTo("userId", userId)
            .snapshots()
            .map { qs ->
                qs.documents.mapNotNull { ds ->
                    ds.toObject(SensorVibracionDB::class.java)?.let { sensorVibracion ->
                        SensorVibracion(
                            id = ds.id,
                            userId = sensorVibracion.userId,
                            nombre = sensorVibracion.nombre,
                            tipo = sensorVibracion.tipo,
                            ubicacion = sensorVibracion.ubicacion,
                            imagen = sensorVibracion.imagen,
                            estado = sensorVibracion.estado,
                        )
                    }
                }
            }
    }

    suspend fun addSensorVibracion(sensorVibracion: SensorVibracion) {
        val db = FirebaseFirestore.getInstance()
        val sensoresRef = db.collection(COLLECTION_SENSORVIBRACION)

        val documentReference = sensoresRef.document()
        val sensorId = documentReference.id

        val sensorConId = sensorVibracion.copy(id = sensorId)

        documentReference.set(sensorConId).await()
    }

    suspend fun deleteSensorVibracionById(sensorVibracionId: String) {
        firestore.collection(COLLECTION_SENSORVIBRACION).document(sensorVibracionId).delete().await()
    }

    fun getSensorNivelAgua(): Flow<List<SensorNivelAgua>> {
        return firestore.collection(COLLECTION_SENSORNIVELAGUA)
            .whereEqualTo("userId", userId)
            .snapshots()
            .map { qs ->
                qs.documents.mapNotNull { ds ->
                    ds.toObject(SensorNivelAguaDB::class.java)?.let { sensorNivelAgua ->
                        SensorNivelAgua(
                            id = ds.id,
                            userId = sensorNivelAgua.userId,
                            nombre = sensorNivelAgua.nombre,
                            tipo = sensorNivelAgua.tipo,
                            ubicacion = sensorNivelAgua.ubicacion,
                            imagen = sensorNivelAgua.imagen,
                            litros = sensorNivelAgua.litros,
                        )
                    }
                }
            }
    }

    suspend fun addSensorNivelAgua(sensorNivelAgua: SensorNivelAgua) {
        val db = FirebaseFirestore.getInstance()
        val sensoresRef = db.collection(COLLECTION_SENSORNIVELAGUA)

        val documentReference = sensoresRef.document()
        val sensorId = documentReference.id

        val sensorConId = sensorNivelAgua.copy(id = sensorId)

        documentReference.set(sensorConId).await()
    }

    suspend fun deleteSensorNivelAguaById(sensorNivelAguaId: String) {
        firestore.collection(COLLECTION_SENSORNIVELAGUA).document(sensorNivelAguaId).delete().await()
    }

    fun getSensorPresion(): Flow<List<SensorPresion>> {
        return firestore.collection(COLLECTION_SENSORPRESION)
            .whereEqualTo("userId", userId)
            .snapshots()
            .map { qs ->
                qs.documents.mapNotNull { ds ->
                    ds.toObject(SensorPresionDB::class.java)?.let { sensorPresion ->
                        SensorPresion(
                            id = ds.id,
                            userId = sensorPresion.userId,
                            nombre = sensorPresion.nombre,
                            tipo = sensorPresion.tipo,
                            ubicacion = sensorPresion.ubicacion,
                            imagen = sensorPresion.imagen,
                            presion = sensorPresion.presion,
                        )
                    }
                }
            }
    }

    suspend fun addSensorPresion(sensorPresion: SensorPresion) {
        val db = FirebaseFirestore.getInstance()
        val sensoresRef = db.collection(COLLECTION_SENSORPRESION)

        val documentReference = sensoresRef.document()
        val sensorId = documentReference.id

        val sensorConId = sensorPresion.copy(id = sensorId)

        documentReference.set(sensorConId).await()
    }

    suspend fun deleteSensorPresionById(sensorPresionId: String) {
        firestore.collection(COLLECTION_SENSORPRESION).document(sensorPresionId).delete().await()
    }

    fun getSensorApertura(): Flow<List<SensorApertura>> {
        return firestore.collection(COLLECTION_SENSORAPERTURA)
            .whereEqualTo("userId", userId)
            .snapshots()
            .map { qs ->
                qs.documents.mapNotNull { ds ->
                    ds.toObject(SensorAperturaDB::class.java)?.let { sensorApertura ->
                        SensorApertura(
                            id = ds.id,
                            userId = sensorApertura.userId,
                            nombre = sensorApertura.nombre,
                            tipo = sensorApertura.tipo,
                            ubicacion = sensorApertura.ubicacion,
                            imagen = sensorApertura.imagen,
                            estado = sensorApertura.estado,
                        )
                    }
                }
            }
    }

    suspend fun addSensorApertura(sensorApertura: SensorApertura) {
        val db = FirebaseFirestore.getInstance()
        val sensoresRef = db.collection(COLLECTION_SENSORAPERTURA)

        val documentReference = sensoresRef.document()
        val sensorId = documentReference.id

        val sensorConId = sensorApertura.copy(id = sensorId)

        documentReference.set(sensorConId).await()
    }

    suspend fun deleteSensorAperturaById(sensorAperturaId: String) {
        firestore.collection(COLLECTION_SENSORAPERTURA).document(sensorAperturaId).delete().await()
    }

    fun getSensorCalidadAire(): Flow<List<SensorCalidadAire>> {
        return firestore.collection(COLLECTION_SENSORCALIDADAIRE)
            .whereEqualTo("userId", userId)
            .snapshots()
            .map { qs ->
                qs.documents.mapNotNull { ds ->
                    ds.toObject(SensorCalidadAireDB::class.java)?.let { sensorCalidadAire ->
                        SensorCalidadAire(
                            id = ds.id,
                            userId = sensorCalidadAire.userId,
                            nombre = sensorCalidadAire.nombre,
                            tipo = sensorCalidadAire.tipo,
                            ubicacion = sensorCalidadAire.ubicacion,
                            imagen = sensorCalidadAire.imagen,
                            ICA = sensorCalidadAire.ICA,
                        )
                    }
                }
            }
    }

    suspend fun addSensorCalidadAire(sensorCalidadAire: SensorCalidadAire) {
        val db = FirebaseFirestore.getInstance()
        val sensoresRef = db.collection(COLLECTION_SENSORCALIDADAIRE)

        val documentReference = sensoresRef.document()
        val sensorId = documentReference.id

        val sensorConId = sensorCalidadAire.copy(id = sensorId)

        documentReference.set(sensorConId).await()    }



    suspend fun deleteSensorCalidadAireById(sensorCalidadAireId: String) {
        firestore.collection(COLLECTION_SENSORCALIDADAIRE).document(sensorCalidadAireId).delete().await()
    }


    // ACTUADORES
    fun getActuadorValvula(): Flow<List<ActuadorValvula>> {
        return firestore.collection(COLLECTION_ACTUADORVALVULA)
            .whereEqualTo("userId", userId)
            .snapshots()
            .map { qs ->
                qs.documents.mapNotNull { ds ->
                    ds.toObject(ActuadorValvulaDB::class.java)?.let { actuadorValvula ->
                        ActuadorValvula(
                            id = ds.id,
                            userId = actuadorValvula.userId,
                            nombre = actuadorValvula.nombre,
                            tipo = actuadorValvula.tipo,
                            ubicacion = actuadorValvula.ubicacion,
                            imagen = actuadorValvula.imagen,
                            activo = actuadorValvula.activo,
                        )
                    }
                }
            }
    }

    suspend fun addActuadorValvula(actuadorValvula: ActuadorValvula) {
        val db = FirebaseFirestore.getInstance()
        val sensoresRef = db.collection(COLLECTION_ACTUADORVALVULA)

        val documentReference = sensoresRef.document()
        val sensorId = documentReference.id

        val sensorConId = actuadorValvula.copy(id = sensorId)

        documentReference.set(sensorConId).await()
    }



    suspend fun deleteActuadorValvulaById(actuadorValvulaId: String) {
        firestore.collection(COLLECTION_ACTUADORVALVULA).document(actuadorValvulaId).delete().await()
    }

    fun getCerraduraElectronica(): Flow<List<CerraduraElectronica>> {
        return firestore.collection(COLLECTION_ACTUADORCERRADURAELECTRONICA)
            .whereEqualTo("userId", userId)
            .snapshots()
            .map { qs ->
                qs.documents.mapNotNull { ds ->
                    ds.toObject(CerraduraElectronicaDB::class.java)?.let { cerraduraElectronica ->
                        CerraduraElectronica(
                            id = ds.id,
                            userId = cerraduraElectronica.userId,
                            nombre = cerraduraElectronica.nombre,
                            tipo = cerraduraElectronica.tipo,
                            ubicacion = cerraduraElectronica.ubicacion,
                            imagen = cerraduraElectronica.imagen,
                            cerrado = cerraduraElectronica.cerrado,
                        )
                    }
                }
            }
    }

    suspend fun addCerraduraElectronica(cerraduraElectronica: CerraduraElectronica) {
        val db = FirebaseFirestore.getInstance()
        val sensoresRef = db.collection(COLLECTION_ACTUADORCERRADURAELECTRONICA)

        val documentReference = sensoresRef.document()
        val sensorId = documentReference.id

        val sensorConId = cerraduraElectronica.copy(id = sensorId)

        documentReference.set(sensorConId).await()
    }



    suspend fun deleteCerraduraElectronicaById(cerraduraElectronicaId: String) {
        firestore.collection(COLLECTION_ACTUADORCERRADURAELECTRONICA).document(cerraduraElectronicaId).delete().await()
    }

    fun getControladorIluminacion(): Flow<List<ControladorIluminacion>> {
        return firestore.collection(COLLECTION_ACTUADORES)
            .whereEqualTo("userId", userId)
            .snapshots()
            .map { qs ->
                qs.documents.mapNotNull { ds ->
                    ds.toObject(ControladorIluminacionDB::class.java)?.let { controladorIluminacion ->
                        ControladorIluminacion(
                            id = ds.id,
                            userId = controladorIluminacion.userId,
                            nombre = controladorIluminacion.nombre,
                            tipo = controladorIluminacion.tipo,
                            ubicacion = controladorIluminacion.ubicacion,
                            imagen = controladorIluminacion.imagen,
                            encendido = controladorIluminacion.encendido,
                        )
                    }
                }
            }
    }

    suspend fun addControladorIluminacion(controladorIluminacion: ControladorIluminacion) {
        firestore.collection(COLLECTION_ACTUADORES).add(controladorIluminacion).await()
    }

    suspend fun updateControladorIluminacion(controladorIluminacion: ControladorIluminacion) {
        val controladorIluminacionRef = controladorIluminacion.id?.let {
            firestore.collection(COLLECTION_ACTUADORES).document(it)
        }
        controladorIluminacionRef?.set(controladorIluminacion)?.await()
    }

    suspend fun deleteControladorIluminacionById(controladorIluminacionId: String) {
        firestore.collection(COLLECTION_ACTUADORES).document(controladorIluminacionId).delete().await()
    }


    // MONITOREO
    fun getControladorClima(): Flow<List<ControladorClima>> {
        return firestore.collection(COLLECTION_MONITOREO)
            .whereEqualTo("userId", userId)
            .snapshots()
            .map { qs ->
                qs.documents.mapNotNull { ds ->
                    ds.toObject(ControladorClimaDB::class.java)?.let { controladorClima ->
                        ControladorClima(
                            id = ds.id,
                            userId = controladorClima.userId,
                            nombre = controladorClima.nombre,
                            tipo = controladorClima.tipo,
                            ubicacion = controladorClima.ubicacion,
                            imagen = controladorClima.imagen,
                            temperatura = controladorClima.temperatura,
                            humedad = controladorClima.humedad,
                        )
                    }
                }
            }
    }

    suspend fun addControladorClima(controladorClima: ControladorClima) {
        firestore.collection(COLLECTION_MONITOREO).add(controladorClima).await()
    }

    suspend fun updateControladorClima(controladorClima: ControladorClima) {
        val controladorClimaRef = controladorClima.id?.let {
            firestore.collection(COLLECTION_MONITOREO).document(it)
        }
        controladorClimaRef?.set(controladorClima)?.await()
    }

    suspend fun deleteControladorClimaById(controladorClimaId: String) {
        firestore.collection(COLLECTION_MONITOREO).document(controladorClimaId).delete().await()
    }

    fun getMedidorConsumoAgua(): Flow<List<MedidorConsumoAgua>> {
        return firestore.collection(COLLECTION_MONITOREO)
            .whereEqualTo("userId", userId)
            .snapshots()
            .map { qs ->
                qs.documents.mapNotNull { ds ->
                    ds.toObject(MedidorConsumoAguaDB::class.java)?.let { medidorConsumoAgua ->
                        MedidorConsumoAgua(
                            id = ds.id,
                            userId = medidorConsumoAgua.userId,
                            nombre = medidorConsumoAgua.nombre,
                            tipo = medidorConsumoAgua.tipo,
                            ubicacion = medidorConsumoAgua.ubicacion,
                            imagen = medidorConsumoAgua.imagen,
                            litros = medidorConsumoAgua.litros,
                        )
                    }
                }
            }
    }

    suspend fun addMedidorConsumoAgua(medidorConsumoAgua: MedidorConsumoAgua) {
        firestore.collection(COLLECTION_MONITOREO).add(medidorConsumoAgua).await()
    }

    suspend fun updateMedidorConsumoAgua(medidorConsumoAgua: MedidorConsumoAgua) {
        val medidorConsumoAguaRef = medidorConsumoAgua.id?.let {
            firestore.collection(COLLECTION_MONITOREO).document(it)
        }
        medidorConsumoAguaRef?.set(medidorConsumoAgua)?.await()
    }

    suspend fun deleteMedidorConsumoAguaById(medidorConsumoAguaId: String) {
        firestore.collection(COLLECTION_MONITOREO).document(medidorConsumoAguaId).delete().await()
    }

    fun getMedidorGas(): Flow<List<MedidorGas>> {
        return firestore.collection(COLLECTION_MONITOREO)
            .whereEqualTo("userId", userId)
            .snapshots()
            .map { qs ->
                qs.documents.mapNotNull { ds ->
                    ds.toObject(MedidorGasDB::class.java)?.let { medidorGas ->
                        MedidorGas(
                            id = ds.id,
                            userId = medidorGas.userId,
                            nombre = medidorGas.nombre,
                            tipo = medidorGas.tipo,
                            ubicacion = medidorGas.ubicacion,
                            imagen = medidorGas.imagen,
                            metroscubicos = medidorGas.metroscubicos,
                        )
                    }
                }
            }
    }

    suspend fun addMedidorGas(medidorGas: MedidorGas) {
        firestore.collection(COLLECTION_MONITOREO).add(medidorGas).await()
    }

    suspend fun updateMedidorGas(medidorGas: MedidorGas) {
        val medidorGasRef = medidorGas.id?.let {
            firestore.collection(COLLECTION_MONITOREO).document(it)
        }
        medidorGasRef?.set(medidorGas)?.await()
    }

    suspend fun deleteMedidorGasById(medidorGasId: String) {
        firestore.collection(COLLECTION_MONITOREO).document(medidorGasId).delete().await()
    }

}