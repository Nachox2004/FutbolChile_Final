package com.example.futbolchilefinal.repository

import com.example.futbolchilefinal.model.User
import com.example.futbolchilefinal.remote.RetrofitClient

class UserRepository {
    private val apiService = RetrofitClient.instance

    suspend fun getUsers(): List<User> {
        return apiService.getUsers()
    }
}
