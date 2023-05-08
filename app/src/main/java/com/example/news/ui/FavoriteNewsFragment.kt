package com.example.news.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.news.R
import com.example.news.databinding.FragmentDetailNewsInfoBinding
import com.example.news.databinding.FragmentFavoriteNewsBinding
import com.example.news.databinding.FragmentListTopNewsBinding


class FavoriteNewsFragment : Fragment() {
    private var _binding: FragmentFavoriteNewsBinding? = null
    private val binding: FragmentFavoriteNewsBinding
        get() = _binding ?: throw RuntimeException("FavoriteNewsFragment is null")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View  {
        _binding =  FragmentFavoriteNewsBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}