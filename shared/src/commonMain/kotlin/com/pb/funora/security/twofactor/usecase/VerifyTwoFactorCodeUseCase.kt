package com.pb.funora.security.twofactor.usecase

import com.pb.funora.security.twofactor.repository.TwoFactorRepository

class VerifyTwoFactorCodeUseCase(private val repository: TwoFactorRepository) {
    suspend operator fun invoke(code: String): Boolean = repository.verifyCode(code)
}
