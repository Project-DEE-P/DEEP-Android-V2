package com.dragonest.domain.repository

import com.dragonest.domain.model.oauth.GoogleOauthRequestModel
import com.dragonest.domain.model.oauth.GoogleOauthResponseModel
import com.dragonest.domain.model.user.LoginRequestModel
import com.dragonest.domain.model.user.LoginResponseModel
import com.dragonest.domain.model.user.SignupRequestModel
import com.dragonest.domain.model.user.SignupResponseModel

interface UserRepository {

    suspend fun login(
        loginRequestModel: LoginRequestModel
    ): LoginResponseModel

    suspend fun signup(
        signupRequestModel: SignupRequestModel
    ): SignupResponseModel

}