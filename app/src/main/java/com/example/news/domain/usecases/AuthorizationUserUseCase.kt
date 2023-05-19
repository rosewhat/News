package com.example.news.domain.usecases

import com.example.news.domain.repository.RoleModel

class AuthorizationUserUseCase(
    private val repository: RoleModel
) {
    suspend operator fun invoke(userEmail: String, userPassword: String) =
        repository.authorizationUserUseCase(
            userEmail = userEmail,
            userPassword = userPassword
        )
}