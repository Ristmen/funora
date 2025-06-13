package com.pb.funora.auth.navigation

import androidx.compose.runtime.Composable

@Composable
fun AuthNavGraph(
    showLogin: @Composable () -> Unit,
    showSignUp: @Composable () -> Unit
) {
    // Replace with actual navigation logic using your preferred library
    showLogin()
    showSignUp()
}
