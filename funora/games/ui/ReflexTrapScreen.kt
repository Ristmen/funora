package com.pb.funora.games.ui

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import kotlinx.coroutines.delay
import kotlin.random.Random
import com.pb.funora.R

// Top-level enum for game state
enum class GameState { NotStarted, Playing, Finished }

@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ReflexTrapScreen(
    totalTimeSec: Int = 30,
    onExit: () -> Unit
) {
    var gameState by remember { mutableStateOf(GameState.NotStarted) }
    var score by remember { mutableStateOf(0) }
    var timeLeft by remember { mutableStateOf(totalTimeSec) }
    var targetOffset by remember { mutableStateOf(Offset.Zero) }
    val targetSize = 56.dp
    val density = LocalDensity.current // Capture density once

    fun spawnTarget(maxW: Dp, maxH: Dp) {
        val xPx = density.run { (maxW - targetSize).toPx() * Random.nextFloat() }
        val yPx = density.run { (maxH - targetSize).toPx() * Random.nextFloat() }
        targetOffset = Offset(xPx, yPx)
    }

    LaunchedEffect(gameState) {
        if (gameState == GameState.Playing) {
            while (timeLeft > 0) {
                delay(1000L)
                timeLeft--
            }
            gameState = GameState.Finished
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(stringResource(R.string.reflex_title)) },
                navigationIcon = {
                    IconButton(onClick = onExit) {
                        Icon(Icons.Default.ArrowBack, contentDescription = null)
                    }
                }
            )
        }
    ) { padding ->
        BoxWithConstraints(
            Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            val maxW = maxWidth
            val maxH = maxHeight

            AnimatedContent(
                targetState = gameState,
                transitionSpec = { fadeIn() with fadeOut() using SizeTransform(false) }
            ) { state ->
                when (state) {
                    GameState.NotStarted -> Column(
                        Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            stringResource(R.string.reflex_instructions),
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(16.dp),
                            textAlign = TextAlign.Center
                        )
                        Button(onClick = {
                            score = 0
                            timeLeft = totalTimeSec
                            spawnTarget(maxW, maxH)
                            gameState = GameState.Playing
                        }) {
                            Text(stringResource(R.string.start_button))
                        }
                    }
                    GameState.Playing -> Box(Modifier.fillMaxSize()) {
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("${stringResource(R.string.score_label)}: $score")
                            Text("${stringResource(R.string.time_left_label)}: $timeLeft")
                        }
                        Box(
                            Modifier
                                .size(targetSize)
                                .offset { IntOffset(targetOffset.x.toInt(), targetOffset.y.toInt()) }
                                .clip(CircleShape)
                                .background(MaterialTheme.colorScheme.primary)
                                .clickable {
                                    score++
                                    spawnTarget(maxW, maxH)
                                }
                        )
                    }
                    GameState.Finished -> Column(
                        Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            stringResource(R.string.game_over_title),
                            style = MaterialTheme.typography.headlineSmall
                        )
                        Spacer(Modifier.height(12.dp))
                        Text(
                            "${stringResource(R.string.your_score)}: $score",
                            style = MaterialTheme.typography.titleLarge
                        )
                        Spacer(Modifier.height(24.dp))
                        Button(onClick = {
                            score = 0
                            timeLeft = totalTimeSec
                            spawnTarget(maxW, maxH)
                            gameState = GameState.Playing
                        }) {
                            Text(stringResource(R.string.retry_button))
                        }
                    }
                }
            }
        }
    }
}

