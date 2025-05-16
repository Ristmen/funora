// TournamentsScreen.kt
package com.pb.funora.games.ui

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.pb.funora.R
import com.pb.funora.core.model.Tournament

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TournamentsScreen(
    tournaments: List<Tournament>,
    onRegister: (Tournament) -> Unit,
    onCreateNew: () -> Unit
) {
    Scaffold(
        topBar = { CenterAlignedTopAppBar(title = { Text(stringResource(R.string.tournaments_title)) }) },
        floatingActionButton = {
            FloatingActionButton(onClick = onCreateNew) {
                Icon(Icons.Default.Add, contentDescription = null)
            }
        }
    ) { padding ->
        LazyColumn(
            Modifier
                .padding(padding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(tournaments) { t ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .animateContentSize(),
                    shape = MaterialTheme.shapes.medium
                ) {
                    Column(Modifier.padding(16.dp)) {
                        Text(t.name, fontWeight = FontWeight.Bold)
                        Text("${t.date} • ${t.participants} ${stringResource(R.string.participants)}")
                        Spacer(Modifier.height(8.dp))
                        Button(onClick = { onRegister(t) }) {
                            Text(stringResource(R.string.register_button))
                        }
                    }
                }
            }
        }
    }
}
