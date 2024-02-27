package com.example.todoapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.todoapp.R
import com.example.todoapp.ui.viewmodels.TaskViewModel
import com.example.todoapp.ui.viewmodels.ViewModelProviderFactory

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: TaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this, ViewModelProviderFactory(application)).get(TaskViewModel::class.java)

    }
}