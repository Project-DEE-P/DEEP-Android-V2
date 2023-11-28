package com.dragonest.domain.usecase.card

import com.dragonest.domain.mapper.toCardRequestModel
import com.dragonest.domain.model.CardRequestModel
import com.dragonest.domain.model.ClovaOcrResponseModel
import com.dragonest.domain.repository.CardRepository
import okhttp3.MultipartBody
import javax.inject.Inject

class PostImageCardUseCase @Inject constructor(
    private val repository: CardRepository
) {

    suspend operator fun invoke(image : MultipartBody.Part, request : ClovaOcrResponseModel){
        repository.postImageCard(image, request.toCardRequestModel())
    }

}