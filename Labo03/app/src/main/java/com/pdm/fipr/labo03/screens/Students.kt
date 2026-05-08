package com.pdm.fipr.labo03.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.pdm.fipr.labo03.model.Student
import com.pdm.fipr.labo03.screens.components.AppScaffold

@Composable
fun StudentsScreen(
    modifier: Modifier = Modifier,
    students: List<Student>,
    onStudentAdd: (String) -> Unit,
    onClear: () -> Unit,
    onBackHome: () -> Unit
) {
    var studentName by rememberSaveable { mutableStateOf("") }

    AppScaffold(
        title = "Lista de Estudiantes",
        modifier = modifier
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            TextField(
                value = studentName,
                onValueChange = { studentName = it },
                label = { Text("Nombre") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        onStudentAdd(studentName)
                        studentName = ""
                    }
                )
            )

            Button(
                onClick = {
                    onStudentAdd(studentName)
                    studentName = ""
                },
                modifier = Modifier
            ) {
                Text(text = "Guardar")
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Listado de nombre y posición en la lista",
                    color = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.weight(1f)
                )
                Button(onClick = onClear) {
                    Text(text = "Limpiar",
                        textAlign = TextAlign.Center)
                }
            }

            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize().border(1.dp, Color.LightGray, shape = RoundedCornerShape(8.dp))
            ) {
                itemsIndexed(students) { index, item ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp, horizontal = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = item.name,
                            color = Color.Gray)
                        Text(
                            text = "${index + 1}",
                            color = Color.Gray
                        )
                    }
                }
            }
            Button(
                onClick = onBackHome,
                modifier = Modifier
                    .padding(top = 16.dp)
            ) {
                Text(
                    text = "Home",
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}