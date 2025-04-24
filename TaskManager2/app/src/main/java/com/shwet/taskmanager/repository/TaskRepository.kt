package com.shwet.taskmanager.repository

import androidx.lifecycle.LiveData
import com.shwet.taskmanager.data.Task
import com.shwet.taskmanager.data.TaskDao

class TaskRepository(private val taskDao: TaskDao) {
    val allTasks: LiveData<List<Task>> = taskDao.getAllTasks()

    suspend fun insert(task: Task) = taskDao.insert(task)
    suspend fun update(task: Task) = taskDao.update(task)
    suspend fun delete(task: Task) = taskDao.delete(task)
}