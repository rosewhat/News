package com.example.news.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.news.databinding.ActivityMainBinding
import com.example.news.ui.viewModel.NewsViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: NewsViewModel
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[NewsViewModel::class.java]

    }
}