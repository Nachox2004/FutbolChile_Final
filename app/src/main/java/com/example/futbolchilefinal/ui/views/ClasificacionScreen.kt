package com.example.futbolchilefinal.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.futbolchilefinal.R
import com.example.futbolchilefinal.model.Clasificacion
import com.example.futbolchilefinal.model.Equipo
import com.example.futbolchilefinal.viewmodel.ClasificacionViewModel
import com.example.futbolchilefinal.viewmodel.EquipoViewModel

@Composable
fun ClasificacionScreen() {
    val clasificacionViewModel: ClasificacionViewModel = viewModel()
    val clasificacion by clasificacionViewModel.clasificacion.collectAsState()

    val equipoViewModel: EquipoViewModel = viewModel()
    val equipos by equipoViewModel.equipos.collectAsState()

    LaunchedEffect(Unit) {
        clasificacionViewModel.fetchClasificacion()
        equipoViewModel.fetchEquipos()
    }

    val teamDataMap = equipos.associateBy { it.id }

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.campo_futbol),
            contentDescription = "Fondo de campo de fútbol",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(modifier = Modifier.fillMaxSize().padding(top = 40.dp, start = 16.dp, end = 16.dp)) {
            Text(
                "Tabla de Posiciones", 
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Black.copy(alpha = 0.5f))
            ) {
                Text("", modifier = Modifier.width(40.dp).padding(8.dp)) // Empty space for shield
                Text("Equipo", modifier = Modifier.weight(1.5f).padding(8.dp), fontWeight = FontWeight.Bold, color = Color.White)
                Text("PJ", modifier = Modifier.weight(0.5f).padding(8.dp), fontWeight = FontWeight.Bold, color = Color.White)
                Text("Pts", modifier = Modifier.weight(0.5f).padding(8.dp), fontWeight = FontWeight.Bold, color = Color.White)
            }

            if (clasificacion.isEmpty() || equipos.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(color = Color.White)
                }
            } else {
                LazyColumn {
                    items(clasificacion) {
                        val equipo = teamDataMap[it.equipo]
                        ClasificacionRow(equipo = equipo, item = it)
                    }
                }
            }
        }
    }
}

@Composable
fun ClasificacionRow(equipo: Equipo?, item: Clasificacion) {
    val fullImageUrl = "https://x8ki-letl-twmt.n7.xano.io${equipo?.escudo?.path}"

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .background(Color.Black.copy(alpha = 0.3f)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = fullImageUrl,
            contentDescription = "Escudo de ${equipo?.nombre}",
            modifier = Modifier
                .size(40.dp)
                .padding(4.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Text(equipo?.nombre ?: "Cargando...", modifier = Modifier.weight(1.5f).padding(8.dp), color = Color.White)
        Text(item.jornadasDisputadas.toString(), modifier = Modifier.weight(0.5f).padding(8.dp), color = Color.White, textAlign = TextAlign.Center)
        Text(item.puntos.toString(), modifier = Modifier.weight(0.5f).padding(8.dp), color = Color.White, textAlign = TextAlign.Center)
    }
}
