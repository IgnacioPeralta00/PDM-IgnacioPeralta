package com.FIPRUNO.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.FIPRUNO.app.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(modifier: Modifier = Modifier) {

    val usuario: MutableState<String> = remember { mutableStateOf("") }
    // Creamos una lista mutable que Compose pueda observar
    val listaEstudiantes = remember { mutableStateListOf<Estudiante>() }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Box(modifier = Modifier.background(Color.White).weight(0.20f).fillMaxWidth(), contentAlignment = Alignment.Center ) {
            TextField(
                value = usuario.value,
                onValueChange = {
                    usuario.value = it
                },
                label = { Text("Usuario") }
            )
        }
        Box(modifier = Modifier.background(Color.White).weight(0.10f).fillMaxWidth(), contentAlignment = Alignment.Center ) {
            Button(onClick = {
                if (usuario.value.isNotBlank()) {
                    listaEstudiantes.add(Estudiante(usuario.value))
                    usuario.value = "" // Limpiar el campo después de guardar
                }
            }) {
                Text(text = "Guardar")
            }
        }
        Row(modifier = Modifier.weight(0.30f)) {
            Box(modifier = Modifier.background(Color.White).weight(1.5f).fillMaxHeight(), contentAlignment = Alignment.Center) {
                Text(text = "Listado de nombres y posición en la lista", textAlign = TextAlign.Justify)
            }
            Box(modifier = Modifier.background(Color.White).weight(1f).fillMaxHeight(), contentAlignment = Alignment.Center) {
                Button(onClick = {
                    listaEstudiantes.clear()
                }) {
                    Text(text = "Limpiar")
                }
            }
        }
        Box(modifier = Modifier.background(Color.White).weight(1f).fillMaxWidth(), contentAlignment = Alignment.TopCenter ) {
            LazyColumn {
                itemsIndexed(listaEstudiantes) { index, item ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = item.nombre // Accedemos a la propiedad nombre
                        )
                        Text(
                            text = (index + 1).toString()
                        )
                    }
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AppTheme {
        Greeting(modifier = Modifier)
    }
}
