package com.pdm.fipr.labo03

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.Modifier
import com.pdm.fipr.labo03.ui.theme.Labo03Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Labo03Theme {
                AppNavigator(
                    modifier = Modifier
                )
            }
        }
    }
}

