package com.example.futbolchilefinal.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.futbolchilefinal.model.Fecha
import com.example.futbolchilefinal.repository.FechaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FechaViewModel : ViewModel() {

    private val repository = FechaRepository()

    private val _fechas = MutableStateFlow<List<Fecha>>(emptyList())
    val fechas: StateFlow<List<Fecha>> = _fechas

    fun fetchFechas() {
        viewModelScope.launch {
            try {
                _fechas.value = repository.getFechas()
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
}
