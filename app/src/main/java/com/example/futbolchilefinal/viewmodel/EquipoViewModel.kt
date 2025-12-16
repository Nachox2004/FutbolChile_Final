package com.example.futbolchilefinal.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.futbolchilefinal.model.Equipo
import com.example.futbolchilefinal.repository.EquipoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class EquipoViewModel : ViewModel() {

    private val repository = EquipoRepository()
    private val TAG = "FutbolChileApp"

    private val _equipos = MutableStateFlow<List<Equipo>>(emptyList())
    val equipos: StateFlow<List<Equipo>> = _equipos

    fun fetchEquipos() {
        viewModelScope.launch {
            try {
                Log.d(TAG, "EquipoViewModel: Iniciando fetchEquipos...")
                _equipos.value = repository.getEquipos()
                Log.d(TAG, "EquipoViewModel: fetchEquipos completado con ${_equipos.value.size} items.")
            } catch (e: Exception) {
                Log.e(TAG, "EquipoViewModel: Error fetching equipos: ", e)
            }
        }
    }
}
