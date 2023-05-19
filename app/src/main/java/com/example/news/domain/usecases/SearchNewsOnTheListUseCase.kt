package com.example.news.domain.usecases

import com.example.news.domain.repository.NewsRepository

class SearchNewsOnTheListUseCase(
    private val repository: NewsRepository
) {
    operator fun invoke(query: String) = repository.searchNewsOnTheListUseCase(query)
}