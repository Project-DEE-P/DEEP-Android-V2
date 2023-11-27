package com.dragonest.data.datasource

import com.dragonest.data.network.api.CardApi
import com.dragonest.data.network.api.ClovaOcrApi
import com.dragonest.data.network.api.UserApi
import com.dragonest.data.network.request.ClovaOcrRequest
import com.dragonest.data.network.response.ClovaOcrResponse
import okhttp3.MultipartBody
import javax.inject.Inject

class ClovaOcrDataSource @Inject constructor(
    private val clovaOcrApi: ClovaOcrApi
) {

    suspend fun postClovaOcr (
        message : ClovaOcrRequest,
        file : List<MultipartBody.Part>
    ) : ClovaOcrResponse =
        clovaOcrApi.postClovaOcr(
            message = message,
            file = file
        )
}