// LeaderboardScreen.kt
package com.pb.funora.commonui

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.pb.funora.R
import com.pb.funora.core.model.LeaderboardEntry

/** Basit bir satır görseli: başlık, alt metin, opsiyonel önde/arkada içerik **/
@Composable
private fun SimpleListItemRow(
    headline: @Composable () -> Unit,
    supporting: @Composable () -> Unit = {},
    leading: (@Composable () -> Unit)? = null,
    trailing: (@Composable () -> Unit)? = null
) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (leading != null) {
            Box(Modifier.padding(end = 12.dp)) { leading() }
        }
        Column(modifier = Modifier.weight(1f)) {
            headline()
            Spacer(Modifier.height(4.dp))
            supporting()
        }
        if (trailing != null) {
            Box(Modifier.padding(start = 12.dp)) { trailing() }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LeaderboardScreen(
    entries: List<LeaderboardEntry>,
    onFilterChange: (String) -> Unit
) {
    var selected by remember { mutableStateOf("daily") }
    val timeFrames = listOf(
        "daily" to R.string.daily,
        "weekly" to R.string.weekly,
        "monthly" to R.string.monthly
    )

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(stringResource(R.string.leaderboard_title)) },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors()
            )
        }
    ) { padding ->
        Column(Modifier.padding(padding)) {
            // Filtre butonları
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                timeFrames.forEach { (key, labelRes) ->
                    FilterChip(
                        selected = selected == key,
                        onClick = {
                            selected = key
                            onFilterChange(key)
                        },
                        label = { Text(stringResource(labelRes)) }
                    )
                }
            }

            Spacer(Modifier.height(16.dp))

            // İlk üç lider
            if (entries.size >= 3) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    entries.take(3).forEachIndexed { idx, e ->
                        Card(elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)) {
                            Column(
                                Modifier
                                    .padding(12.dp)
                                    .animateContentSize(),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    "#${idx + 1}",
                                    style = MaterialTheme.typography.bodySmall
                                        .copy(fontWeight = FontWeight.Bold)
                                )
                                Text(e.name, fontWeight = FontWeight.Bold)
                                Text(
                                    text = "${e.score}",
                                    color = MaterialTheme.colorScheme.primary
                                )
                            }
                        }
                    }
                }
                Spacer(Modifier.height(16.dp))
            }

            // Geri kalan liste
            LazyColumn(
                Modifier
                    .fillMaxSize()
                    .padding(horizontal = 8.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                items(entries.drop(3)) { e ->
                    SimpleListItemRow(
                        leading = { Text("#${entries.indexOf(e) + 1}", style = MaterialTheme.typography.bodyMedium) },
                        headline = { Text(e.name, style = MaterialTheme.typography.bodyLarge) },
                        supporting = { Text("${e.score} ${stringResource(R.string.points)}", style = MaterialTheme.typography.bodySmall) }
                    )
                    Divider()
                }
            }
        }
    }
}
