package com.pdm.fipr.labo03.screens

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pdm.fipr.labo03.screens.components.AppScaffold

import kotlin.math.abs

@Composable
fun useSensor(sensorType: Int): List<Float> {
    val context = LocalContext.current
    val sensorManager = remember { context.getSystemService(Context.SENSOR_SERVICE) as SensorManager }
    val sensor = sensorManager.getDefaultSensor(sensorType) ?: return emptyList()
    var sensorValues by remember { mutableStateOf(listOf(0f, 0f, 0f)) }

    // Constante para el filtro paso bajo (0.0 a 1.0)
    // Valores menores = más suavizado pero más retraso
    val alpha = 0.1f

    DisposableEffect(sensorType) {
        val listener = object : SensorEventListener {
            override fun onSensorChanged(event: SensorEvent?) {
                event?.values?.let { newValues ->
                    // Aplicar Filtro Paso Bajo: smoothed = alpha * new + (1 - alpha) * old
                    val filteredValues = sensorValues.zip(newValues.toList()) { old, new ->
                        val smoothed = alpha * new + (1 - alpha) * old
                        
                        // Aplicar umbral (Noise Gate) para ignorar jitter pequeño cerca de 0
                        if (abs(smoothed) < 0.02f) 0f else smoothed
                    }
                    sensorValues = filteredValues
                }
            }

            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
        }

        sensorManager.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_UI)

        onDispose {
            sensorManager.unregisterListener(listener)
        }
    }

    return sensorValues
}

@Composable
fun GyroscopeSensor (
    modifier: Modifier = Modifier,
    onBackHome: () -> Unit
) {
    val gyroscopeValues = useSensor(Sensor.TYPE_GYROSCOPE)


    AppScaffold(
        modifier = modifier,
        title = "Giroscopio"
    ) { innerPadding ->
        Column (
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Giroscopio", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "X: ${"%.3f".format(gyroscopeValues[0])}", fontSize = 18.sp)
            Text(text = "Y: ${"%.3f".format(gyroscopeValues[1])}", fontSize = 18.sp)
            Text(text = "Z: ${"%.3f".format(gyroscopeValues[2])}", fontSize = 18.sp)
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = onBackHome) {
                Text(
                    text = "Home",
                    fontSize = 18.sp
                )
            }
        }
    }
}
