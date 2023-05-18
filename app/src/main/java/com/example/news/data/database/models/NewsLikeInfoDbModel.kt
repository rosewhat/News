package com.example.news.data.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "like_news")
data class NewsLikeInfoDbModel(
    @PrimaryKey
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String,
)
