package com.example.news.ui.adapters.like_headlines

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.ListAdapter
import com.example.news.R
import com.example.news.databinding.ItemNewsInfoBinding
import com.example.news.domain.models.NewsEntity
import com.squareup.picasso.Picasso

class NewsLikeAdapter : ListAdapter<NewsEntity, NewsLikeViewHolder>(NewsLikeInfoDiffCallback) {
   var onNewsClickListener: ((NewsEntity) -> Unit)? = null
   var onLikeNewsLongClickListener: ((NewsEntity) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsLikeViewHolder {
        val binding =
            ItemNewsInfoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return NewsLikeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsLikeViewHolder, position: Int) {
        val news = getItem(position)
        with(holder.binding) {
            with(news) {
                tvHeadlineTitle.text = title
                tvHeadlineAuthor.text = author
                tvHeadlinePublished.text = publishedAt
                Picasso.get().load(news.urlToImage)
                    .into(holder.itemView.findViewById<ImageView>(R.id.ivNews))
                holder.itemView.setOnClickListener {
                    onNewsClickListener?.invoke(this)
                }
                holder.itemView.setOnLongClickListener {
                    onLikeNewsLongClickListener?.invoke(this)
                    true
                }

            }
        }
    }
}