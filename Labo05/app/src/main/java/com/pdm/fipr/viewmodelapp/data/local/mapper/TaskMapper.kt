package com.pdm.fipr.viewmodelapp.data.local.mapper

import android.os.Build
import androidx.annotation.RequiresApi
import com.pdm.fipr.viewmodelapp.data.local.entities.TaskEntity
import com.pdm.fipr.viewmodelapp.domain.model.Task


// Mappers
// De domain a Entity
fun Task.toEntity() = TaskEntity(
    id = id,
    title = title,
    content = description,
    endDate = endDate,
    isCompleted = isCompleted
)

// De entity a domain
@RequiresApi(Build.VERSION_CODES.O)
fun TaskEntity.toDomainModel() = Task(
    id = id,
    title = title,
    description = content,
    endDate = endDate,
    isCompleted = isCompleted
)