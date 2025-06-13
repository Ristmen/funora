package com.funora.auth

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController

@Composable
fun AuthNavGraph(navController: NavHostController) {
    NavHost(navController, startDestination = "login") {
        authGraph(navController)
    }
}

fun NavGraphBuilder.authGraph(navController: NavHostController) {
    composable("login") {
        LoginScreen(onSignUp = { navController.navigate("signup") })
    }
    composable("signup") {
        SignUpScreen(onBack = { navController.popBackStack() })
    }
}
