package com.example.news.domain.usecases

import com.example.news.domain.repository.NewsRepository

class GetTopHeadLinesNews(private val repository: NewsRepository) {
    operator fun invoke() = repository.getTopHeadLinesNews()
}