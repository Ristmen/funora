package com.pb.funora.currency.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

@Composable
fun StakeDialog(onConfirm: (Long) -> Unit, onCancel: () -> Unit) {
    val amount = remember { mutableStateOf("") }

    Column {
        OutlinedTextField(value = amount.value, onValueChange = { amount.value = it }, label = { Text("Amount") })
        Row {
            Button(onClick = { amount.value.toLongOrNull()?.let(onConfirm) }) {
                Text("Confirm")
            }
            Button(onClick = onCancel) {
                Text("Cancel")
            }
        }
    }
}
