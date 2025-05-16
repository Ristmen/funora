package com.pb.funora.ui

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.pb.funora.R
import com.pb.funora.commonui.model.User

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FriendsScreen(
    onInvite: (User) -> Unit,
    friends: List<User>
) {
    var query by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            SmallTopAppBar(title = { Text(stringResource(R.string.friends_title)) })
        }
    ) { padding ->
        Column(Modifier.padding(padding)) {
            OutlinedTextField(
                value = query,
                onValueChange = { query = it },
                placeholder = { Text(stringResource(R.string.search_hint)) },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(onSearch = { /* TODO: klavyoyu gizle */ }),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )

            val filtered = friends.filter { it.name.contains(query, ignoreCase = true) }
            if (filtered.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(R.string.empty_friends),
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.padding(16.dp)
                ) {
                    items(filtered) { user ->
                        ElevatedCard(
                            onClick = { onInvite(user) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .animateContentSize(),
                            shape = MaterialTheme.shapes.medium
                        ) {
                            Row(
                                modifier = Modifier.padding(12.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(
                                    painter = painterResource(user.avatarResId),
                                    contentDescription = user.name,
                                    modifier = Modifier
                                        .size(48.dp)
                                        .clip(CircleShape)
                                )
                                Spacer(modifier = Modifier.width(12.dp))
                                Text(
                                    text = user.name,
                                    modifier = Modifier.weight(1f)
                                )
                                TextButton(onClick = { onInvite(user) }) {
                                    Text(stringResource(R.string.invite_button))
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
