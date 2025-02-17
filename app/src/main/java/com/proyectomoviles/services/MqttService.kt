package com.proyectomoviles.services

import android.content.Context
import android.util.Log
import org.eclipse.paho.android.service.MqttAndroidClient
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken
import org.eclipse.paho.client.mqttv3.IMqttMessageListener
import org.eclipse.paho.client.mqttv3.MqttCallback
import org.eclipse.paho.client.mqttv3.MqttClient
import org.eclipse.paho.client.mqttv3.MqttConnectOptions
import org.eclipse.paho.client.mqttv3.MqttException
import org.eclipse.paho.client.mqttv3.MqttMessage
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence

class MqttService(val context: Context, val idClient: String) {
    private val MQTT_SERVER_URI = "tcp://ieshm.mooo.com:1883"
    private val MQTT_USER = "mqtt"
    private val MQTT_PASS = "mqtt"
    private val TAG = "MqttService"

    private lateinit var client: MqttClient

    // Conecta al servidor MQTT
    fun connect() {
        try {
            Log.d(TAG, "Connecting to $MQTT_SERVER_URI")
            val persistence = MemoryPersistence()

            // Crea un cliente MQTT con la URI del servidor y el ID del cliente
            client = MqttClient(MQTT_SERVER_URI, idClient, persistence)

            val connectOptions = MqttConnectOptions()
            connectOptions.isCleanSession = true
            connectOptions.userName = MQTT_USER
            connectOptions.password = MQTT_PASS.toCharArray()

            // Conecta al servidor con las opciones especificadas
            client.connect(connectOptions)

            // Establece el callback para manejar eventos de conexión, mensajes y entrega
            client.setCallback(object : MqttCallback {
                override fun messageArrived(topic: String?, message: MqttMessage?) {
                    Log.d(TAG, "Receive message: ${message.toString()} from topic: $topic")
                }

                override fun connectionLost(cause: Throwable?) {
                    Log.d(TAG, "Connection lost ${cause.toString()}")
                }

                override fun deliveryComplete(token: IMqttDeliveryToken?) {
                    Log.d(TAG, "Delivery complete")
                }
            })
        } catch (e: MqttException) {
            e.printStackTrace()
            Log.d(TAG, "Error al conectar. ${e.message}")
        }
    }

    // Desconecta del servidor MQTT
    fun disconnect() {
        try {
            client.disconnect()
        } catch (e: MqttException) {
            e.printStackTrace()
        }
    }

    // Publica un mensaje en un tópico específico
    fun publish(topic: String, message: String) {
        try {
            val mqttMessage = MqttMessage(message.toByteArray())
            client.publish(topic, mqttMessage)
        } catch (e: MqttException) {
            e.printStackTrace()
        }
    }

    // Se suscribe a un tópico específico con un nivel de calidad de servicio (QoS) y un listener para manejar los mensajes recibidos
    fun subscribe(topic: String, qos: Int = 1, listener: (String) -> Unit) {
        try {
            client.subscribe(topic, qos, object : IMqttMessageListener {
                override fun messageArrived(topic: String?, message: MqttMessage?) {
                    Log.d(TAG, "Receive message al conectar: ${message.toString()} from topic: $topic")
                    listener(message.toString())
                }
            })
        } catch (e: MqttException) {
            e.printStackTrace()
        }
    }

    // Conecta al broker MQTT usando MqttAndroidClient
    fun connectBroker() {
        val mqttClient = MqttAndroidClient(context, MQTT_SERVER_URI, idClient)
        val mqttConnectOptions = MqttConnectOptions()

        try {
            mqttClient.connect(mqttConnectOptions)
        } catch (e: MqttException) {
            e.printStackTrace()
        }
    }
}