package com.dragonest.deep_v2.feature.carddetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.bumptech.glide.Glide
import com.dragonest.deep_v2.R
import com.dragonest.deep_v2.databinding.FragmentCardDetailBinding
import com.dragonest.deep_v2.feature.main.MainViewModel
import com.ggd.zendee.base.BaseFragment

class CardDetailFragment : BaseFragment<FragmentCardDetailBinding, CardDetailViewModel>(R.layout.fragment_card_detail) {

    override val viewModel: CardDetailViewModel by viewModels()
    private val mainViewModel : MainViewModel by activityViewModels()

    private val cardInfoAdapter : CardInfoRecyclerViewAdapter by lazy { CardInfoRecyclerViewAdapter() }

    override fun start() {

        binding.recyclerviewInfo.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = cardInfoAdapter
        }

        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }

        viewModel.setCardInfoModelList(mainViewModel.storageToDetail!!)

        setCard()

    }

    fun setCard(){

        mainViewModel.storageToDetail.let {
            if (it != null) {

                Glide.with(this)
                    .load("https://api.ddeep.store/v1/api/images/${it.image}")
                    .into(binding.imgCard)

                binding.txtTopbar.text = "${it.name}님의 명함"

            }
        }

        cardInfoAdapter.submitList(viewModel.cardInfoModelList)

    }

}