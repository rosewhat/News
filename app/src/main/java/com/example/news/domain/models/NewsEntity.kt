package com.example.news.domain.models

data class NewsEntity(
    val source: SourceEntity,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String,
)
