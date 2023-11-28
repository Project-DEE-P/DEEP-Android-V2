package com.dragonest.deep_v2.feature.cardremember

import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.dragonest.deep_v2.R
import com.dragonest.deep_v2.base.UiState
import com.dragonest.deep_v2.databinding.FragmentCardRememberBinding
import com.dragonest.deep_v2.feature.cardremember.CardRememberViewModel
import com.dragonest.deep_v2.feature.main.MainViewModel
import com.dragonest.domain.model.CardResponseModel
import com.ggd.zendee.base.BaseFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CardRememberFragment : BaseFragment<FragmentCardRememberBinding, CardRememberViewModel>(R.layout.fragment_card_remember) {

    override val viewModel: CardRememberViewModel by viewModels()
    private val mainViewModel : MainViewModel by activityViewModels()

    var cardData : CardResponseModel? = null

    override fun start() {

        setCollector()

    }

    fun setCollector(){

        CoroutineScope(Dispatchers.IO).launch(){
            viewModel.getCardState.collect {
                when (it) {
                    UiState.Failure -> {}
                    UiState.Loading -> {}
                    is UiState.Success -> {
                        withContext(Dispatchers.Main){
                            cardData = it.data
                            Glide.with(requireContext())
                                .load("https://api.ddeep.store/v1/api/images/${cardData?.image}")
                                .into(binding.imgCard)

                            binding.txtCardTitle1.text = "${cardData?.name}님의"
                        }
                    }
                }
            }
        }

        CoroutineScope(Dispatchers.IO).launch() {
            viewModel.rememberCardState.collect {
                when (it) {
                    UiState.Failure -> {}
                    UiState.Loading -> {}
                    is UiState.Success -> {
                        findNavController().popBackStack(findNavController().graph.startDestinationId, true)
                    }
                }
            }
        }

    }

}