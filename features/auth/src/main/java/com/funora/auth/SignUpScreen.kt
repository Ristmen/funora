package com.funora.auth

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun SignUpScreen(onBack: () -> Unit, viewModel: AuthViewModel = hiltViewModel()) {
    val state by viewModel.signUpState.collectAsState()
    AuthContent(
        state = state,
        onEmailChange = viewModel::onEmailChange,
        onPasswordChange = viewModel::onPasswordChange,
        onTogglePassword = viewModel::onTogglePassword,
        onAction = {
            viewModel.signUp()
        },
        actionText = "Sign Up",
        alternateAction = onBack
    )
}
