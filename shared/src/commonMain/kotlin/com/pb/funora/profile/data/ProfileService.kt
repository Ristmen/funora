package com.pb.funora.profile.data

import com.pb.funora.profile.model.AvatarSuggestion
import com.pb.funora.profile.model.UserProfile

interface ProfileService {
    suspend fun fetchProfile(): UserProfile
    suspend fun updateProfile(profile: UserProfile): UserProfile
    suspend fun fetchAvatarSuggestions(countryCode: String): List<AvatarSuggestion>
}
