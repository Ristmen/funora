// app/src/main/java/com/pb/funora/auth/ui/ForgotPasswordScreen.kt
package com.pb.funora.auth.ui

import android.widget.Toast
import androidx.compose.foundation.layout.*                    // Column, Spacer, fillMaxSize, padding
import androidx.compose.animation.animateContentSize  // Modifier.animateContentSize()
import androidx.compose.material3.*                          // Scaffold, Button, Text, vs.
import androidx.compose.material.icons.Icons                 // Icons
import androidx.compose.material.icons.filled.ArrowBack       // ArrowBack ikonu
import androidx.compose.runtime.*                            // @Composable, remember, mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.foundation.text.KeyboardOptions         // keyboardOptions için
import androidx.compose.ui.text.input.KeyboardType           // KeyboardType.Email
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import com.pb.funora.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForgotPasswordScreen(
    onSendReset: (email: String) -> Unit,
    onBack: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = context.getString(R.string.forgot_pwd_title)) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector        = Icons.Filled.ArrowBack,
                            contentDescription = context.getString(R.string.back)
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(24.dp)
                .animateContentSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text      = context.getString(R.string.enter_email),
                style     = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.height(16.dp))

            OutlinedTextField(
                value           = email,
                onValueChange   = { email = it },
                label           = { Text(text = context.getString(R.string.email_label)) },
                singleLine      = true,
                modifier        = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            )
            Spacer(Modifier.height(24.dp))

            Button(
                onClick = {
                    if (email.isBlank()) {
                        scope.launch {
                            Toast
                                .makeText(
                                    context,
                                    context.getString(R.string.enter_valid_email),
                                    Toast.LENGTH_SHORT
                                )
                                .show()
                        }
                    } else {
                        onSendReset(email)
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = context.getString(R.string.send_button))
            }
        }
    }
}
