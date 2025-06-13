package com.funora.auth

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun LoginScreen(onSignUp: () -> Unit, viewModel: AuthViewModel = hiltViewModel()) {
    val state by viewModel.loginState.collectAsState()
    AuthContent(
        state = state,
        onEmailChange = viewModel::onEmailChange,
        onPasswordChange = viewModel::onPasswordChange,
        onTogglePassword = viewModel::onTogglePassword,
        onAction = viewModel::login,
        actionText = "Login",
        alternateAction = onSignUp
    )
}

@Composable
fun AuthContent(
    state: AuthState,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onTogglePassword: () -> Unit,
    onAction: () -> Unit,
    actionText: String,
    alternateAction: () -> Unit
) {
    Scaffold { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = state.email,
                onValueChange = onEmailChange,
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(8.dp))
            OutlinedTextField(
                value = state.password,
                onValueChange = onPasswordChange,
                label = { Text("Password") },
                trailingIcon = {
                    IconButton(onClick = onTogglePassword) {
                        val image = if (state.passwordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility
                        Icon(image, contentDescription = null)
                    }
                },
                visualTransformation = if (state.passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(16.dp))
            Button(onClick = onAction, modifier = Modifier.fillMaxWidth()) {
                Text(actionText)
            }
            TextButton(onClick = alternateAction, modifier = Modifier.fillMaxWidth()) {
                Text("Switch")
            }
            state.message?.let { msg ->
                Snackbar { Text(msg) }
            }
        }
    }
}
