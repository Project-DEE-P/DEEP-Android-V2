package com.dragonest.deep_v2.base

sealed class UiState<out T>(data : T?){
    object Loading : UiState<Nothing>(null)
    object Failure : UiState<Nothing>(null)
    data class Success<out R>(val data : R) : UiState<R>(data)
}

