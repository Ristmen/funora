package com.pb.funora.profile.model

import kotlinx.serialization.Serializable

@Serializable
data class AvatarSuggestion(
    val url: String,
    val description: String
)
