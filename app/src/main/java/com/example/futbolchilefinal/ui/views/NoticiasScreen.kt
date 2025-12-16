package com.example.futbolchilefinal.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.futbolchilefinal.model.Noticia
import com.example.futbolchilefinal.viewmodel.ClasificacionViewModel
import com.example.futbolchilefinal.viewmodel.EquipoViewModel
import com.example.futbolchilefinal.viewmodel.FechaViewModel
import com.example.futbolchilefinal.viewmodel.NoticiasViewModel

@Composable
fun NoticiasScreen() {
    val noticiasViewModel: NoticiasViewModel = viewModel()
    val noticias by noticiasViewModel.noticias.collectAsState()

    // We need data from other ViewModels to generate news
    val clasificacionViewModel: ClasificacionViewModel = viewModel()
    val clasificacion by clasificacionViewModel.clasificacion.collectAsState()

    val fechaViewModel: FechaViewModel = viewModel()
    val fechas by fechaViewModel.fechas.collectAsState()

    val equipoViewModel: EquipoViewModel = viewModel()
    val equipos by equipoViewModel.equipos.collectAsState()

    // Load all data
    LaunchedEffect(Unit) {
        clasificacionViewModel.fetchClasificacion()
        fechaViewModel.fetchFechas()
        equipoViewModel.fetchEquipos()
    }

    // Generate news only when all data is available
    LaunchedEffect(clasificacion, fechas, equipos) {
        if (clasificacion.isNotEmpty() && fechas.isNotEmpty() && equipos.isNotEmpty()) {
            noticiasViewModel.loadNoticias(clasificacion, fechas, equipos)
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = com.example.futbolchilefinal.R.drawable.campo_futbol),
            contentDescription = "Fondo de campo de fútbol",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        if (noticias.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = Color.White)
            }
        } else {
            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(noticias) { noticia ->
                    NoticiaCard(noticia = noticia)
                }
            }
        }
    }
}

@Composable
fun NoticiaCard(noticia: Noticia) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.Black.copy(alpha = 0.6f)
        )
    ) {
        Column {
            Image(
                painter = painterResource(id = noticia.imagen),
                contentDescription = noticia.titulo,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.padding(16.dp)) {
                Text(noticia.titulo, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold, color = Color.White)
                Spacer(modifier = Modifier.height(8.dp))
                Text(noticia.resumen, style = MaterialTheme.typography.bodyMedium, color = Color.LightGray)
            }
        }
    }
}
