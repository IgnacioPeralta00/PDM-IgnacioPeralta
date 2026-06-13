package com.pdm.fipr.viewmodelapp.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import com.pdm.fipr.viewmodelapp.ViewmodelappApplication
import com.pdm.fipr.viewmodelapp.domain.model.Task
import com.pdm.fipr.viewmodelapp.domain.repository.TaskRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TasksViewModel(
    private val taskRepository: TaskRepository
) : ViewModel() {

    val tasks : StateFlow<List<Task>> =
        taskRepository.getAllTasks()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList()
            )

    fun addTask(
        title : String,
        description : String
    ) {
        viewModelScope.launch {
            taskRepository.insertTask(
                Task(
                    title = title,
                    description = description
                )
            )
        }
    }
    fun deleteTask(task : Task) {
        viewModelScope.launch {
            taskRepository.deleteTask(task)
        }
    }

    companion object {
        val Factory = viewModelFactory {
            initializer {
                val app = this[APPLICATION_KEY] as ViewmodelappApplication
                TasksViewModel(app.appProvider.provideTaskRepository())
            }
        }
    }
}
