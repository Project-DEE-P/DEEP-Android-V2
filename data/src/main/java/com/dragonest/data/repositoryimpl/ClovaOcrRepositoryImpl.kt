package com.dragonest.data.repositoryimpl

import com.dragonest.data.mapper.toDto
import com.dragonest.data.mapper.toModel
import com.dragonest.data.network.api.ClovaOcrApi
import com.dragonest.domain.model.CardInfoModel
import com.dragonest.domain.model.ClovaOcrRequestModel
import com.dragonest.domain.model.ClovaOcrResponseModel
import com.dragonest.domain.repository.ClovaOcrRepository
import okhttp3.MultipartBody
import javax.inject.Inject

class ClovaOcrRepositoryImpl @Inject constructor(
    private val clovaOcrApi: ClovaOcrApi
) : ClovaOcrRepository {
    override suspend fun postClovaOcr(message: ClovaOcrRequestModel, file: MultipartBody.Part) : ClovaOcrResponseModel =
        clovaOcrApi.postClovaOcr(
            message = message.toDto(),
            file = listOf(file)
        ).toModel()

}