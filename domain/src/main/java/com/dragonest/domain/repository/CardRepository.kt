package com.dragonest.domain.repository

import com.dragonest.domain.model.CardRequestModel
import com.dragonest.domain.model.CardResponseModel
import okhttp3.MultipartBody


interface CardRepository {

    suspend fun postImageCard(
        image : MultipartBody.Part,
        request : CardRequestModel
    )

    suspend fun getRememberedCardList(

    ) : List<CardResponseModel>?

    suspend fun getCardList(

    ) : List<CardResponseModel>?

    suspend fun getCard(
        id : Int,
        isTemplate : Boolean
    ) : CardResponseModel?

    suspend fun deleteCard(
        id : Int
    )

    suspend fun rememberCard(
        cardType : String,
        id : Long
    )

}