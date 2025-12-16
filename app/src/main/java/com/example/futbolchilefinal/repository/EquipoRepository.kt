package com.example.futbolchilefinal.repository

import com.example.futbolchilefinal.model.Equipo
import com.example.futbolchilefinal.remote.RetrofitClient

class EquipoRepository {
    private val apiService = RetrofitClient.instance

    suspend fun getEquipos(): List<Equipo> {
        return apiService.getEquipos()
    }
}
