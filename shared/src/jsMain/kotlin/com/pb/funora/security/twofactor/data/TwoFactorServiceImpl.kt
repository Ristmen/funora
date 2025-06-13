package com.pb.funora.security.twofactor.data

import com.pb.funora.security.twofactor.model.TwoFactorCode
import kotlin.random.Random

actual class TwoFactorService {
    private var storedCode: String? = null
    private var expiry: Long = 0L

    actual suspend fun sendCode(email: String): TwoFactorCode {
        val code = Random.nextInt(100000, 999999).toString()
        storedCode = code
        expiry = js("Date.now()") as Double + 5 * 60 * 1000
        return TwoFactorCode(code, expiry.toLong())
    }

    actual suspend fun verifyCode(code: String): Boolean {
        val now = js("Date.now()") as Double
        val valid = storedCode == code && now < expiry
        if (valid) {
            storedCode = null
        }
        return valid
    }
}
