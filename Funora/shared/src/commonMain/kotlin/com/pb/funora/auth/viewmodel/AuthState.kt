package com.pb.funora.auth.viewmodel

import com.pb.funora.auth.model.AuthResult

data class AuthState(
    val isLoading: Boolean = false,
    val result: AuthResult? = null
)

