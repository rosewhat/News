package com.example.news.domain.usecases

import com.example.news.domain.repository.NewsLikeRepository

class GetLikeNewsUseCase(
    private val repository: NewsLikeRepository
) {
    operator fun invoke() = repository.getLikeNewsUseCase()
}