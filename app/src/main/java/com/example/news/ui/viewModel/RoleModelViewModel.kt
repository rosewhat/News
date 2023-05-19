package com.example.news.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.data.repository.RoleModelImpl
import kotlinx.coroutines.launch

class RoleModelViewModel : ViewModel() {
    private val repository = RoleModelImpl()

    private val authorizationStatusLiveData: LiveData<Boolean> =
        repository.getAuthorizationStatusLiveData()

    private val checkStatusLiveData: LiveData<Boolean> =
        repository.getCheckStatusLiveData()

    private val registerStatusLiveData: LiveData<Boolean> =
        repository.getRegisterStatusLiveData()

    private val signOutFromAccountLiveData: LiveData<Boolean> =
        repository.getSignOutFromAccountLiveData()

    fun getSignOutFromAccountLiveData(): LiveData<Boolean> {
        return signOutFromAccountLiveData
    }

    fun getRegisterStatusLiveData(): LiveData<Boolean> {
        return registerStatusLiveData
    }

    fun getAuthorizationStatusLiveData(): LiveData<Boolean> {
        return authorizationStatusLiveData
    }

    fun getCheckStatusLiveData(): LiveData<Boolean> {
        return checkStatusLiveData
    }

    fun authorizationUser(userEmail: String, userPassword: String) {
        viewModelScope.launch {
            repository.authorizationUserUseCase(userEmail, userPassword)
        }
    }

    fun registrationNewUser(userEmail: String, userPassword: String) {
        viewModelScope.launch {
            repository.registrationNewUserUseCase(userEmail, userPassword)
        }
    }

    fun checkCurrentUser() {
        viewModelScope.launch {
            repository.checkCurrentUserUseCase()
        }
    }

    fun signOutFromAccount() {
        viewModelScope.launch {
            repository.signOutFromAccountUseCase()
        }
    }
}