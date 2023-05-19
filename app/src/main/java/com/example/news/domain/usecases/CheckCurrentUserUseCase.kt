package com.example.news.domain.usecases

import com.example.news.domain.repository.RoleModel

class CheckCurrentUserUseCase(
    private val repository: RoleModel
) {
    suspend  operator fun invoke() = repository.checkCurrentUserUseCase()
}