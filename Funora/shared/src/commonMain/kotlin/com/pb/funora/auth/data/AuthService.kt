package com.pb.funora.auth.data

import com.pb.funora.auth.model.AuthResult
import com.pb.funora.auth.model.UserCredentials

expect class AuthService {
    suspend fun signIn(credentials: UserCredentials): AuthResult
    suspend fun signUp(credentials: UserCredentials): AuthResult
}

