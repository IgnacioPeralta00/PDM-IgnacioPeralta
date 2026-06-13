package com.pdm.fipr.viewmodelapp.domain.repository

import com.pdm.fipr.viewmodelapp.domain.model.Task
import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    fun getAllTasks(): Flow<List<Task>>
    suspend fun insertTask(task: Task)
    suspend fun deleteTask(task: Task)
}