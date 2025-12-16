package com.example.futbolchilefinal.repository

import com.example.futbolchilefinal.model.Fecha
import com.example.futbolchilefinal.remote.RetrofitClient

class FechaRepository {
    private val apiService = RetrofitClient.instance

    suspend fun getFechas(): List<Fecha> {
        return apiService.getFechas()
    }
}
