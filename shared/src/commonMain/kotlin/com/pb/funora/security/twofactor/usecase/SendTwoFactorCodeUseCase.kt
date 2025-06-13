package com.pb.funora.security.twofactor.usecase

import com.pb.funora.security.twofactor.model.TwoFactorCode
import com.pb.funora.security.twofactor.repository.TwoFactorRepository

class SendTwoFactorCodeUseCase(private val repository: TwoFactorRepository) {
    suspend operator fun invoke(email: String): TwoFactorCode = repository.sendCode(email)
}
