package com.pb.funora.home.data

import com.pb.funora.home.model.GameCardData
import com.pb.funora.home.model.HomeData
import kotlin.random.Random

actual class HomeService {
    actual suspend fun fetchHomeData(): HomeData {
        val announcements = listOf(
            "Big event coming soon!",
            "Check out the latest tournaments",
            "Don't miss the daily rewards"
        )
        val featuredGames = List(5) { index ->
            GameCardData(
                gameId = "game$index",
                title = "Game Title $index",
                playerCount = Random.nextInt(0, 50),
                maxPlayers = 50
            )
        }
        return HomeData(
            coinBalance = Random.nextLong(0, 10000),
            announcements = announcements,
            featuredGames = featuredGames
        )
    }
}
