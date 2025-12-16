package com.example.futbolchilefinal.viewmodel

import androidx.lifecycle.ViewModel
import com.example.futbolchilefinal.model.Clasificacion
import com.example.futbolchilefinal.model.Equipo
import com.example.futbolchilefinal.model.Fecha
import com.example.futbolchilefinal.model.Noticia
import com.example.futbolchilefinal.repository.NoticiasRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class NoticiasViewModel : ViewModel() {

    private val repository = NoticiasRepository()

    private val _noticias = MutableStateFlow<List<Noticia>>(emptyList())
    val noticias: StateFlow<List<Noticia>> = _noticias

    // Updated function to accept live data
    fun loadNoticias(clasificacion: List<Clasificacion>, fechas: List<Fecha>, equipos: List<Equipo>) {
        _noticias.value = repository.getNoticias(clasificacion, fechas, equipos)
    }
}
