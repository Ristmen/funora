package com.pb.funora.profile.usecase

import com.pb.funora.profile.model.UserProfile
import com.pb.funora.profile.repository.ProfileRepository

class GetProfileUseCase(private val repository: ProfileRepository) {
    suspend operator fun invoke(): UserProfile = repository.getProfile()
}
