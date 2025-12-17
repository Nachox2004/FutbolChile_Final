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

    companion object {
        private const val XANO_BASE_URL = "https://x8ki-letl-twmt.n7.xano.io"
    }

    private val _equipos = MutableStateFlow<List<Equipo>>(emptyList())
    val equipos: StateFlow<List<Equipo>> = _equipos

    fun fetchEquipos() {
        viewModelScope.launch {
            try {
                Log.d(TAG, "EquipoViewModel: Iniciando fetchEquipos...")
                val equiposFromRepo = repository.getEquipos()
                val equiposConUrlCompleta = equiposFromRepo.map { equipo ->
                    equipo.copy(
                        escudo = equipo.escudo?.copy(
                            path = equipo.escudo.path?.let { "$XANO_BASE_URL$it" }
                        )
                    )
                }
                _equipos.value = equiposConUrlCompleta
                Log.d(TAG, "ViewModel: fetchEquipos completado con ${_equipos.value.size} items.")
            } catch (e: Exception) {
                Log.e(TAG, "ViewModel: Error fetching equipos: ", e)
            }
        }
    }
}
