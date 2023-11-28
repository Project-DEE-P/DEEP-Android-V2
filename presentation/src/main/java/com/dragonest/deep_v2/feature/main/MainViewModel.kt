package com.dragonest.deep_v2.feature.main

import android.graphics.Bitmap
import android.text.BoringLayout
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dragonest.deep_v2.base.UiState
import com.dragonest.deep_v2.util.TAG
import com.dragonest.domain.model.CardResponseModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.File

class MainViewModel : ViewModel() {

    var storageToDetail : CardResponseModel? = null

    var selectedCardId : Long? = null
    var isPutNfcClicked : Boolean = false

    private val _successPutNfc = MutableStateFlow<Boolean>(false)
    val successPutNfc: StateFlow<Boolean>
        get() = _successPutNfc

    fun updateSuccessPutNfc(value: Boolean) {
        _successPutNfc.value = value
        viewModelScope.launch{
            _successPutNfc.emit(value)
        }
    }



}