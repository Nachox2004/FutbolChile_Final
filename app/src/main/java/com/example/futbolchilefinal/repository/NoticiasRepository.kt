package com.example.futbolchilefinal.repository

import com.example.futbolchilefinal.R
import com.example.futbolchilefinal.model.Clasificacion
import com.example.futbolchilefinal.model.Equipo
import com.example.futbolchilefinal.model.Fecha
import com.example.futbolchilefinal.model.Noticia

class NoticiasRepository {

    fun getNoticias(clasificacion: List<Clasificacion>, fechas: List<Fecha>, equipos: List<Equipo>): List<Noticia> {
        if (clasificacion.isEmpty() || equipos.isEmpty()) {
            return emptyList()
        }

        val teamMap = equipos.associateBy { it.id }
        val noticias = mutableListOf<Noticia>()

        // 1. Noticia del Líder
        val lider = clasificacion.maxByOrNull { it.puntos }
        if (lider != null) {
            val nombreLider = teamMap[lider.equipo]?.nombre ?: "El puntero"
            noticias.add(
                Noticia(
                    id = 1,
                    titulo = "¡${nombreLider} no cede la punta!",
                    resumen = "Con ${lider.puntos} puntos, ${nombreLider} se mantiene como el sólido líder del campeonato, demostrando una consistencia impresionante.",
                    imagen = R.drawable.campo_futbol
                )
            )
        }

        // 2. Noticia de un partido importante de la próxima fecha
        val proximaFecha = fechas.firstOrNull()
        if (proximaFecha != null) {
            val local = teamMap[proximaFecha.equipoLocal]?.nombre ?: "Equipo Local"
            val visitante = teamMap[proximaFecha.equipoVisitante]?.nombre ?: "Equipo Visitante"
            noticias.add(
                Noticia(
                    id = 2,
                    titulo = "Duelo de titanes: ${local} vs. ${visitante}",
                    resumen = "La próxima jornada nos trae un partido imperdible que podría ser clave para las aspiraciones de ambos equipos.",
                    imagen = R.drawable.campo_futbol
                )
            )
        }
        
        // 3. Noticia sobre la lucha en la parte alta
        if (clasificacion.size >= 2) {
             val segundo = clasificacion.sortedByDescending { it.puntos }[1]
             val nombreSegundo = teamMap[segundo.equipo]?.nombre ?: "El escolta"
             noticias.add(
                Noticia(
                    id = 3,
                    titulo = "La pelea por el segundo puesto está que arde",
                    resumen = "Con ${segundo.puntos} puntos, ${nombreSegundo} no le pierde pisada al líder y promete luchar hasta el final.",
                    imagen = R.drawable.campo_futbol
                )
            )
        }

        // 4. Noticia sobre la zona de descenso
         val colista = clasificacion.minByOrNull { it.puntos }
         if (colista != null) {
            val nombreColista = teamMap[colista.equipo]?.nombre ?: "El colista"
            noticias.add(
                Noticia(
                    id = 4,
                    titulo = "Lucha agónica en la parte baja de la tabla",
                    resumen = "La batalla por no descender está al rojo vivo. ${nombreColista} necesita sumar puntos con urgencia para salir del fondo.",
                    imagen = R.drawable.campo_futbol
                )
            )
        }

        return noticias
    }
}
