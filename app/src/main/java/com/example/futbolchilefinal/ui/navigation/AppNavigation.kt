package com.example.futbolchilefinal.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.futbolchilefinal.ui.views.*
import com.example.futbolchilefinal.viewmodel.AuthViewModel

// Define las rutas de la aplicacion
sealed class Screen(val route: String) {
    object Main : Screen("main")
    object Clasificacion : Screen("clasificacion")
    object Fecha : Screen("fecha")
    object Register : Screen("register")
    object Login : Screen("login")
    object Noticias : Screen("noticias") // Nueva ruta
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val authViewModel: AuthViewModel = viewModel()

    NavHost(navController = navController, startDestination = Screen.Main.route) {
        composable(Screen.Main.route) {
            MainScreen(navController, authViewModel)
        }
        composable(Screen.Clasificacion.route) {
            ClasificacionScreen()
        }
        composable(Screen.Fecha.route) {
            FechaScreen()
        }
        composable(Screen.Register.route) {
            UserRegistrationScreen(navController, authViewModel)
        }
        composable(Screen.Login.route) {
            LoginScreen(navController, authViewModel)
        }
        composable(Screen.Noticias.route) { // Nuevo destino
            NoticiasScreen()
        }
    }
}
