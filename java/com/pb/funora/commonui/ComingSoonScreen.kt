package com.pb.funora.commonui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.pb.funora.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComingSoonScreen() {
    Scaffold(
        topBar = { SmallTopAppBar(title = { Text(stringResource(R.string.coming_soon_title)) }) }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentAlignment = Alignment.Center
        ) {
            Text(
                stringResource(R.string.coming_soon_message),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}
