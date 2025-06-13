package com.pb.funora.profile.viewmodel

import com.pb.funora.profile.model.AvatarSuggestion
import com.pb.funora.profile.model.UserProfile

data class ProfileState(
    val profile: UserProfile? = null,
    val suggestions: List<AvatarSuggestion> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
