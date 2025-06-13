package com.pb.funora.games.okey.engine

import com.pb.funora.games.okey.model.GameState
import com.pb.funora.games.okey.model.Tile
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlin.random.Random

/**
 * Simple in-memory game engine managing the deck and turns.
 */
class OkeyEngine {
    private val mutex = Mutex()
    private val deck = mutableListOf<Tile>()
    private val players = mutableMapOf<Int, MutableList<Tile>>()
    private val tilesOnTable = mutableListOf<Tile>()
    private var currentPlayer: Int = 0

    init {
        initDeck()
    }

    private fun initDeck() {
        deck.clear()
        TileColor.values().forEach { color ->
            for (i in 1..13) {
                deck += Tile("${color.name}_$i", color, i)
            }
        }
        deck.shuffle(Random(System.currentTimeMillis()))
    }

    suspend fun dealTiles(playerCount: Int = 4) = mutex.withLock {
        players.clear()
        repeat(playerCount) { player ->
            players[player] = mutableListOf()
            repeat(14) {
                players[player]!!.add(deck.removeAt(0))
            }
        }
        currentPlayer = 0
    }

    suspend fun playTile(tile: Tile) = mutex.withLock {
        tilesOnTable.add(tile)
        players[currentPlayer]?.remove(tile)
        currentPlayer = (currentPlayer + 1) % players.size
    }

    suspend fun drawTile(): Tile = mutex.withLock {
        val tile = deck.removeAt(0)
        players[currentPlayer]?.add(tile)
        tile
    }

    suspend fun getState(): GameState = mutex.withLock {
        GameState(
            tilesOnHand = players[currentPlayer]?.toList() ?: emptyList(),
            tilesOnTable = tilesOnTable.toList(),
            currentPlayer = currentPlayer
        )
    }
}
