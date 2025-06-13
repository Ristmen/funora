package com.pb.funora.security.twofactor.data

import com.pb.funora.security.twofactor.model.TwoFactorCode

expect class TwoFactorService {
    suspend fun sendCode(email: String): TwoFactorCode
    suspend fun verifyCode(code: String): Boolean
}
