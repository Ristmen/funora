package com.pb.funora.home.model

import kotlinx.serialization.Serializable

@Serializable
data class HomeData(
    val coinBalance: Long,
    val announcements: List<String>,
    val featuredGames: List<GameCardData>
)
