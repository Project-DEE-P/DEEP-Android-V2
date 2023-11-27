package com.dragonest.data.network.api

import com.dragonest.data.network.request.GoogleOauthRequest
import com.dragonest.data.network.request.LoginRequest
import com.dragonest.data.network.response.GoogleOauthResponse
import com.dragonest.data.network.response.LoginResponse
import com.dragonest.data.remote.dto.request.SignupRequest
import com.dragonest.data.remote.dto.response.SignupResponse
import com.dragonest.data.remote.util.BaseResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {

    @POST("/v1/api/auth/google")
    suspend fun googleOauthLogin(
        @Body googleOauthRequest: GoogleOauthRequest
    ): BaseResponse<GoogleOauthResponse>

    @POST("/v1/api/auth/login")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): BaseResponse<LoginResponse>

    @POST("/v1/api/auth/signup")
    suspend fun signup(
        @Body signupRequest: SignupRequest
    ): SignupResponse

}