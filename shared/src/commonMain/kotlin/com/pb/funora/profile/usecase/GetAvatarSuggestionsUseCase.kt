package com.pb.funora.profile.usecase

import com.pb.funora.profile.model.AvatarSuggestion
import com.pb.funora.profile.repository.ProfileRepository

class GetAvatarSuggestionsUseCase(private val repository: ProfileRepository) {
    suspend operator fun invoke(countryCode: String): List<AvatarSuggestion> =
        repository.getAvatarSuggestions(countryCode)
}
