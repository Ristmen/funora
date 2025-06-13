package com.pb.funora.profile.model

import kotlinx.serialization.Serializable

@Serializable
data class UserProfile(
    val id: String,
    val name: String,
    val avatarUrl: String,
    val countryCode: String,
    val badges: List<String>
)
