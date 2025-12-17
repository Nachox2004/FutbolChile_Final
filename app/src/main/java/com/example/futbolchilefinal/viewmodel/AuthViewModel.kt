package com.example.futbolchilefinal.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.futbolchilefinal.repository.SessionManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

data class UserInfo(val name: String, val favoriteTeam: String, val favoriteTeamLogo: String)

class AuthViewModel(application: Application) : AndroidViewModel(application) {

    private val sessionManager = SessionManager(application)

    private val _currentUser = MutableStateFlow<UserInfo?>(null)
    val currentUser: StateFlow<UserInfo?> = _currentUser

    init {
        viewModelScope.launch {
            _currentUser.value = sessionManager.sessionFlow.first()
        }
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            // Simulate network call for login
            if (email == "test@test.com" && password == "123456") {
                val userInfo = UserInfo(
                    name = "Usuario",
                    favoriteTeam = "Equipo de Prueba",
                    favoriteTeamLogo = "https://firebasestorage.googleapis.com/v0/b/futbolchile-ed33f.appspot.com/o/images%2Fudeconce.png?alt=media&token=383182f7-1339-433b-8224-b552d0a0d9e7"
                )
                sessionManager.saveSession(userInfo)
                _currentUser.value = userInfo
            }
        }
    }

    fun register(email: String, password: String, name: String, favoriteTeam: String, favoriteTeamLogo: String) {
        viewModelScope.launch {
             if (email.isNotEmpty() && password.isNotEmpty() && name.isNotEmpty()) {
                val userInfo = UserInfo(name = name, favoriteTeam = favoriteTeam, favoriteTeamLogo = favoriteTeamLogo)
                sessionManager.saveSession(userInfo)
                _currentUser.value = userInfo
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            sessionManager.clearSession()
            _currentUser.value = null
        }
    }
}
