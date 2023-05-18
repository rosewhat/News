package com.example.news.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.news.R
import com.example.news.databinding.FragmentListTopNewsBinding
import com.example.news.ui.adapters.NewsAdapter
import com.example.news.ui.viewModel.NewsViewModel


class ListTopNewsFragment : Fragment() {

    private var _binding: FragmentListTopNewsBinding? = null
    private val binding: FragmentListTopNewsBinding
        get() = _binding ?: throw RuntimeException(FRAGMENT_ERROR)

    private lateinit var viewModel: NewsViewModel
    private lateinit var newsAdapter: NewsAdapter

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
        newsAdapter = NewsAdapter()
        binding.rvCoinPriceList.adapter = newsAdapter
        viewModel = ViewModelProvider(this)[NewsViewModel::class.java]
        setDataInList()
        setupClickListener()
        setupSwipeListener()

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

    private fun setDataInList() {
        viewModel.newsInfoList.observe(viewLifecycleOwner) {
            newsAdapter.submitList(it)
        }
    }

    private fun setupSwipeListener() {
        val callback = object : ItemTouchHelper.SimpleCallback(
            0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                viewModel.deleteChoiceNewsFromListUseCase(newsAdapter.currentList[viewHolder.adapterPosition])
            }
        }

        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(binding.rvCoinPriceList)
    }

    private fun setupClickListener() {
        newsAdapter.onNewsClickListener = {
            if (binding.fragmentContainer == null) {
                launchDetailNewsInfoFragment(it.author)
            } else {
                albumLaunchDetailNewsInfoFragment(it.author)
            }
        }
    }

    private fun albumLaunchDetailNewsInfoFragment(id: String) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, DetailNewsInfoFragment.newInstance(id))
            .addToBackStack(null)
            .commit()
    }

    private fun launchDetailNewsInfoFragment(author: String) {
        requireActivity().supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                R.anim.slide_in_right,
                R.anim.slide_out_left,
                R.anim.slide_in_left,
                R.anim.slide_out_right
            )
            .replace(R.id.main_container, DetailNewsInfoFragment.newInstance(author))
            .addToBackStack(null)
            .commit()
    }

    companion object {
        private const val FRAGMENT_ERROR = "ListTopNewsFragment is null"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}