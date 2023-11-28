package com.dragonest.data.repositoryimpl

import com.dragonest.data.mapper.toDto
import com.dragonest.data.mapper.toModel
import com.dragonest.data.network.api.CardApi
import com.dragonest.data.remote.dto.request.RememberCardRequest
import com.dragonest.domain.model.CardRequestModel
import com.dragonest.domain.model.CardResponseModel
import com.dragonest.domain.repository.CardRepository
import okhttp3.MultipartBody
import javax.inject.Inject

class CardRepositoryImpl @Inject constructor(
    private val cardApi: CardApi
) : CardRepository {


    override suspend fun postImageCard(image: MultipartBody.Part, request: CardRequestModel) {

        val ident = cardApi.postImage(image = image)
        cardApi.postImageCard(request.toDto().apply {
            this.image = if (ident.data?.ident == null) "" else ident.data.ident
        })
    }

    override suspend fun getRememberedCardList(): List<CardResponseModel>? {
        val imageList = cardApi.getRememberedImageCardList().data?.map { it.toModel() }
        val templateList = cardApi.getRememberedTempleteCardList().data?.map { it.toModel() }

        if (imageList == null && templateList == null) return null
        else if (templateList == null) return imageList
        else if (imageList == null) return templateList
        else return imageList + templateList
    }

    override suspend fun getCardList(): List<CardResponseModel>? {

        val imageList = cardApi.getImageCardList().data?.map { it.toModel() }
        val templateList = cardApi.getTemplateCardList().data?.map { it.toModel() }

        if (imageList == null && templateList == null) return null
        else if (templateList == null) return imageList
        else if (imageList == null) return templateList
        else return imageList + templateList

    }


    override suspend fun getCard(id: Int, isTemplate : Boolean): CardResponseModel? {

        if(isTemplate){
            return cardApi.getTemplateCard(id).data?.toModel()
        }
        else {
            return cardApi.getImageCard(id).data?.toModel()
        }

    }

    override suspend fun deleteCard(id: Int) {
        cardApi.deleteCard(id)

    }

    override suspend fun rememberCard(cardType: String, id: Long) {
        cardApi.rememberCard(RememberCardRequest(
            cardType, id
        ))
    }
}