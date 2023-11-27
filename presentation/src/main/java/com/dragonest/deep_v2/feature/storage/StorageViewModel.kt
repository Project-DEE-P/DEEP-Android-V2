package com.dragonest.deep_v2.feature.storage

import androidx.lifecycle.viewModelScope
import com.dragonest.deep_v2.base.UiState
import com.dragonest.domain.model.CardResponseModel
import com.dragonest.domain.repository.CardRepository
import com.dragonest.domain.usecase.card.GetCardListUseCase
import com.dragonest.domain.usecase.card.GetCardUseCase
import com.dragonest.domain.usecase.card.GetRememberedCardListUseCase
import com.ggd.zendee.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StorageViewModel @Inject constructor(
    private val getRememberedCardListUseCase: GetRememberedCardListUseCase
): BaseViewModel() {

    private val _getRememberedCardListState = MutableStateFlow<UiState<List<CardResponseModel>?>>(UiState.Loading)
    var getRememberedListCardState: StateFlow<UiState<List<CardResponseModel>?>> = _getRememberedCardListState.asStateFlow()

    fun getCardList() = viewModelScope.launch {

        kotlin.runCatching {
            getRememberedCardListUseCase()
        }.onSuccess {
            _getRememberedCardListState.value = UiState.Success(it)
        }.onFailure {
            _getRememberedCardListState.value = UiState.Failure
        }

    }

}