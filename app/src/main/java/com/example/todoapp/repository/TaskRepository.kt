package com.example.todoapp.repository

import androidx.lifecycle.LiveData
import com.example.todoapp.db.TaskDAO
import com.example.todoapp.models.Task

class TaskRepository(
    private val taskDAO: TaskDAO
) {
    val readAllData: LiveData<List<Task>> = taskDAO.readAllTask()

    suspend fun addTask(task: Task) = taskDAO.addTask(task)
    suspend fun deleteCompletedTask() = taskDAO.deleteAllCompletedTask()
    suspend fun deleteTask(task: Task) = taskDAO.deleteTask(task)
}