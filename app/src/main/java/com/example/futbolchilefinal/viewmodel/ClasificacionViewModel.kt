package com.example.futbolchilefinal.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.futbolchilefinal.model.Clasificacion
import com.example.futbolchilefinal.repository.ClasificacionRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ClasificacionViewModel : ViewModel() {

    private val repository = ClasificacionRepository()
    private val TAG = "FutbolChileApp"

    private val _clasificacion = MutableStateFlow<List<Clasificacion>>(emptyList())
    val clasificacion: StateFlow<List<Clasificacion>> = _clasificacion

    fun fetchClasificacion() {
        viewModelScope.launch {
            try {
                Log.d(TAG, "ClasificacionViewModel: Iniciando fetchClasificacion...")
                _clasificacion.value = repository.getClasificacion()
                Log.d(TAG, "ClasificacionViewModel: fetchClasificacion completado con ${_clasificacion.value.size} items.")
            } catch (e: Exception) {
                Log.e(TAG, "ClasificacionViewModel: Error fetching clasificacion: ", e)
            }
        }
    }
}
