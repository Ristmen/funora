package com.pb.funora.home.viewmodel

import com.pb.funora.home.usecase.LoadHomeDataUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val loadHomeData: LoadHomeDataUseCase) {
    private val viewModelScope = CoroutineScope(Dispatchers.Main)
    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state

    fun loadHomeData() {
        _state.value = _state.value.copy(isLoading = true, errorMessage = null)
        viewModelScope.launch {
            runCatching {
                loadHomeData()
            }.onSuccess { data ->
                _state.value = HomeState(data = data, isLoading = false)
            }.onFailure { throwable ->
                _state.value = HomeState(
                    data = null,
                    isLoading = false,
                    errorMessage = throwable.message
                )
            }
        }
    }
}
