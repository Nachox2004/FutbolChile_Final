package com.example.futbolchilefinal.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.futbolchilefinal.R
import com.example.futbolchilefinal.ui.navigation.Screen
import com.example.futbolchilefinal.viewmodel.AuthViewModel

@Composable
fun MainScreen(navController: NavController, authViewModel: AuthViewModel) {
    val currentUser by authViewModel.currentUser.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.campo_futbol),
            contentDescription = "Fondo de campo de fútbol",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 48.dp, start = 16.dp, end = 16.dp),
            horizontalArrangement = if (currentUser != null) Arrangement.SpaceBetween else Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            currentUser?.let { user ->
                Column(horizontalAlignment = Alignment.Start) {
                    Text("Hola buenas ${user.name} :} ", color = Color.White, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(8.dp))
                    Image(
                        painter = rememberAsyncImagePainter(user.favoriteTeamLogo),
                        contentDescription = "Logo del equipo favorito",
                        modifier = Modifier.size(64.dp)
                    )
                }
                Button(
                    onClick = { authViewModel.logout() },
                    shape = RoundedCornerShape(50),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black.copy(alpha = 0.8f),
                        contentColor = Color.White
                    )
                ) {
                    Text("Cerrar Sesión")
                }
            } ?: run {
                Button(
                    onClick = { navController.navigate(Screen.Login.route) },
                    shape = RoundedCornerShape(50),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black.copy(alpha = 0.8f),
                        contentColor = Color.White
                    )
                ) {
                    Text("Iniciar Sesión")
                }
            }
        }

        Column(
            modifier = Modifier.fillMaxSize().padding(bottom = 100.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "FutbolChile",
                fontSize = 56.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                style = TextStyle(
                    shadow = Shadow(
                        color = Color.Black.copy(alpha = 0.5f),
                        offset = Offset(4f, 4f),
                        blurRadius = 8f
                    )
                )
            )
            Spacer(modifier = Modifier.height(48.dp))

            val buttonColors = ButtonDefaults.buttonColors(
                containerColor = Color.Black.copy(alpha = 0.8f),
                contentColor = Color.White
            )
            val buttonShape = RoundedCornerShape(50)

            Button(
                onClick = { navController.navigate(Screen.Clasificacion.route) },
                shape = buttonShape,
                colors = buttonColors,
                modifier = Modifier.width(200.dp)
            ) {
                Text(text = "Clasificación", fontSize = 16.sp)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { navController.navigate(Screen.Fecha.route) },
                shape = buttonShape,
                colors = buttonColors,
                modifier = Modifier.width(150.dp)
            ) {
                Text(text = "Fecha", fontSize = 16.sp)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { navController.navigate(Screen.Noticias.route) }, // New button navigation
                shape = buttonShape,
                colors = buttonColors,
                modifier = Modifier.width(200.dp)
            ) {
                Text(text = "Noticias", fontSize = 16.sp)
            }
        }
    }
}
