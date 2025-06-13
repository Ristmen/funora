package com.pb.funora.auth.data

import com.pb.funora.auth.model.AuthResult
import com.pb.funora.auth.model.UserCredentials

actual class AuthService {
    actual suspend fun signIn(credentials: UserCredentials): AuthResult {
        // TODO: Implement Firebase Authentication for iOS
        return AuthResult.Failure("Not implemented")
    }

    actual suspend fun signUp(credentials: UserCredentials): AuthResult {
        // TODO: Implement Firebase Authentication for iOS
        return AuthResult.Failure("Not implemented")
    }
}

