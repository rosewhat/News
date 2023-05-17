package com.example.news.ui.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.news.domain.models.NewsEntity

object NewsInfoDiffCallback : DiffUtil.ItemCallback<NewsEntity>() {
    override fun areItemsTheSame(oldItem: NewsEntity, newItem: NewsEntity): Boolean {
        return oldItem.author == newItem.author
    }

    override fun areContentsTheSame(oldItem: NewsEntity, newItem: NewsEntity): Boolean {
        return oldItem == newItem
    }
}