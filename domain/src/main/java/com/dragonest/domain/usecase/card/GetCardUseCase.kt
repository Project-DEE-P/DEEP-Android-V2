package com.dragonest.domain.usecase.card

import com.dragonest.domain.model.CardResponseModel
import com.dragonest.domain.repository.CardRepository
import javax.inject.Inject

class GetCardUseCase @Inject constructor(
    private val repository: CardRepository
){
    suspend operator fun invoke(id : Int, isTemplate : Boolean) : CardResponseModel?{
        return repository.getCard(id, isTemplate)
    }
}