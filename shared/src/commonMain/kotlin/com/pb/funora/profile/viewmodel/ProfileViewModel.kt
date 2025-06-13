package com.pb.funora.profile.viewmodel

import com.pb.funora.profile.model.UserProfile
import com.pb.funora.profile.usecase.GetAvatarSuggestionsUseCase
import com.pb.funora.profile.usecase.GetProfileUseCase
import com.pb.funora.profile.usecase.UpdateProfileUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val getProfile: GetProfileUseCase,
    private val updateProfile: UpdateProfileUseCase,
    private val getAvatarSuggestions: GetAvatarSuggestionsUseCase,
    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.Main)
) {
    private val _state = MutableStateFlow(ProfileState())
    val state: StateFlow<ProfileState> = _state

    private var job: Job? = null

    fun fetchProfile() {
        job?.cancel()
        job = coroutineScope.launch {
            _state.value = _state.value.copy(isLoading = true, error = null)
            runCatching {
                val profile = getProfile()
                val suggestions = getAvatarSuggestions(profile.countryCode)
                _state.value.copy(
                    profile = profile,
                    suggestions = suggestions,
                    isLoading = false
                )
            }.onSuccess { newState -> _state.value = newState }
             .onFailure { _state.value = _state.value.copy(isLoading = false, error = it.message) }
        }
    }

    fun updateProfile(profile: UserProfile) {
        job?.cancel()
        job = coroutineScope.launch {
            _state.value = _state.value.copy(isLoading = true, error = null)
            runCatching {
                val updated = updateProfile(profile)
                val suggestions = getAvatarSuggestions(updated.countryCode)
                _state.value.copy(
                    profile = updated,
                    suggestions = suggestions,
                    isLoading = false
                )
            }.onSuccess { newState -> _state.value = newState }
             .onFailure { _state.value = _state.value.copy(isLoading = false, error = it.message) }
        }
    }
}
