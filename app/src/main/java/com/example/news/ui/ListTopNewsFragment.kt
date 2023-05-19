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
import com.example.news.ui.adapters.top_headlines.NewsAdapter
import com.example.news.ui.viewModel.NewsViewModel
import com.example.news.ui.viewModel.RoleModelViewModel


class ListTopNewsFragment : Fragment() {
    private var _binding: FragmentListTopNewsBinding? = null
    private val binding: FragmentListTopNewsBinding
        get() = _binding ?: throw RuntimeException(FRAGMENT_ERROR)

    private var currentQuery: String = ""


    private lateinit var viewModel: NewsViewModel
    private lateinit var viewModelRole: RoleModelViewModel
    private lateinit var newsAdapter: NewsAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListTopNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[NewsViewModel::class.java]
        viewModelRole = ViewModelProvider(this)[RoleModelViewModel::class.java]
        newsAdapter = NewsAdapter()
        viewModelRole.checkCurrentUser()
        viewModelRole.getCheckStatusLiveData().observe(viewLifecycleOwner) {
            if (it == false) {
                launchRegisterFragment()
            }
        }
        binding.btnSignOutFromAccount?.setOnClickListener {
            viewModelRole.signOutFromAccount()
            viewModelRole.getSignOutFromAccountLiveData().observe(viewLifecycleOwner) {
                if (it == true) {
                    launchRegisterFragment()
                }
            }
        }
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
                currentQuery = newText.orEmpty()
                setDataInList()
                return true
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
        viewModel.newsInfoList.observe(viewLifecycleOwner) { newsList ->
            if (currentQuery.isEmpty()) {
                newsAdapter.updateNewsListFiltered(newsList)
            } else {
                val filteredList =
                    newsList.filter { it.title.contains(currentQuery, ignoreCase = true) }
                newsAdapter.updateNewsListFiltered(filteredList)
            }
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

    private fun launchRegisterFragment() {
        requireActivity().supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                R.anim.slide_in_right,
                R.anim.slide_out_left,
                R.anim.slide_in_left,
                R.anim.slide_out_right
            )
            .replace(R.id.main_container, RegistrationUserFragment.newInstance())
            .commit()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val FRAGMENT_ERROR = "ListTopNewsFragment is null"
        fun newInstance(): Fragment {
            return ListTopNewsFragment()
        }
    }

}