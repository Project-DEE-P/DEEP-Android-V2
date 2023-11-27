package com.dragonest.data.repositoryimpl

import com.dragonest.data.mapper.toDto
import com.dragonest.data.mapper.toModel
import com.dragonest.data.network.api.UserApi
import com.dragonest.domain.model.user.LoginRequestModel
import com.dragonest.domain.model.user.LoginResponseModel
import com.dragonest.domain.model.user.SignupRequestModel
import com.dragonest.domain.model.user.SignupResponseModel
import com.dragonest.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userApi: UserApi
): UserRepository {

    override suspend fun login(loginRequestModel: LoginRequestModel): LoginResponseModel =
        userApi.login(loginRequestModel.toDto()).data!!.toModel()

    override suspend fun signup(signupRequestModel: SignupRequestModel): SignupResponseModel =
        userApi.signup(signupRequestModel.toDto()).toModel()

}