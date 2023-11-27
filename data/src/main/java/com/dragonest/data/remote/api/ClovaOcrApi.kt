package com.dragonest.data.network.api

import com.dragonest.data.network.request.ClovaOcrRequest
import com.dragonest.data.network.response.ClovaOcrResponse
import okhttp3.MultipartBody
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ClovaOcrApi {

    @Multipart
    @POST("name-card")
    suspend fun postClovaOcr(
        @Header("X-OCR-SECRET") secret : String = "Rk1KWnVyUnlRcEZzdUJEbkVBeU9vTU9QcG1pRlhOQWk=",
        @Part("message") message : ClovaOcrRequest,
        @Part file : List<MultipartBody.Part>
    ) : ClovaOcrResponse

}






