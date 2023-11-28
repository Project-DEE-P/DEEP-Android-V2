package com.dragonest.domain.usecase.card

import com.dragonest.domain.repository.CardRepository
import javax.inject.Inject

class RememberCardUseCase @Inject constructor(
    private val cardRepository: CardRepository
) {

    suspend operator fun invoke(
        cardType : String, id : Long
    ){
        cardRepository.rememberCard(cardType, id)
    }

}