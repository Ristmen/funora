@file:OptIn(ExperimentalMaterial3Api::class)

package com.pb.funora.core.ui

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Inbox
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.pb.funora.R

// Models
data class User(val name: String, val avatarResId: Int, val coin: Int)
data class UserAction(val icon: ImageVector, val label: String, val route: String)
data class Game(val id: Int, val name: String, val imageResId: Int, val colors: List<Color>)

@Composable
fun HomeScreen(navController: NavController) {
    val currentUser = User("Oyuncu Adı", R.drawable.ic_logo, 46)
    val userActions = listOf(
        UserAction(Icons.Filled.People, "Arkadaşlar", "friends"),
        UserAction(Icons.Filled.Chat, "Sohbet", "chat"),
        UserAction(Icons.Filled.Inbox, "Coin", "coin"),
        UserAction(Icons.Filled.AccountCircle, "Profil", "profile")
    )
    val games = listOf(
        Game(1, "Tavla", R.drawable.ic_backgammon, listOf(Color(0xFF4CAF50), Color(0xFF8BC34A))),
        Game(2, "Satranç", R.drawable.ic_chess, listOf(Color(0xFFD32F2F), Color(0xFFE57373))),
        Game(3, "Batak", R.drawable.ic_spades, listOf(Color(0xFF673AB7), Color(0xFF9575CD))),
        Game(4, "Refleks", R.drawable.ic_reflex, listOf(Color(0xFF1E88E5), Color(0xFF64B5F6))),
        Game(5, "Okey", R.drawable.ic_okey, listOf(Color(0xFF8BC34A), Color(0xFFC5E1A5))),
        Game(6, "Kelime Avcısı", R.drawable.ic_word_hunter, listOf(Color(0xFFFFA000), Color(0xFFFFB74D)))
    )

    Scaffold(
        topBar = { MainTopAppBar(user = currentUser) },
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            SearchBar()
            Spacer(modifier = Modifier.height(16.dp))
            ActionCarousel(navController, userActions)
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Oyunlar",
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.height(12.dp))
            Box(modifier = Modifier.weight(1f)) {
                GameGrid(games = games)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopAppBar(user: User) {
    CenterAlignedTopAppBar(
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.ic_logo),
                    contentDescription = "Funora Logo",
                    modifier = Modifier.size(32.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "FUNORA",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                )
            }
        },
        actions = {
            IconWithBadge(icon = Icons.Filled.Inbox, badgeCount = user.coin)
            IconButton(onClick = { /* navigate to purchase */ }) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "Add Coin")
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.surface,
            scrolledContainerColor = MaterialTheme.colorScheme.surface
        )
    )
}

@Composable
fun IconWithBadge(icon: ImageVector, badgeCount: Int) {
    BadgedBox(
        badge = {
            if (badgeCount > 0) Badge { Text(badgeCount.toString()) }
        }
    ) {
        Icon(imageVector = icon, contentDescription = "Coin", modifier = Modifier.size(24.dp))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar() {
    var query by remember { mutableStateOf(TextFieldValue("")) }
    OutlinedTextField(
        value = query,
        onValueChange = { query = it },
        placeholder = { Text("Oyunu ara...") },
        leadingIcon = { Icon(imageVector = Icons.Filled.Search, contentDescription = null) },
        singleLine = true,
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .shadow(2.dp, RoundedCornerShape(12.dp))
    )
}

@Composable
fun ActionCarousel(navController: NavController, actions: List<UserAction>) {
    LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        items(actions) { action ->
            ElevatedCard(
                shape = CircleShape,
                colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.primary),
                modifier = Modifier
                    .size(72.dp)
                    .clickable { navController.navigate(action.route) }
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = action.icon,
                        contentDescription = action.label,
                        tint = Color.White,
                        modifier = Modifier.size(32.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun GameGrid(games: List<Game>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(bottom = 16.dp),
        modifier = Modifier.animateContentSize()
    ) {
        items(games, key = { it.id }) { game ->
            ElevatedCard(
                shape = RoundedCornerShape(24.dp),
                elevation = CardDefaults.elevatedCardElevation(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
            ) {
                Box(
                    modifier = Modifier
                        .background(
                            brush = Brush.linearGradient(
                                colors = game.colors,
                                start = Offset.Zero,
                                end = Offset(100f, 100f)
                            )
                        )
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = painterResource(id = game.imageResId),
                            contentDescription = game.name,
                            modifier = Modifier.size(72.dp),
                            contentScale = ContentScale.Fit
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = game.name,
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            ),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }
        }
    }
}
