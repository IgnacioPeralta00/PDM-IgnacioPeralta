package com.pdm.fipr.labo03.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pdm.fipr.labo03.screens.components.AppScaffold

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onStudentClick: () -> Unit,
    onSensorClick: () -> Unit
) {
    AppScaffold(
        modifier = modifier,
        title = "Home") { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = onStudentClick) {
                Text(text = "Lista de Estudiantes")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = onSensorClick) {
                Text(text = "Datos de Sensores")
            }
        }
    }
}