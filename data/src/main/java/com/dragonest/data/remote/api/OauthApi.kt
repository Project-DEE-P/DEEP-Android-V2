package com.dragonest.data.remote.api

import com.dragonest.data.network.request.GoogleOauthRequest
import com.dragonest.data.network.response.GoogleOauthResponse
import com.dragonest.data.remote.util.BaseResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface OauthApi {

    @POST("/v1/api/auth/google")
    suspend fun googleOauthLogin(
        @Body googleOauthRequest: GoogleOauthRequest
    ): BaseResponse<GoogleOauthResponse>

}