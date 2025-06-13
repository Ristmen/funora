package com.funora.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val repository: AuthRepository) : ViewModel() {
    private val _loginState = MutableStateFlow(AuthState())
    val loginState: StateFlow<AuthState> = _loginState

    private val _signUpState = MutableStateFlow(AuthState())
    val signUpState: StateFlow<AuthState> = _signUpState

    fun onEmailChange(value: String) {
        _loginState.update { it.copy(email = value) }
        _signUpState.update { it.copy(email = value) }
    }

    fun onPasswordChange(value: String) {
        _loginState.update { it.copy(password = value) }
        _signUpState.update { it.copy(password = value) }
    }

    fun onTogglePassword() {
        _loginState.update { it.copy(passwordVisible = !it.passwordVisible) }
        _signUpState.update { it.copy(passwordVisible = !it.passwordVisible) }
    }

    fun login() {
        viewModelScope.launch {
            _loginState.update { it.copy(loading = true, message = null) }
            val result = repository.login(_loginState.value.email, _loginState.value.password)
            _loginState.update { it.copy(loading = false, message = result.exceptionOrNull()?.message ?: "Success") }
        }
    }

    fun signUp() {
        viewModelScope.launch {
            _signUpState.update { it.copy(loading = true, message = null) }
            val result = repository.signUp(_signUpState.value.email, _signUpState.value.password)
            _signUpState.update { it.copy(loading = false, message = result.exceptionOrNull()?.message ?: "Success") }
        }
    }
}
