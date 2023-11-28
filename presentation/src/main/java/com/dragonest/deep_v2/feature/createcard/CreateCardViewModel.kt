package com.dragonest.deep_v2.feature.createcard

import android.graphics.Bitmap
import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.viewModelScope
import com.dragonest.deep_v2.base.UiState
import com.dragonest.deep_v2.util.TAG
import com.dragonest.domain.mapper.toCardInfoModel
import com.dragonest.domain.model.CardInfoModel
import com.dragonest.domain.model.CardRequestModel
import com.dragonest.domain.model.CardResponseModel
import com.dragonest.domain.model.ClovaOcrRequestModel
import com.dragonest.domain.model.ClovaOcrResponseModel
import com.dragonest.domain.usecase.card.GetCardUseCase
import com.dragonest.domain.usecase.card.PostImageCardUseCase
import com.dragonest.domain.usecase.clovaocr.PostClovaOcrUseCase
import com.ggd.zendee.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import java.io.File
import javax.inject.Inject

@HiltViewModel
class CreateCardViewModel @Inject constructor(
    private val postClovaOcrUseCase: PostClovaOcrUseCase,
    private val postImageCardUseCase: PostImageCardUseCase
): BaseViewModel() {

    var croppedImageBitmap : Bitmap? = null


    var clovaOcrMultiPart : MultipartBody.Part? = null
    var postImageMultiPart : MultipartBody.Part? = null

    var clovaOcrResponseModel : ClovaOcrResponseModel? = null


    private val _postClovaOcrState = MutableStateFlow<UiState<List<CardInfoModel>?>>(UiState.Loading)
    var postClovaOcrState: StateFlow<UiState<List<CardInfoModel>?>> = _postClovaOcrState.asStateFlow()
    fun postOcrClova(
        message : ClovaOcrRequestModel,
        file : MultipartBody.Part
    ) = viewModelScope.launch {

        kotlin.runCatching {
            postClovaOcrUseCase(message, file)
        }.onSuccess {
            Log.d(TAG, "getCardList: ${it} ")
            clovaOcrResponseModel = it
            _postClovaOcrState.value = UiState.Success(it.toCardInfoModel())
        }.onFailure {
            _postClovaOcrState.value = UiState.Failure
        }

    }

    private val _postImageCardState = MutableStateFlow<UiState<Nothing?>>(UiState.Loading)
    var postImageCardState: StateFlow<UiState<Nothing?>> = _postImageCardState.asStateFlow()
    fun postImageCard(
        image : MultipartBody.Part,
        request : ClovaOcrResponseModel
    ) = viewModelScope.launch {

        kotlin.runCatching {
            postImageCardUseCase(
                image = image,
                request = request
            )
        }.onSuccess {
            _postImageCardState.value = UiState.Success(null)
        }.onFailure {
            _postImageCardState.value = UiState.Failure
        }

    }





}