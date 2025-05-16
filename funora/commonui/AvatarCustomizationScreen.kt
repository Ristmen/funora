package com.pb.funora.commonui

import com.pb.funora.commonui.model.AvatarOption
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.pb.funora.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AvatarCustomizationScreen(
    parts: Map<String, List<AvatarOption>>,
    onOptionSelected: (String, AvatarOption) -> Unit
) {
    var selectedTab by remember { mutableStateOf(parts.keys.first()) }
    Scaffold(
        topBar = { CenterAlignedTopAppBar(title = { Text(stringResource(R.string.avatar_customize_title)) }) }
    ) { padding ->
        Column(Modifier.padding(padding)) {
            TabRow(selectedTabIndex = parts.keys.indexOf(selectedTab)) {
                parts.keys.forEach { cat ->
                    Tab(selected = cat == selectedTab, onClick = { selectedTab = cat }) {
                        Text(cat)
                    }
                }
            }
            Spacer(Modifier.height(12.dp))
            LazyRow(
                Modifier.fillMaxWidth().padding(start = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(parts[selectedTab] ?: emptyList()) { opt ->
                    ElevatedCard(
                        onClick = { onOptionSelected(selectedTab, opt) },
                        modifier = Modifier
                            .size(100.dp)
                            .animateContentSize(),
                        shape = CircleShape
                    ) {
                        Image(
                            painterResource(opt.resId),
                            contentDescription = opt.name,
                            modifier = Modifier.fillMaxSize().padding(12.dp)
                        )
                    }
                }
            }
        }
    }
}
