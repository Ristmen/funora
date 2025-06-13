package com.pb.funora.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavGraph(navController: NavHostController, startDestination: String = "home") {
    NavHost(navController = navController, startDestination = startDestination) {
        composable("home") { /* Home Screen */ }
        composable("profile") { /* Profile Screen */ }
    }
}
