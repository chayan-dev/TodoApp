package com.example.todoapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.todoapp.databinding.FragmentHomeBinding
import com.google.gson.Gson


class HomeFragment : Fragment(), DismissBottomSheetListener {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var taskAdapter: TaskAdapter
    private val viewModel: TaskViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        initTaskList()
        viewModel.readAllLiveData.observe(viewLifecycleOwner){
//            Log.d("complete_fragment",it.toString())
            if(it!=null) taskAdapter.taskList = it
            taskAdapter.notifyDataSetChanged()
        }
        setOnClickListener()

        return binding.root
    }

    private fun setOnClickListener() {
        binding.addBtn.setOnClickListener {
            val bottomSheet = TaskBottomSheet()
            val bundle = Bundle()
            bundle.putBoolean("isUpdate", false)
            bottomSheet.arguments = bundle
            bottomSheet.show(parentFragmentManager, "ModalBottomSheet")
        }

        binding.deleteAllBtn.setOnClickListener {
            viewModel.deleteCompletedData()
        }

        binding.tvOrderByCompletionStatus.setOnClickListener {
            viewModel.readAllDataOrderByCompletionDate()
        }

        binding.tvOrderByDate.setOnClickListener {
            viewModel.readAllDataOrderByDate()
        }
    }

    private fun initTaskList(){
        taskAdapter = TaskAdapter(listOf(),
            onClick = {
                val bottomSheet = TaskBottomSheet()
                val bundle = Bundle()
                bundle.putBoolean("isUpdate", true)
                bundle.putString("taskObj",Gson().toJson(it))
                bottomSheet.arguments = bundle
                bottomSheet.show(parentFragmentManager, "ModalBottomSheet")
            },
            onCheckClick = {
                viewModel.addTask(it)
            },
            onDeleteClick = {
                viewModel.deleteTask(it)
            })
        binding.rvTask.adapter = taskAdapter
    }

    override fun dismissBottomSheetAction() {
        taskAdapter.notifyDataSetChanged()
    }


}