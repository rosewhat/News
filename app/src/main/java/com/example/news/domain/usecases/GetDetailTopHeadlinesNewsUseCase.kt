package com.example.news.domain.usecases

import com.example.news.domain.repository.NewsRepository

class GetDetailTopHeadlinesNewsUseCase(
    private val repository: NewsRepository
) {
    operator fun invoke(author: String) = repository.getDetailTopHeadlinesNewsUseCase(author = author)
}