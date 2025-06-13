package com.pb.funora.profile.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pb.funora.profile.model.UserProfile
import com.pb.funora.profile.viewmodel.ProfileViewModel

@Composable
fun ProfileScreen(viewModel: ProfileViewModel, modifier: Modifier = Modifier) {
    val state by viewModel.state.collectAsState()

    Column(modifier = modifier.padding(16.dp)) {
        if (state.isLoading) {
            CircularProgressIndicator()
        }
        state.profile?.let { profile ->
            ProfileContent(profile, onSave = viewModel::updateProfile)
            Spacer(modifier = Modifier.padding(8.dp))
            BadgeRow(profile.badges)
            Spacer(modifier = Modifier.padding(8.dp))
            AvatarPicker(state.suggestions, onAvatarSelected = { url ->
                viewModel.updateProfile(profile.copy(avatarUrl = url))
            }, onGalleryPick = { /*TODO*/ })
        }
        state.error?.let { Text(text = it) }
        Button(onClick = viewModel::fetchProfile, modifier = Modifier.fillMaxWidth()) {
            Text("Refresh")
        }
    }
}

@Composable
private fun ProfileContent(profile: UserProfile, onSave: (UserProfile) -> Unit) {
    var name by remember { mutableStateOf(profile.name) }
    BasicTextField(
        value = name,
        onValueChange = { name = it },
        modifier = Modifier.fillMaxWidth()
    )
    Button(onClick = { onSave(profile.copy(name = name)) }) {
        Text("Save")
    }
}
