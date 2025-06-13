package com.funora.auth

data class AuthState(
    val email: String = "",
    val password: String = "",
    val passwordVisible: Boolean = false,
    val loading: Boolean = false,
    val message: String? = null
)
