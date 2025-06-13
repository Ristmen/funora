package com.pb.funora.auth.usecase

import com.pb.funora.auth.model.AuthResult
import com.pb.funora.auth.model.UserCredentials
import com.pb.funora.auth.repository.AuthRepository

class SignUpUseCase(private val repository: AuthRepository) {
    suspend operator fun invoke(credentials: UserCredentials): AuthResult =
        repository.signUp(credentials)
}
