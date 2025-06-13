package com.pb.funora.auth.model

sealed class AuthResult {
    object Success : AuthResult()
    data class Failure(val error: String) : AuthResult()
}
