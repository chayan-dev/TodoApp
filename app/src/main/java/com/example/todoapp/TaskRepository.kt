package com.example.todoapp

import android.util.Log
import androidx.lifecycle.LiveData

class TaskRepository(
    private val taskDAO: TaskDAO
) {
    val readAllData: LiveData<List<Task>> = taskDAO.readAllTask()

    suspend fun addTask(task: Task) = taskDAO.addTask(task)
    suspend fun deleteCompletedTask() {
        val res = taskDAO.deleteAllCompletedTask()
        Log.d("delete_res", res.toString())
    }
    suspend fun deleteCompletedTaskIDs(toDeleteTask: List<Int>) {
        val res = taskDAO.deleteAllCompletedTaskIDs(toDeleteTask)
        Log.d("delete_res", res.toString())
    }
    suspend fun deleteTask(task: Task) {
        val res = taskDAO.deleteTask(task)
        Log.d("delete_res", res.toString())
    }
}