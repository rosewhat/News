package com.example.news.data.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "full_news")
data class NewsInfoDbModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String,
)