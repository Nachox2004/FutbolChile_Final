package com.example.futbolchilefinal.remote

import com.example.futbolchilefinal.model.Clasificacion
import com.example.futbolchilefinal.model.Equipo
import com.example.futbolchilefinal.model.Fecha
import com.example.futbolchilefinal.model.User
import retrofit2.http.GET

interface ApiService {
    @GET("clasificacion")
    suspend fun getClasificacion(): List<Clasificacion>

    @GET("equipo")
    suspend fun getEquipos(): List<Equipo>

    @GET("fecha")
    suspend fun getFechas(): List<Fecha>

    @GET("user")
    suspend fun getUsers(): List<User>
}
