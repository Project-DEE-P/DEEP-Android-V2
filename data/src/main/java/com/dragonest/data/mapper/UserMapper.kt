package com.dragonest.data.mapper

import com.dragonest.data.network.request.LoginRequest
import com.dragonest.data.network.response.LoginResponse
import com.dragonest.domain.model.user.LoginRequestModel
import com.dragonest.domain.model.user.LoginResponseModel

fun LoginRequestModel.toDto() = LoginRequest(
    userId = this.userId,
    password = this.password
)

fun LoginResponse.toModel() = LoginResponseModel(
    token = this.token,
    refreshToken = this.refreshToken
)