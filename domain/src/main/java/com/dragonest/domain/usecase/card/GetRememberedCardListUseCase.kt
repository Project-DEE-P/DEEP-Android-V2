package com.dragonest.domain.usecase.card

import com.dragonest.domain.model.CardResponseModel
import com.dragonest.domain.repository.CardRepository
import javax.inject.Inject

class GetRememberedCardListUseCase @Inject constructor(
    private val repository: CardRepository
) {

    suspend operator fun invoke() : List<CardResponseModel>?{
        return repository.getRememberedCardList()
    }
}