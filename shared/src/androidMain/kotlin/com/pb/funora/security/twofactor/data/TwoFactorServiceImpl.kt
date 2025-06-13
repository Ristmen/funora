package com.pb.funora.security.twofactor.data

import com.pb.funora.security.twofactor.model.TwoFactorCode
import kotlin.random.Random

actual class TwoFactorService {
    private var storedCode: String? = null
    private var expiry: Long = 0L

    actual suspend fun sendCode(email: String): TwoFactorCode {
        val code = Random.nextInt(100000, 999999).toString()
        storedCode = code
        expiry = System.currentTimeMillis() + 5 * 60 * 1000
        return TwoFactorCode(code, expiry)
    }

    actual suspend fun verifyCode(code: String): Boolean {
        val valid = storedCode == code && System.currentTimeMillis() < expiry
        if (valid) {
            storedCode = null
        }
        return valid
    }
}
