package com.pb.funora.profile.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.pb.funora.profile.model.AvatarSuggestion

@Composable
fun AvatarPicker(
    suggestions: List<AvatarSuggestion>,
    onAvatarSelected: (String) -> Unit,
    onGalleryPick: () -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(suggestions) { suggestion ->
            Image(
                painter = painterResource(""), // placeholder
                contentDescription = suggestion.description,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clickable { onAvatarSelected(suggestion.url) }
            )
        }
        item {
            IconButton(onClick = onGalleryPick) {
                Icon(
                    painterResource(""), // placeholder
                    contentDescription = "Gallery",
                    tint = Color.Gray
                )
            }
        }
    }
}
