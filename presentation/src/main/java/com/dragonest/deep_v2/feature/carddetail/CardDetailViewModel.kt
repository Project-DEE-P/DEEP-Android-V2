package com.dragonest.deep_v2.feature.carddetail

import com.dragonest.domain.mapper.toModel
import com.dragonest.domain.model.CardInfoModel
import com.dragonest.domain.model.CardResponseModel
import com.ggd.zendee.base.BaseViewModel

class CardDetailViewModel : BaseViewModel() {

    lateinit var cardInfoModelList : List<CardInfoModel>


    fun setCardInfoModelList(
        storageToDetail : CardResponseModel
    ){
        cardInfoModelList = storageToDetail.toModel()

    }

}


