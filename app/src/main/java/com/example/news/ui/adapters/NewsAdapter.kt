package com.example.news.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.ListAdapter

import com.example.news.R
import com.example.news.databinding.ItemNewsInfoBinding
import com.example.news.domain.models.NewsEntity
import com.squareup.picasso.Picasso

class NewsAdapter : ListAdapter<NewsEntity, NewsViewHolder>(NewsInfoDiffCallback) {
    var onNewsClickListener: OnNewsClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding =
            ItemNewsInfoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val news = getItem(position)
        with(holder.binding) {
            with(news) {
                tvHeadlineTitle.text = title
                tvHeadlineAuthor.text = author
                tvHeadlinePublished.text = publishedAt
                Picasso.get().load(news.urlToImage)
                    .into(holder.itemView.findViewById<ImageView>(R.id.ivNews))
                holder.itemView.setOnClickListener {
                    onNewsClickListener?.onNewsClick(this)
                }
                holder.itemView.setOnLongClickListener {
                    onNewsClickListener?.onDeleteNewsClick(news)
                    true
                }
            }
        }


    }

    interface OnNewsClickListener {
        fun onNewsClick(news: NewsEntity)
        fun onDeleteNewsClick(news: NewsEntity)
    }

}