package com.pb.funora.auth.viewmodel

import com.pb.funora.auth.model.UserCredentials
import com.pb.funora.auth.usecase.SignInUseCase
import com.pb.funora.auth.usecase.SignUpUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class AuthViewModel(
    private val signInUseCase: SignInUseCase,
    private val signUpUseCase: SignUpUseCase
) {
    private val viewModelScope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    var state: AuthState = AuthState()
        private set

    fun signIn(credentials: UserCredentials) {
        state = state.copy(isLoading = true)
        viewModelScope.launch {
            val result = signInUseCase(credentials)
            state = AuthState(isLoading = false, result = result)
        }
    }

    fun signUp(credentials: UserCredentials) {
        state = state.copy(isLoading = true)
        viewModelScope.launch {
            val result = signUpUseCase(credentials)
            state = AuthState(isLoading = false, result = result)
        }
    }
}

