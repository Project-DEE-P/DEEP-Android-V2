package com.dragonest.data.mapper

import com.dragonest.data.network.request.GoogleOauthRequest
import com.dragonest.data.network.response.GoogleOauthResponse
import com.dragonest.domain.model.oauth.GoogleOauthRequestModel
import com.dragonest.domain.model.oauth.GoogleOauthResponseModel

fun GoogleOauthRequestModel.toDto() = GoogleOauthRequest(
    token = this.token
)

fun GoogleOauthResponse.toModel() = GoogleOauthResponseModel(
    token = this.token,
    refreshToken = this.refreshToken
)