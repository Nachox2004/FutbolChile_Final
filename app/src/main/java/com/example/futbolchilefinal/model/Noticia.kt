package com.example.futbolchilefinal.model

import androidx.annotation.DrawableRes

// Data class for a news item
data class Noticia(
    val id: Int,
    val titulo: String,
    val resumen: String,
    @DrawableRes val imagen: Int // Image from local drawable resources
)
