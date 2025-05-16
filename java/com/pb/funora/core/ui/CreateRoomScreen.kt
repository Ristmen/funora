package com.pb.funora.core.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.pb.funora.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateRoomScreen(
    onCreate: (fee: Int, password: String) -> Unit
) {
    var fee by remember { mutableStateOf(0) }
    var password by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text(stringResource(R.string.create_room_title)) })
        }
    ) { padding ->
        Column(
            Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(stringResource(R.string.entry_fee, fee))
            Slider(
                value = fee.toFloat(),
                onValueChange = { fee = it.toInt() },
                valueRange = 0f..500f
            )
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text(stringResource(R.string.room_password_hint)) },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.weight(1f))
            Button(
                onClick = { onCreate(fee, password) },
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.large
            ) {
                Text(stringResource(R.string.create_button))
            }
        }
    }
}
