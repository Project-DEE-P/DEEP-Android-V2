package com.dragonest.domain.usecase.card

import com.dragonest.domain.model.CardResponseModel
import com.dragonest.domain.repository.CardRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCardListUseCase @Inject constructor(
    private val repository: CardRepository
) {

    suspend operator fun invoke() : List<CardResponseModel>? {
        return repository.getCardList()
    }
}