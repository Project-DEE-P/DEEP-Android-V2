package com.dragonest.domain.model.user

data class LoginRequestModel(
    val userId: String,
    val password: String
)