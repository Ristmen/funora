package com.pb.funora.chat.ui

import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.animation.animateContentSize
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.pb.funora.R
import com.pb.funora.core.model.ChatMessage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(
    messages: List<ChatMessage>,
    onSendText: (String) -> Unit,
    onRecord: () -> Unit
) {
    var input by remember { mutableStateOf("") }
    val listState = rememberLazyListState()
    Scaffold(
        topBar = { SmallTopAppBar(title = { Text(stringResource(R.string.chat_title)) }) },
        floatingActionButton = {
            FloatingActionButton(onClick = onRecord) {
                Icon(Icons.Default.Mic, contentDescription = null)
            }
        }
    ) { padding ->
        Column(Modifier.padding(padding)) {
            LazyColumn(
                modifier = Modifier.weight(1f),
                reverseLayout = true,
                state = listState
            ) {
                items(messages) { msg ->
                    MessageBubble(msg)
                }
            }
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .animateContentSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { /* emoji panel */ }) {
                    Icon(Icons.Default.EmojiEmotions, contentDescription = null)
                }
                OutlinedTextField(
                    value = input,
                    onValueChange = { input = it },
                    placeholder = { Text(stringResource(R.string.message_hint)) },
                    modifier = Modifier.weight(1f)
                )
                IconButton(
                    onClick = {
                        onSendText(input)
                        input = ""
                    },
                    enabled = input.isNotBlank()
                ) {
                    Icon(Icons.Default.Send, contentDescription = null)
                }
            }
        }
    }
}

@Composable
private fun MessageBubble(msg: ChatMessage) {
    Row(
        horizontalArrangement = if (msg.isMine) Arrangement.End else Arrangement.Start,
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        Surface(
            shape = MaterialTheme.shapes.medium,
            color = if (msg.isMine) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant,
            modifier = Modifier.animateContentSize()
        ) {
            Text(
                text = msg.text,
                modifier = Modifier.padding(12.dp),
                color = if (msg.isMine) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface
            )
        }
    }
}
