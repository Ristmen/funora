package com.pb.funora.auth.usecase

import com.pb.funora.auth.model.AuthResult
import com.pb.funora.auth.repository.AuthRepository

class SignOutUseCase(private val repository: AuthRepository) {
    suspend operator fun invoke(): AuthResult = repository.signOut()
}
