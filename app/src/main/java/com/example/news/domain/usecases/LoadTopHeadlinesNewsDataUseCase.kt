package com.example.news.domain.usecases

import com.example.news.domain.repository.NewsRepository

class LoadTopHeadlinesNewsDataUseCase(
    private val repository: NewsRepository
) {
    suspend operator fun invoke() = repository.loadTopHeadlinesNewsData()
}