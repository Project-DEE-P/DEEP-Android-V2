package com.dragonest.domain.repository

import com.dragonest.domain.model.user.LoginRequestModel
import com.dragonest.domain.model.user.LoginResponseModel

interface UserRepository {

    suspend fun login(
        loginRequestModel: LoginRequestModel
    ): LoginResponseModel

}