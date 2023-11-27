package com.dragonest.domain.repository

import com.dragonest.domain.model.oauth.GoogleOauthRequestModel
import com.dragonest.domain.model.oauth.GoogleOauthResponseModel

interface OauthRepository {

    suspend fun googleLogin(
        googleOauthRequestModel: GoogleOauthRequestModel
    ): GoogleOauthResponseModel

}