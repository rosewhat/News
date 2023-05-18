package com.example.news.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.news.R
import com.example.news.databinding.FragmentFavoriteNewsBinding
import com.example.news.ui.adapters.like_headlines.NewsLikeAdapter
import com.example.news.ui.viewModel.NewsViewModel


class FavoriteNewsFragment : Fragment() {
    private var _binding: FragmentFavoriteNewsBinding? = null
    private val binding: FragmentFavoriteNewsBinding
        get() = _binding ?: throw RuntimeException(FRAGMENT_ERROR)

    private lateinit var viewModel: NewsViewModel
    private lateinit var newsAdapter: NewsLikeAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newsAdapter = NewsLikeAdapter()
        binding.rvLikePriceList.adapter = newsAdapter
        viewModel = ViewModelProvider(this)[NewsViewModel::class.java]
        viewModel.newsLikeInfoList.observe(viewLifecycleOwner) {
            newsAdapter.submitList(it)
        }
        setupSwipeListener()
        newsAdapter.onNewsClickListener = {
            launchDetailNewsInfoFragment(it.author)
        }
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
                viewModel.deleteChoiceLikeNewsFromList(newsAdapter.currentList[viewHolder.adapterPosition])
            }
        }

        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(binding.rvLikePriceList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        private const val FRAGMENT_ERROR = "FavoriteNewsFragment is null"
        fun newInstance(): Fragment {
            return FavoriteNewsFragment()
        }
    }
}