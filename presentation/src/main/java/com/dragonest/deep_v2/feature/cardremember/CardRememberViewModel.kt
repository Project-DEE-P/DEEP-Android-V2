package com.dragonest.deep_v2.feature.cardremember

import androidx.lifecycle.viewModelScope
import com.dragonest.deep_v2.base.UiState
import com.dragonest.domain.model.CardResponseModel
import com.dragonest.domain.usecase.card.GetCardUseCase
import com.dragonest.domain.usecase.card.RememberCardUseCase
import com.ggd.zendee.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class CardRememberViewModel @Inject constructor(

    private val rememberCardUseCase: RememberCardUseCase,
    private val getCardUseCase: GetCardUseCase

): BaseViewModel() {

    private val _rememberCardState = MutableStateFlow<UiState<Nothing?>>(
        UiState.Loading)
    var rememberCardState: StateFlow<UiState<Nothing?>> = _rememberCardState.asStateFlow()

    fun getCardList(
        cardType : String, id : Long
    ) = viewModelScope.launch {

        kotlin.runCatching {
            rememberCardUseCase(cardType, id)
        }.onSuccess {
            _rememberCardState.value = UiState.Success(data = null)
        }.onFailure {
            _rememberCardState.value = UiState.Failure
        }

    }

    private val _getCardState = MutableStateFlow<UiState<CardResponseModel?>>(
        UiState.Loading)
    var getCardState: StateFlow<UiState<CardResponseModel?>> = _getCardState.asStateFlow()

    fun getCard(
        isTemplate : Boolean, id : Int
    ) = viewModelScope.launch {

        kotlin.runCatching {
            getCardUseCase(id, isTemplate)
        }.onSuccess {
            _getCardState.value = UiState.Success(it)
        }.onFailure {
            _getCardState.value = UiState.Failure
        }

    }

}