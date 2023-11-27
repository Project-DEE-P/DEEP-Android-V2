package com.dragonest.data.mapper

import com.dragonest.data.network.request.LoginRequest
import com.dragonest.data.network.response.LoginResponse
import com.dragonest.data.remote.dto.request.SignupRequest
import com.dragonest.data.remote.dto.response.SignupResponse
import com.dragonest.domain.model.user.LoginRequestModel
import com.dragonest.domain.model.user.LoginResponseModel
import com.dragonest.domain.model.user.SignupRequestModel
import com.dragonest.domain.model.user.SignupResponseModel

fun LoginRequestModel.toDto() = LoginRequest(
    userId = this.userId,
    password = this.password
)

fun LoginResponse.toModel() = LoginResponseModel(
    token = this.token,
    refreshToken = this.refreshToken
)

fun SignupRequestModel.toDto() = SignupRequest(
    userId = this.userId,
    password = this.password,
    name = this.name,
    email = this.email
)

fun SignupResponse.toModel() = SignupResponseModel(
    code = this.code,
    message = this.message
)