package com.example.todoapp.ui.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.R
import com.example.todoapp.databinding.ItemTaskBinding
import com.example.todoapp.models.Task

class TaskAdapter(
    var taskList: List<Task>,
    val onClick: (task: Task) -> Unit,
    val onCheckClick: (task: Task) -> Unit,
    val onDeleteClick: (task: Task) -> Unit
): RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    private lateinit var binding: ItemTaskBinding

    inner class TaskViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(
            parent.context.getSystemService(LayoutInflater::class.java).inflate(
                R.layout.item_task,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        binding = ItemTaskBinding.bind(holder.itemView)
        val task = taskList[position]
        binding.apply {
            checkbox.text = task.title
            checkbox.isChecked = task.isCompleted
            tvDate.text = task.date
            editBtn.setOnClickListener { onClick(task) }
            checkbox.setOnClickListener {
                task.isCompleted = !task.isCompleted
                onCheckClick(task)
            }
            deleteBtn.setOnClickListener { onDeleteClick(task) }
        }
    }

    override fun getItemCount(): Int {
        return taskList.size
    }
}