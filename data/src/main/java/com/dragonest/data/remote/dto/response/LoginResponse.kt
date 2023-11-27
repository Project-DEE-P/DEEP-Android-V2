package com.dragonest.data.network.response

data class LoginResponse(
    val token: String,
    val refreshToken: String
)