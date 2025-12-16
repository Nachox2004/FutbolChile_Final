package com.example.futbolchilefinal.repository

import com.example.futbolchilefinal.model.Clasificacion
import com.example.futbolchilefinal.remote.RetrofitClient

class ClasificacionRepository {
    private val apiService = RetrofitClient.instance

    suspend fun getClasificacion(): List<Clasificacion> {
        return apiService.getClasificacion()
    }
}
