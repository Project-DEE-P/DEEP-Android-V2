package com.dragonest.domain.model.user

data class SignupRequestModel(
    val userId: String,
    val password: String,
    val name: String,
    val email: String,
)