package com.example.news.domain.usecases

import com.example.news.domain.repository.RoleModel

class RegistrationNewUserUseCase(
    private val repository: RoleModel
) {
    suspend operator fun invoke(userEmail: String, userPassword: String) =
        repository.registrationNewUserUseCase(
            userEmail = userEmail,
            userPassword = userPassword
        )
}