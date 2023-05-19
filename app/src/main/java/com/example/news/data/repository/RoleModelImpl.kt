package com.example.news.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.news.domain.repository.RoleModel
import com.google.firebase.auth.FirebaseAuth

class RoleModelImpl : RoleModel {
    private val auth = FirebaseAuth.getInstance()
    private val authorizationStatusLiveData: MutableLiveData<Boolean> = MutableLiveData()
    private val registerStatusLiveData: MutableLiveData<Boolean> = MutableLiveData()
    private val checkStatusLiveData: MutableLiveData<Boolean> = MutableLiveData()
    private val signOutFromAccountLiveData: MutableLiveData<Boolean> = MutableLiveData()


    override suspend fun authorizationUserUseCase(userEmail: String, userPassword: String) {
        auth.signInWithEmailAndPassword(userEmail, userPassword)
            .addOnCompleteListener {
                authorizationStatusLiveData.value = it.isSuccessful
            }
    }

    override suspend fun registrationNewUserUseCase(userEmail: String, userPassword: String) {
        auth.createUserWithEmailAndPassword(userEmail, userPassword)
            .addOnCompleteListener {
                registerStatusLiveData.value = it.isSuccessful
            }
    }

    override suspend fun checkCurrentUserUseCase() {
        checkStatusLiveData.value = auth.currentUser != null
    }

    override suspend fun signOutFromAccountUseCase() {
        auth.signOut()
        signOutFromAccountLiveData.value = true

    }

    fun getAuthorizationStatusLiveData(): LiveData<Boolean> {
        return authorizationStatusLiveData
    }

    fun getRegisterStatusLiveData(): LiveData<Boolean> {
        return registerStatusLiveData
    }

    fun getCheckStatusLiveData(): LiveData<Boolean> {
        return checkStatusLiveData
    }

    fun getSignOutFromAccountLiveData(): LiveData<Boolean> {
        return signOutFromAccountLiveData
    }
}