package com.example.todoapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.todoapp.databinding.LayoutTaskBottomsheetBinding
import com.example.todoapp.models.Task
import com.example.todoapp.ui.viewmodels.TaskViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.gson.Gson

class TaskBottomSheet : BottomSheetDialogFragment() {

    private val viewModel: TaskViewModel by activityViewModels()
    private lateinit var binding: LayoutTaskBottomsheetBinding
    private var listener: DismissBottomSheetListener? = null
    lateinit var task: Task
    var isTaskUpdate: Boolean = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = LayoutTaskBottomsheetBinding.inflate(inflater, container, false)
        isTaskUpdate = arguments?.getBoolean("isUpdate") == true
        if(isTaskUpdate) task = Gson().fromJson(arguments?.getString("taskObj"), Task::class.java)
        setData()
        setOnClickListener()
        return binding.root
    }

    private fun setData() {
        if (isTaskUpdate){
            binding.etTitle.setText(task.title)
            binding.etDueDate.setText(task.date)
            binding.etDueDate.isEnabled = false
            binding.checkbox.isEnabled = false
        }
    }

    private fun setOnClickListener() {
        binding.saveBtn.setOnClickListener {
            if (binding.etTitle.text?.isNotEmpty() == true && binding.etDueDate.text?.isNotEmpty() == true){
                if(isTaskUpdate){
                    viewModel.addTask(Task(task.id,task.date,binding.etTitle.text.toString(),task.isCompleted))
                }
                else {
                    viewModel.addTask(
                        Task(
                            date = binding.etDueDate.text.toString(),
                            title = binding.etTitle.text.toString(),
                            isCompleted = binding.checkbox.isChecked
                        )
                    )
                }
                listener?.dismissBottomSheetAction()
                dismiss()
            } else{
                Toast.makeText(context,"Title or Due date is empty", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

interface DismissBottomSheetListener {
    fun dismissBottomSheetAction()
}