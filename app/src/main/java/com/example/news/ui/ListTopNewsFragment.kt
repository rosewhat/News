package com.example.news.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.news.R
import com.example.news.databinding.ActivityMainBinding
import com.example.news.databinding.FragmentDetailNewsInfoBinding
import com.example.news.databinding.FragmentListTopNewsBinding
import com.example.news.domain.models.NewsEntity
import com.example.news.ui.adapters.NewsAdapter
import com.example.news.ui.viewModel.NewsViewModel


class ListTopNewsFragment : Fragment() {

    private var _binding: FragmentListTopNewsBinding? = null
    private val binding: FragmentListTopNewsBinding
        get() = _binding ?: throw RuntimeException("ListTopNewsFragment is null")

    private lateinit var viewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListTopNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val newsAdapter = NewsAdapter()
        binding.rvCoinPriceList.adapter = newsAdapter
        binding.rvCoinPriceList.itemAnimator = null
        viewModel = ViewModelProvider(this)[NewsViewModel::class.java]
        viewModel.newsInfoList.observe(viewLifecycleOwner) {
            newsAdapter.submitList(it)
        }

        newsAdapter.onNewsClickListener = object : NewsAdapter.OnNewsClickListener {
            override fun onNewsClick(news: NewsEntity) {
                if (binding.fragmentContainer == null) {
                    launchDetailNewsInfoFragment(news.id.toString())
                } else {
                    albomlaunchDetailNewsInfoFragment(news.id.toString())
                }
            }

            override fun onDeleteNewsClick(news: NewsEntity) {
                TODO("Not yet implemented")
            }
        }

        binding.searchNews?.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })


    }


    private fun albomlaunchDetailNewsInfoFragment(id: String) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, DetailNewsInfoFragment.newInstance(id))
            .addToBackStack(null)
            .commit()
    }

    private fun launchDetailNewsInfoFragment(id: String) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, DetailNewsInfoFragment.newInstance(id))
            .addToBackStack(null)
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}