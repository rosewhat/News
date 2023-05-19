package com.example.news.domain.repository

interface RoleModel {
    suspend fun authorizationUserUseCase(userEmail: String, userPassword: String)
    suspend fun registrationNewUserUseCase(userEmail: String, userPassword: String)
    suspend fun signOutFromAccountUseCase()
    suspend fun checkCurrentUserUseCase()
}