package ru.netology.andad29.viewmodel

import android.app.Application
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.netology.andad29.auth.AppAuth
import ru.netology.andad29.auth.AuthState
import ru.netology.andad29.db.AppDb
import ru.netology.andad29.repository.PostRepository
import ru.netology.andad29.repository.PostRepositoryImpl
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor (
    private val auth: AppAuth,
    private val repository: PostRepository
    ) : ViewModel() {

    val data: LiveData<AuthState> = auth.authStateFlow
        .asLiveData(Dispatchers.Default)
    val authenticated: Boolean
        get() = auth.authStateFlow.value.id != 0L

    fun authentication(login: String, pass: String) {
        viewModelScope.launch {
            repository.authentication(login, pass)
        }
    }

    fun registration(name: String, login: String, pass: String) {
        viewModelScope.launch {
            repository.registration(name, login, pass)
        }
    }

}