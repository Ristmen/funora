package com.pb.funora.commonui

import androidx.compose.ui.unit.sp
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.pb.funora.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutLegalScreen(
    version: String,
    onOpenPolicy: (String) -> Unit
) {
    Scaffold(
        topBar = { SmallTopAppBar(title = { Text(stringResource(R.string.about_title)) }) }
    ) { padding ->
        Column(
            Modifier
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(24.dp)
        ) {
            Text(stringResource(R.string.version_template, version), style = MaterialTheme.typography.titleLarge)
            Spacer(Modifier.height(16.dp))
            Text(stringResource(R.string.about_description), style = MaterialTheme.typography.bodyMedium, lineHeight = 20.sp)
            Spacer(Modifier.height(24.dp))
            TextButton(onClick = { onOpenPolicy("privacy") }) {
                Text(stringResource(R.string.privacy_policy))
            }
            TextButton(onClick = { onOpenPolicy("terms") }) {
                Text(stringResource(R.string.terms_of_service))
            }
        }
    }
}
