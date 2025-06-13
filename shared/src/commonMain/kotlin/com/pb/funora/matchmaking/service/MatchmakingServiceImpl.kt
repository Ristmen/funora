package com.pb.funora.matchmaking.service

import com.pb.funora.matchmaking.model.MatchRequest
import com.pb.funora.matchmaking.model.MatchResult
import com.pb.funora.matchmaking.model.PlayerInfo
import kotlinx.coroutines.delay
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlin.random.Random

class MatchmakingServiceImpl : MatchmakingService {
    private val queue = mutableListOf<MatchRequest>()
    private val mutex = Mutex()

    override suspend fun findMatch(request: MatchRequest): MatchResult {
        mutex.withLock {
            val opponentIndex = queue.indexOfFirst { it.gameId == request.gameId && it.playerId != request.playerId }
            if (opponentIndex != -1) {
                val opponentReq = queue.removeAt(opponentIndex)
                val opponentInfo = PlayerInfo(opponentReq.playerId, "Player ${'$'}{opponentReq.playerId}", 1000, false)
                return MatchResult(true, opponentInfo, null)
            }
            queue.add(request)
        }

        val deadline = request.timestamp + 5000
        while (System.currentTimeMillis() < deadline) {
            mutex.withLock {
                val opponentIndex = queue.indexOfFirst { it.gameId == request.gameId && it.playerId != request.playerId }
                if (opponentIndex != -1) {
                    queue.remove(request)
                    val opponentReq = queue.removeAt(opponentIndex)
                    val opponentInfo = PlayerInfo(opponentReq.playerId, "Player ${'$'}{opponentReq.playerId}", 1000, false)
                    return MatchResult(true, opponentInfo, null)
                }
            }
            delay(500)
        }

        mutex.withLock { queue.remove(request) }
        val botInfo = PlayerInfo("bot_${'$'}{Random.nextInt(1000, 9999)}", "Bot", 1000, true)
        return MatchResult(true, botInfo, null)
    }

    override suspend fun cancelMatch(request: MatchRequest) {
        mutex.withLock {
            queue.removeIf { it.playerId == request.playerId && it.gameId == request.gameId }
        }
    }
}
