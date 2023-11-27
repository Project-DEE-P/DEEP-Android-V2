package com.dragonest.domain.usecase.card

import com.dragonest.domain.repository.CardRepository
import javax.inject.Inject

class DeleteCardUseCase @Inject constructor(
    private val repository: CardRepository
) {

    suspend operator fun invoke(id : Int){
        repository.deleteCard(id)
    }

}