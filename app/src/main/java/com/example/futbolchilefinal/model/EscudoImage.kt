package com.example.futbolchilefinal.model

// Represents the image object sent by Xano
data class EscudoImage(
    val path: String? = null
    // Xano might send other fields like name, type, size, etc.
    // but we only need the path for the URL for now.
)
