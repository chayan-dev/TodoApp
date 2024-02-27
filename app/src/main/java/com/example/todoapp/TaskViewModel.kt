package com.example.todoapp

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskViewModel(
    application: Application
): ViewModel() {

    private val _readAllLiveData= MutableLiveData<List<Task>>()
    val readAllLiveData: LiveData<List<Task>> = _readAllLiveData
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

    fun readAllData(){
        _readAllLiveData.value= repository.readAllData.value
    }

    fun readAllDataOrderByDate(){
        _readAllLiveData.value = repository.readAllDataOrderByDate.value
    }

    fun readAllDataOrderByCompletionDate(){
        _readAllLiveData.value = repository.readAllDataOrderByCompletionStatus.value
//        readAllData = repository.readAllDataOrderByCompletionStatus
        Log.d("complete",repository.readAllDataOrderByCompletionStatus.value.toString())
    }

//    fun deleteWithIDs(){
//        val toDeleteTask: List<Int> = listOf()
//        _readAllLiveData.value?.forEach{
//            if(it.isCompleted) toDeleteTask.plus(it.id)
//        }
//        Log.d("toDelete", toDeleteTask.toString())
//        viewModelScope.launch(Dispatchers.IO){
//            repository.deleteCompletedTaskIDs(listOf(1,4))
//        }
//    }

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