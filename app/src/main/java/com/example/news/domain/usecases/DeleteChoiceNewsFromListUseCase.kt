package com.example.news.domain.usecases

import com.example.news.domain.models.NewsEntity
import com.example.news.domain.repository.NewsRepository

class DeleteChoiceNewsFromListUseCase(
    private val repository: NewsRepository
) {
    suspend operator fun invoke(
        newsEntity: NewsEntity
    ) = repository.deleteChoiceNewsFromListUseCase(newsEntity)
}