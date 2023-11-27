package com.dragonest.deep_v2.feature.main

import androidx.lifecycle.ViewModel
import com.dragonest.domain.model.CardResponseModel
import com.dragonest.domain.repository.UserRepository
import javax.inject.Inject


class MainViewModel: ViewModel() {

    var storageToDetail : CardResponseModel? = null

}