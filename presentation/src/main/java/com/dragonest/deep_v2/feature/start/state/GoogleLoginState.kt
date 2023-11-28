package com.dragonest.deep_v2.feature.start.state

data class GoogleLoginState(
    val isSuccess: Boolean = false,
    val error: String = ""
)
