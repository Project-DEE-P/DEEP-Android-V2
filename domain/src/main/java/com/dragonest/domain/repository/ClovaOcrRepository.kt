package com.dragonest.domain.repository

import com.dragonest.domain.model.CardInfoModel
import com.dragonest.domain.model.CardInfoType
import com.dragonest.domain.model.CardRequestModel
import com.dragonest.domain.model.ClovaOcrRequestModel
import com.dragonest.domain.model.ClovaOcrResponseModel
import okhttp3.MultipartBody

interface ClovaOcrRepository {
    suspend fun postClovaOcr(
        message : ClovaOcrRequestModel,
        file : MultipartBody.Part
    ) : ClovaOcrResponseModel

}