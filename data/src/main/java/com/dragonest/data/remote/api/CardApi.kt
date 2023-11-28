package com.dragonest.data.network.api

import com.dragonest.data.network.request.PostCardRequest
import com.dragonest.data.network.response.ImageCardResponse
import com.dragonest.data.network.response.PostImageResponse
import com.dragonest.data.network.response.TemplateCardResponse
import com.dragonest.data.remote.dto.request.RememberCardRequest
import com.dragonest.data.remote.util.BaseResponse
import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface CardApi {

    @Multipart
    @POST("/v1/api/images/image")
    suspend fun postImage(
        @Header("ACCESS-KEY") secret : String = "d15ee2fe18d2ebe2ef7afda51ffd3114e5cd1f29dc8fd70e3ffee96b698ceed027a0",
        @Part image : MultipartBody.Part
    ) : BaseResponse<PostImageResponse>

    @POST("/v2/api/remember")
    suspend fun rememberCard(
        @Body request : RememberCardRequest
    ) : BaseResponse<Any>

    @POST("/v2/api/card/image")
    suspend fun postImageCard(
        @Body request: PostCardRequest
    ) : BaseResponse<Any>

    @GET("/v2/api/remember/image")
    suspend fun getRememberedImageCardList(

    ) : BaseResponse<List<ImageCardResponse>>

    @GET("/v2/api/remember/template")
    suspend fun getRememberedTempleteCardList(

    ) : BaseResponse<List<TemplateCardResponse>>

    @GET("/v2/api/card/image")
    suspend fun getImageCardList(

    ) : BaseResponse<List<ImageCardResponse>>

    @GET("/v2/api/card/template")
    suspend fun getTemplateCardList(

    ) : BaseResponse<List<TemplateCardResponse>>

    @GET("/v2/api/card/image/{id}")
    suspend fun getImageCard(
        @Path("id") id : Int
    ) : BaseResponse<ImageCardResponse>

    @GET("/v2/api/card/template/{id}")
    suspend fun getTemplateCard(
        @Path("id") id : Int
    ) : BaseResponse<TemplateCardResponse>


    @DELETE("v2/api/card/image/{id}")
    suspend fun deleteCard(
        @Path("id") id : Int
    ) : BaseResponse<Any>
}