package com.example.exceptionapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import androidx.activity.viewModels
import com.example.exceptionapplication.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.apply {
            this.lifecycleOwner = this@MainActivity
            this.viewmodel = viewModel
        }

        binding.button2.setOnClickListener {
            viewModel.onEvent(Event.StopReloadButtonClicked)
        }

        binding.button.setOnClickListener {
            viewModel.onEvent(Event.ReloadButtonClicked)
        }
    }
}