package com.dragonest.data.repositoryimpl

import com.dragonest.data.mapper.toDto
import com.dragonest.data.mapper.toModel
import com.dragonest.data.remote.api.OauthApi
import com.dragonest.domain.model.oauth.GoogleOauthRequestModel
import com.dragonest.domain.model.oauth.GoogleOauthResponseModel
import com.dragonest.domain.repository.OauthRepository
import javax.inject.Inject

class OauthRepositoryImpl @Inject constructor(
    private val oauthApi: OauthApi
): OauthRepository {

    override suspend fun googleLogin(googleOauthRequestModel: GoogleOauthRequestModel): GoogleOauthResponseModel =
        oauthApi.googleOauthLogin(googleOauthRequestModel.toDto()).data!!.toModel()
}