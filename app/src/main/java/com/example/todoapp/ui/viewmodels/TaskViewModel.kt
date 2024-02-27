package com.example.todoapp.ui.viewmodels

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.db.TaskDatabase
import com.example.todoapp.models.Task
import com.example.todoapp.repository.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskViewModel(
    application: Application
): ViewModel() {

    lateinit var readAllLiveData: LiveData<List<Task>>
    private lateinit var repository: TaskRepository

    init {
        val taskDao = TaskDatabase.getDatabase(application).taskDao()
        repository = TaskRepository(taskDao)
        readAllData()
    }

    fun addTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO){
            repository.addTask(task)
        }
    }

    private fun readAllData(){
        readAllLiveData = repository.readAllData
    }

    fun deleteCompletedData(){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteCompletedTask()
        }
    }

    fun deleteTask(task: Task){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteTask(task)
        }
    }
}