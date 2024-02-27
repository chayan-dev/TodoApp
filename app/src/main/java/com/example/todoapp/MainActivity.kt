package com.example.todoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.todoapp.databinding.ActivityMainBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

class MainActivity : AppCompatActivity() {

//    private lateinit var binding: ActivityMainBinding
//    private lateinit var taskAdapter: TaskAdapter
    private lateinit var viewModel: TaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        initTaskList()
//        viewModel.addTask(Task(0,"22/03/202", "task1", false))
//        viewModel.addTask(Task(1,"22/03/202", "task2", true))
//        viewModel.addTask(Task(2,"22/03/202", "task3", false))
//        viewModel.addTask(Task(3,"22/03/202", "task4", true))
//        viewModel.readAllData.observe(this){
//            taskAdapter.taskList = it
//            taskAdapter.notifyDataSetChanged()
//        }
//        setOnClickListener()
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this, ViewModelProviderFactory(application)).get(TaskViewModel::class.java)

    }

//    private fun setOnClickListener() {
//        binding.addBtn.setOnClickListener {
//            val bottomSheet = BottomSheetDialog()
//            bottomSheet.show(parentFragmentManager, "ModalBottomSheet")
//        }
//    }
//
//    private fun initTaskList(){
////        val list = listOf(Task(0,"22/03/202", "task1", false), Task(0,"22/03/202", "task2", true), Task(0,"22/03/202", "task3", false), Task(0,"22/03/202", "task1", false))
//        taskAdapter = TaskAdapter(listOf())
//        binding.rvTask.adapter = taskAdapter
//    }
}