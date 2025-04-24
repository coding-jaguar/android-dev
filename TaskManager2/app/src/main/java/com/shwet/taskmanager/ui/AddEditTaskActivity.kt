package com.shwet.taskmanager.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.shwet.taskmanager.data.Task
import com.shwet.taskmanager.databinding.ActivityAddEditTaskBinding
import com.shwet.taskmanager.viewmodel.TaskViewModel

class AddEditTaskActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddEditTaskBinding
    private lateinit var taskViewModel: TaskViewModel

    private var taskId: Int = -1
    private var isEditMode = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddEditTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        taskViewModel = ViewModelProvider(this)[TaskViewModel::class.java]

        // Check if editing existing task
        intent.extras?.let {
            taskId = it.getInt("task_id", -1)
            if (taskId != -1) {
                isEditMode = true
                binding.editTitle.setText(it.getString("task_title"))
                binding.editDescription.setText(it.getString("task_description"))
                binding.editDueDate.setText(it.getString("task_dueDate"))
            }
        }

        binding.btnSave.setOnClickListener {
            saveTask()
        }
    }

    private fun saveTask() {
        val title = binding.editTitle.text.toString().trim()
        val description = binding.editDescription.text.toString().trim()
        val dueDate = binding.editDueDate.text.toString().trim()

        if (title.isEmpty() || dueDate.isEmpty()) {
            Toast.makeText(this, "Title and Due Date are required.", Toast.LENGTH_SHORT).show()
            return
        }

        val task = Task(
            id = if (isEditMode) taskId else 0,
            title = title,
            description = description,
            dueDate = dueDate
        )

        if (isEditMode) {
            taskViewModel.update(task)
            Toast.makeText(this, "Task updated", Toast.LENGTH_SHORT).show()
        } else {
            taskViewModel.insert(task)
            Toast.makeText(this, "Task added", Toast.LENGTH_SHORT).show()
        }

        finish()
    }
}
