package com.pdm.fipr.viewmodelapp.domain.model

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.Instant

data class Task @RequiresApi(Build.VERSION_CODES.O) constructor(
    val id: Int = 0,
    val title: String,
    val description: String,
    val endDate: Instant = Instant.now(),
    val isCompleted: Boolean = false
)