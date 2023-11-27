package com.dragonest.deep_v2.feature.profile

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.dragonest.deep_v2.base.UiState
import com.dragonest.deep_v2.util.TAG
import com.dragonest.domain.model.CardResponseModel
import com.dragonest.domain.usecase.card.GetCardListUseCase
import com.ggd.zendee.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getCardListUseCase: GetCardListUseCase
): BaseViewModel() {

    private val _getCardListState = MutableStateFlow<UiState<List<CardResponseModel>?>>(UiState.Loading)
    var getCardListState: StateFlow<UiState<List<CardResponseModel>?>> = _getCardListState.asStateFlow()

    fun getCardList() = viewModelScope.launch {

        kotlin.runCatching {
            getCardListUseCase()
        }.onSuccess {
            Log.d(TAG, "getCardList: ${it} ")
            _getCardListState.value = UiState.Success(it)
        }.onFailure {
            _getCardListState.value = UiState.Failure
        }

    }

}