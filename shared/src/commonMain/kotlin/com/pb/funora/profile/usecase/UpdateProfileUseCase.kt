package com.pb.funora.profile.usecase

import com.pb.funora.profile.model.UserProfile
import com.pb.funora.profile.repository.ProfileRepository

class UpdateProfileUseCase(private val repository: ProfileRepository) {
    suspend operator fun invoke(profile: UserProfile): UserProfile =
        repository.updateProfile(profile)
}
