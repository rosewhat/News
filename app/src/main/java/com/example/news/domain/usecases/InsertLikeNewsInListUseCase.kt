package com.example.news.domain.usecases

import com.example.news.domain.models.NewsEntity
import com.example.news.domain.repository.NewsLikeRepository

class InsertLikeNewsInListUseCase(
    private val repository: NewsLikeRepository
) {
    suspend operator fun invoke(newsEntity: NewsEntity) =
    repository.insertLikeNews(newsEntity)
}