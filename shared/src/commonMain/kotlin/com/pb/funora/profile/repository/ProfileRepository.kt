package com.pb.funora.profile.repository

import com.pb.funora.profile.data.ProfileService
import com.pb.funora.profile.model.AvatarSuggestion
import com.pb.funora.profile.model.UserProfile

class ProfileRepository(private val service: ProfileService) {
    suspend fun getProfile(): UserProfile = service.fetchProfile()

    suspend fun updateProfile(profile: UserProfile): UserProfile =
        service.updateProfile(profile)

    suspend fun getAvatarSuggestions(countryCode: String): List<AvatarSuggestion> =
        service.fetchAvatarSuggestions(countryCode)
}
