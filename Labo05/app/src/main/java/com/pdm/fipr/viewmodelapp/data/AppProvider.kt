package com.pdm.fipr.viewmodelapp.data

import android.content.Context
import com.pdm.fipr.viewmodelapp.data.local.AppDatabase
import com.pdm.fipr.viewmodelapp.data.repository.TaskRepositoryImpl
import com.pdm.fipr.viewmodelapp.domain.repository.TaskRepository

class AppProvider(context: Context) {
    private val appDatabase = AppDatabase.getDatabase(context)
    private val taskDao = appDatabase.taskDao()

    private val taskRepository : TaskRepository = TaskRepositoryImpl(taskDao)

    fun provideTaskRepository(): TaskRepository {
        return taskRepository
    }
}