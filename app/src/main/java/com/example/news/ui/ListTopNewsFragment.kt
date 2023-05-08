package com.example.news.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.news.R
import com.example.news.databinding.ActivityMainBinding
import com.example.news.databinding.FragmentDetailNewsInfoBinding
import com.example.news.databinding.FragmentListTopNewsBinding


class ListTopNewsFragment : Fragment() {

    private var _binding: FragmentListTopNewsBinding? = null
    private val binding: FragmentListTopNewsBinding
        get() = _binding ?: throw RuntimeException("ListTopNewsFragment is null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =  FragmentListTopNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}