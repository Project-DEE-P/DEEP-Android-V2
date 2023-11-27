package com.dragonest.domain.model.user

data class LoginResponseModel(
    val token: String,
    val refreshToken: String
)