package com.pb.funora.security.twofactor.repository

import com.pb.funora.security.twofactor.data.TwoFactorService
import com.pb.funora.security.twofactor.model.TwoFactorCode

class TwoFactorRepository(private val service: TwoFactorService) {
    suspend fun sendCode(email: String): TwoFactorCode = service.sendCode(email)
    suspend fun verifyCode(code: String): Boolean = service.verifyCode(code)
}
