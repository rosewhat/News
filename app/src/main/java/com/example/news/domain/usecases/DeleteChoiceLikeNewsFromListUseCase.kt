package com.example.news.domain.usecases

import com.example.news.domain.models.NewsEntity
import com.example.news.domain.repository.NewsLikeRepository

class DeleteChoiceLikeNewsFromListUseCase(
    private val repository: NewsLikeRepository
) {
    suspend operator fun invoke(newsEntity: NewsEntity) =
        repository.deleteChoiceLikeNewsFromListUseCase(newsEntity)
}