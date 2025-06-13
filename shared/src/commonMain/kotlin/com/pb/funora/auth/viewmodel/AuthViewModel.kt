package com.pb.funora.auth.viewmodel

import com.pb.funora.auth.usecase.SignInUseCase
import com.pb.funora.auth.usecase.SignOutUseCase
import com.pb.funora.auth.usecase.SignUpUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel(
    private val signInUseCase: SignInUseCase,
    private val signUpUseCase: SignUpUseCase,
    private val signOutUseCase: SignOutUseCase,
    private val scope: CoroutineScope = CoroutineScope(Dispatchers.Main)
) {
    private val _state = MutableStateFlow(AuthState())
    val state: StateFlow<AuthState> = _state

    fun updateEmail(email: String) {
        _state.value = _state.value.copy(email = email)
    }

    fun updatePassword(password: String) {
        _state.value = _state.value.copy(password = password)
    }

    fun performSignIn() {
        scope.launch {
            _state.value = _state.value.copy(loading = true, error = null)
            val result = signInUseCase(_state.value.credentials)
            _state.value = when (result) {
                is com.pb.funora.auth.model.AuthResult.Success -> _state.value.copy(loading = false, signedIn = true)
                is com.pb.funora.auth.model.AuthResult.Failure -> _state.value.copy(loading = false, error = result.error)
            }
        }
    }

    fun performSignUp() {
        scope.launch {
            _state.value = _state.value.copy(loading = true, error = null)
            val result = signUpUseCase(_state.value.credentials)
            _state.value = when (result) {
                is com.pb.funora.auth.model.AuthResult.Success -> _state.value.copy(loading = false, signedIn = true)
                is com.pb.funora.auth.model.AuthResult.Failure -> _state.value.copy(loading = false, error = result.error)
            }
        }
    }

    fun performSignOut() {
        scope.launch {
            _state.value = _state.value.copy(loading = true, error = null)
            val result = signOutUseCase()
            _state.value = when (result) {
                is com.pb.funora.auth.model.AuthResult.Success -> _state.value.copy(loading = false, signedIn = false)
                is com.pb.funora.auth.model.AuthResult.Failure -> _state.value.copy(loading = false, error = result.error)
            }
        }
    }
}
