package com.example.futbolchilefinal.model

import com.google.gson.annotations.SerializedName
import java.sql.Timestamp

data class Clasificacion(
    val id: Int,
    @SerializedName("created_at")
    val createdAt: Long,
    val equipo: Int,
    @SerializedName("jornadas_disputadas")
    val jornadasDisputadas: Int,
    val puntos: Int
)
