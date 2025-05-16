// NotificationsScreen.kt
@file:OptIn(ExperimentalMaterial3Api::class)
package com.pb.funora.commonui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDismissState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.pb.funora.R
import com.pb.funora.core.model.NotificationItem

@Composable
private fun SimpleListItemRow(
    headline: @Composable () -> Unit,
    supporting: @Composable () -> Unit = {},
    leading: (@Composable () -> Unit)? = null
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
    }
}

@Composable
fun NotificationsScreen(
    notifications: List<NotificationItem>,
    onMarkAllRead: () -> Unit,
    onDelete: (NotificationItem) -> Unit
) {
    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = { Text(stringResource(R.string.notifications_title)) },
                actions = {
                    TextButton(onClick = onMarkAllRead) {
                        Text(stringResource(R.string.mark_all_read))
                    }
                }
            )
        }
    ) { padding ->
        if (notifications.isEmpty()) {
            Box(
                Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                Text(stringResource(R.string.no_notifications))
            }
        } else {
            LazyColumn(
                Modifier
                    .padding(padding)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(notifications, key = { it.id }) { note ->
                    val state = rememberDismissState(
                        confirmValueChange = { newValue: DismissValue ->
                            if (newValue == DismissValue.DismissedToStart) {
                                onDelete(note)
                                true
                            } else {
                                false
                            }
                        }
                    )

                    SwipeToDismiss(
                        state = state,
                        directions = setOf(DismissDirection.EndToStart),
                        background = {
                            Box(
                                Modifier
                                    .fillMaxSize()
                                    .background(MaterialTheme.colorScheme.error)
                                    .padding(end = 20.dp),
                                contentAlignment = Alignment.CenterEnd
                            ) {
                                Icon(
                                    Icons.Default.Delete,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.onError
                                )
                            }
                        },
                        dismissContent = {
                            SimpleListItemRow(
                                leading = { Icon(note.icon, contentDescription = null) },
                                headline = {
                                    Text(
                                        note.title,
                                        style = MaterialTheme.typography.bodyLarge
                                    )
                                },
                                supporting = {
                                    Text(
                                        note.timeAgo,
                                        style = MaterialTheme.typography.bodySmall
                                    )
                                }
                            )
                        }
                    )

                    Divider()
                }
            }
        }
    }
}
