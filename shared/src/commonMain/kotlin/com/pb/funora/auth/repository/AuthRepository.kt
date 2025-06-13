package com.pb.funora.auth.repository

import com.pb.funora.auth.data.AuthService
import com.pb.funora.auth.model.AuthResult
import com.pb.funora.auth.model.UserCredentials

class AuthRepository(private val service: AuthService) {
    suspend fun signIn(credentials: UserCredentials): AuthResult =
        service.signIn(credentials)

    suspend fun signUp(credentials: UserCredentials): AuthResult =
        service.signUp(credentials)

    suspend fun signOut(): AuthResult = service.signOut()
}
