package com.example.todoapp

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TaskDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addTask(task: Task)

    @Delete
    fun deleteTask(task: Task)

    @Query("SELECT * FROM task_table  ORDER BY id ASC")
    fun readAllTask(): LiveData<List<Task>>

    @Query("DELETE FROM task_table WHERE isCompleted = 1")
    fun deleteAllCompletedTask(): Int

    @Query("DELETE FROM task_table WHERE id IN (:tasks)")
    fun deleteAllCompletedTaskIDs(tasks: List<Int>): Int
}