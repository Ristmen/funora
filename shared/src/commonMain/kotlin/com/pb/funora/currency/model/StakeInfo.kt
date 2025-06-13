package com.pb.funora.currency.model

import kotlinx.serialization.Serializable

@Serializable
data class StakeInfo(
    val stakedAmount: Long,
    val earnedRewards: Long,
    val since: Long
)
