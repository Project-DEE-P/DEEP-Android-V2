package com.dragonest.domain.repository

import com.dragonest.domain.model.user.GoogleOauthRequestModel
import com.dragonest.domain.model.user.GoogleOauthResponseModel
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

    suspend fun googleLogin(
        googleOauthRequestModel: GoogleOauthRequestModel
    ): GoogleOauthResponseModel

}