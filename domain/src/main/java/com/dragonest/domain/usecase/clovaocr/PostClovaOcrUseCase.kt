package com.dragonest.domain.usecase.clovaocr

import com.dragonest.domain.model.CardInfoModel
import com.dragonest.domain.model.ClovaOcrRequestModel
import com.dragonest.domain.model.ClovaOcrResponseModel
import com.dragonest.domain.repository.ClovaOcrRepository
import okhttp3.MultipartBody
import javax.inject.Inject

class PostClovaOcrUseCase @Inject constructor(
    private val repository: ClovaOcrRepository
) {

    suspend operator fun invoke(
        message : ClovaOcrRequestModel,
        file : MultipartBody.Part
    ) : ClovaOcrResponseModel =
        repository.postClovaOcr(
            message, file
        )


}