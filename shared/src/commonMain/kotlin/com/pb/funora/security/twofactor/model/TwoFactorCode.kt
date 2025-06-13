package com.pb.funora.security.twofactor.model

data class TwoFactorCode(
    val code: String,
    val expiresAt: Long
)
