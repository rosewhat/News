package com.example.news.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.news.databinding.FragmentDetailNewsInfoBinding
import com.example.news.ui.viewModel.NewsViewModel
import com.squareup.picasso.Picasso


class DetailNewsInfoFragment : Fragment() {

    private var _binding: FragmentDetailNewsInfoBinding? = null
    private val binding: FragmentDetailNewsInfoBinding
        get() = _binding ?: throw RuntimeException("DetailNewsInfoFragment is null")

    private lateinit var viewModel: NewsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailNewsInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[NewsViewModel::class.java]
        val id = getParamId()
        viewModel.getDetailNewsInfo(id).observe(viewLifecycleOwner) { info ->
            with(binding) {
                tvDetailTitle.text = info.title
                tvDetailContent.text = info.content
                tvDetailPublished.text = info.publishedAt
                tvDetailAuthor.text = info.author
                btDetailReadFullContent.setOnClickListener {
                    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(info.url)))
                }
                ivDetailNewsRepost.setOnClickListener {
                    val sendIntent = Intent(Intent.ACTION_SEND).apply {
                        putExtra(Intent.EXTRA_TEXT, tvDetailContent.text.toString())
                        type = "text/plain"
                    }
                    startActivity(Intent.createChooser(sendIntent, "Share using"))
                }
                Picasso.get().load(info.urlToImage).into(ivDetailNews)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getParamId(): String {
        return requireArguments().getString(EXTRA_AUTHOR, DEFAULT_VALUE)
    }

    companion object {
        private const val EXTRA_AUTHOR = "author"
        private const val DEFAULT_VALUE = ""

        fun newInstance(author: String): Fragment {
            return DetailNewsInfoFragment().apply {
                arguments = Bundle().apply {
                    putString(EXTRA_AUTHOR, author)
                }
            }
        }
    }
}