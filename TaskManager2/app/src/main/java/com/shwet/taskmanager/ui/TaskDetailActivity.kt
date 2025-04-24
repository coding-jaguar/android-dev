package com.shwet.taskmanager.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.shwet.taskmanager.data.Task
import com.shwet.taskmanager.databinding.ActivityTaskDetailBinding
import com.shwet.taskmanager.viewmodel.TaskViewModel

class TaskDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTaskDetailBinding
    private lateinit var taskViewModel: TaskViewModel

    private var taskId: Int = -1
    private lateinit var currentTask: Task

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTaskDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        taskViewModel = ViewModelProvider(this)[TaskViewModel::class.java]

        val title = intent.getStringExtra("task_title") ?: ""
        val description = intent.getStringExtra("task_description") ?: ""
        val dueDate = intent.getStringExtra("task_dueDate") ?: ""
        taskId = intent.getIntExtra("task_id", -1)

        currentTask = Task(taskId, title, description, dueDate)

        binding.textTitle.text = title
        binding.textDescription.text = description.ifEmpty { "No description" }
        binding.textDueDate.text = "Due: $dueDate"

        binding.btnDelete.setOnClickListener {
            taskViewModel.delete(currentTask)
            Toast.makeText(this, "Task deleted", Toast.LENGTH_SHORT).show()
            finish()
        }

        binding.btnEdit.setOnClickListener {
            val intent = android.content.Intent(this, AddEditTaskActivity::class.java).apply {
                putExtra("task_id", currentTask.id)
                putExtra("task_title", currentTask.title)
                putExtra("task_description", currentTask.description)
                putExtra("task_dueDate", currentTask.dueDate)
            }
            startActivity(intent)
            finish()
        }

    }
}
