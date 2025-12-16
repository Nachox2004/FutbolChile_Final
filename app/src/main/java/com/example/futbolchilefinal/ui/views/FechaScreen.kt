package com.example.futbolchilefinal.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
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
import com.example.futbolchilefinal.model.Equipo
import com.example.futbolchilefinal.model.Fecha
import com.example.futbolchilefinal.viewmodel.EquipoViewModel
import com.example.futbolchilefinal.viewmodel.FechaViewModel

@Composable
fun FechaScreen() {
    val fechaViewModel: FechaViewModel = viewModel()
    val fechas by fechaViewModel.fechas.collectAsState()

    val equipoViewModel: EquipoViewModel = viewModel()
    val equipos by equipoViewModel.equipos.collectAsState()

    LaunchedEffect(Unit) {
        fechaViewModel.fetchFechas()
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

        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            Text(
                "Próximos Partidos",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(24.dp))

            if (fechas.isEmpty() || equipos.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(color = Color.White)
                }
            } else {
                LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(fechas) {
                        val localTeam = teamDataMap[it.equipoLocal]
                        val visitorTeam = teamDataMap[it.equipoVisitante]
                        PartidoCard(fecha = it, localTeam = localTeam, visitorTeam = visitorTeam)
                    }
                }
            }
        }
    }
}

@Composable
fun PartidoCard(fecha: Fecha, localTeam: Equipo?, visitorTeam: Equipo?) {
    val localTeamImageUrl = "https://x8ki-letl-twmt.n7.xano.io${localTeam?.escudo?.path}"
    val visitorTeamImageUrl = "https://x8ki-letl-twmt.n7.xano.io${visitorTeam?.escudo?.path}"

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.Black.copy(alpha = 0.5f))
    ) {
        Column(modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Jornada ${fecha.jornada}", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = Color.White)
            Spacer(modifier = Modifier.height(16.dp))
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceAround) {
                // Local Team
                Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
                    AsyncImage(
                        model = localTeamImageUrl,
                        contentDescription = "Escudo de ${localTeam?.nombre}",
                        modifier = Modifier.size(50.dp).clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(localTeam?.nombre ?: "...", color = Color.White, textAlign = TextAlign.Center)
                }

                Text("vs", modifier = Modifier.padding(horizontal = 8.dp), color = Color.White, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.headlineSmall)

                // Visitor Team
                Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
                    AsyncImage(
                        model = visitorTeamImageUrl,
                        contentDescription = "Escudo de ${visitorTeam?.nombre}",
                        modifier = Modifier.size(50.dp).clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(visitorTeam?.nombre ?: "...", color = Color.White, textAlign = TextAlign.Center)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text("${fecha.diaAJugar} - ${fecha.horaDePartido}", style = MaterialTheme.typography.bodySmall, color = Color.LightGray)
        }
    }
}
