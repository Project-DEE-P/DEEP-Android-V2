package com.dragonest.data.remote.dto.request

data class SignupRequest(
    val userId: String,
    val password: String,
    val name: String,
    val email: String,
)