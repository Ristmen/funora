package com.pb.funora.core.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.pb.funora.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InviteFriendsScreen(
    roomId: String,
    navController: NavController,
    onShareRoom: () -> Unit,
    onInviteFriend: () -> Unit,
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = { Text(stringResource(R.string.invite_friends_title)) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = null)
                    }
                }
            )
        }
    ) { padding ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ElevatedButton(onClick = onShareRoom) {
                Icon(Icons.Default.Share, contentDescription = null)
                Spacer(Modifier.width(8.dp))
                // eski R.string.share_room_link yerine R.string.share_button
                Text(stringResource(R.string.share_button))
            }
            ElevatedButton(onClick = onInviteFriend) {
                Icon(Icons.Default.PersonAdd, contentDescription = null)
                Spacer(Modifier.width(8.dp))
                // eski R.string.invite_friend yerine R.string.invite_button
                Text(stringResource(R.string.invite_button))
            }
        }
    }
}
