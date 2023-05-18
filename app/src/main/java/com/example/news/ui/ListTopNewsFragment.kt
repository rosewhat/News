package com.example.news.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.news.R
import com.example.news.databinding.FragmentListTopNewsBinding
import com.example.news.ui.adapters.top_headlines.NewsAdapter
import com.example.news.ui.viewModel.NewsViewModel


class ListTopNewsFragment : Fragment() {

    private var _binding: FragmentListTopNewsBinding? = null
    private val binding: FragmentListTopNewsBinding
        get() = _binding ?: throw RuntimeException(FRAGMENT_ERROR)

    private lateinit var viewModel: NewsViewModel
    private lateinit var newsAdapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("ListTopNewsFragment", "onCreate")
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
        Log.d("ListTopNewsFragment", "onViewCreated")
        viewModel = ViewModelProvider(this)[NewsViewModel::class.java]
        newsAdapter = NewsAdapter()
        binding.rvCoinPriceList.adapter = newsAdapter
        setDataInList()
        setupClickListener()
        setupSwipeListener()
        binding.btLaunchFavoriteFragment.setOnClickListener {
            launchFavoriteNewsInfoFragment()
        }

        binding.searchNews.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
        newsAdapter.onNewsClickListener = {
            if (binding.fragmentContainerLandscape == null) {
                launchDetailNewsInfoFragment(it.author)
            } else {
                albumLaunchDetailNewsInfoFragment(it.author)
            }
        }
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
            launchDetailNewsInfoFragment(it.author)
        }

        newsAdapter.onLikeNewsLongClickListener = {
            viewModel.insertLikeNews(it)
            Log.d("LIKE_NEWS", it.toString())
        }
    }

    private fun albumLaunchDetailNewsInfoFragment(author: String) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerLandscape, DetailNewsInfoFragment.newInstance(author))
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

    private fun launchFavoriteNewsInfoFragment() {
        requireActivity().supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                R.anim.slide_in_right,
                R.anim.slide_out_left,
                R.anim.slide_in_left,
                R.anim.slide_out_right
            )
            .replace(R.id.main_container, FavoriteNewsFragment.newInstance())
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