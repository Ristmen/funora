package com.pb.funora.auth.viewmodel

import com.pb.funora.auth.model.UserCredentials

data class AuthState(
    val email: String = "",
    val password: String = "",
    val loading: Boolean = false,
    val error: String? = null,
    val signedIn: Boolean = false
) {
    val credentials: UserCredentials
        get() = UserCredentials(email, password)
}
