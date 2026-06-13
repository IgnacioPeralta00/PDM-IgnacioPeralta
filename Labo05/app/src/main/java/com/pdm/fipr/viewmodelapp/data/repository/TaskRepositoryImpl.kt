package com.pdm.fipr.viewmodelapp.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.pdm.fipr.viewmodelapp.data.local.dao.TaskDao
import com.pdm.fipr.viewmodelapp.data.local.mapper.toDomainModel
import com.pdm.fipr.viewmodelapp.data.local.mapper.toEntity
import com.pdm.fipr.viewmodelapp.domain.model.Task
import com.pdm.fipr.viewmodelapp.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TaskRepositoryImpl(
    private val dataSource: TaskDao,
) : TaskRepository {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun getAllTasks(): Flow<List<Task>> {
        return dataSource.getAllTasks().map { tasks ->
            tasks.map { task -> task.toDomainModel() }
        }
    }

    override suspend fun insertTask(task: Task) {
        dataSource.insertTask(task.toEntity())
    }

    override suspend fun deleteTask(task: Task) {
        dataSource.deleteTask(task.toEntity())
    }
}