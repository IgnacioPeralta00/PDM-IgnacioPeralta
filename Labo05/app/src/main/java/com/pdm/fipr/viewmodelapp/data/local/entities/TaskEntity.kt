package com.pdm.fipr.viewmodelapp.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey (autoGenerate = true)
    val id: Int,
    val title: String,
    val content: String,
    val startDate: Instant,
    val isCompleted: Boolean = false
)
