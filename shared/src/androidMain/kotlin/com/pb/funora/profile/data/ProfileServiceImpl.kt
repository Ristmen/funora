package com.pb.funora.profile.data

import com.pb.funora.profile.model.AvatarSuggestion
import com.pb.funora.profile.model.UserProfile
import kotlinx.coroutines.delay

class ProfileServiceImpl : ProfileService {
    private var storedProfile = UserProfile(
        id = "1",
        name = "Guest",
        avatarUrl = "https://example.com/avatar/default.png",
        countryCode = "US",
        badges = emptyList()
    )

    private val avatarsByCountry = mapOf(
        "US" to listOf(
            AvatarSuggestion("https://example.com/avatar/us1.png", "Star"),
            AvatarSuggestion("https://example.com/avatar/us2.png", "Stripe")
        ),
        "TR" to listOf(
            AvatarSuggestion("https://example.com/avatar/tr1.png", "Moon"),
            AvatarSuggestion("https://example.com/avatar/tr2.png", "Sun")
        )
    )

    override suspend fun fetchProfile(): UserProfile {
        delay(300) // simulate network
        return storedProfile
    }

    override suspend fun updateProfile(profile: UserProfile): UserProfile {
        delay(300)
        storedProfile = profile
        return storedProfile
    }

    override suspend fun fetchAvatarSuggestions(countryCode: String): List<AvatarSuggestion> {
        delay(300)
        return avatarsByCountry[countryCode] ?: emptyList()
    }
}
