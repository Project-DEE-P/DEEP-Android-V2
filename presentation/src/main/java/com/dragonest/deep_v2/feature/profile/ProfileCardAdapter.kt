package com.dragonest.deep_v2.feature.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import com.bumptech.glide.Glide
import com.dragonest.deep_v2.R

import com.dragonest.deep_v2.databinding.ItemProfileCardBinding
import com.dragonest.deep_v2.databinding.ItemStorageCardBinding
import com.dragonest.domain.model.CardResponseModel
import com.ggd.zendee.base.BaseListAdapter

class ProfileCardAdapter : BaseListAdapter<CardResponseModel, ItemProfileCardBinding>(R.layout.item_profile_card) {

    private var selectedPosition: Int = -1


    override fun action(data: CardResponseModel, binding: ItemProfileCardBinding) {

        val currentPosition = currentList.indexOf(data)

        // 선택된 아이템에 대한 처리
        binding.checkboxCard.isChecked = selectedPosition == currentPosition

        binding.root.setOnClickListener {
            handleSelection(currentPosition)
        }


        Glide.with(binding.root.context)
            .load("https://api.ddeep.store/v1/api/images/${ data.image }")
            .into(binding.imgCard)
            .onLoadStarted(AppCompatResources.getDrawable(binding.root.context,R.drawable.bg_card_loading))

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

        return BaseViewHolder(ItemProfileCardBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    private fun handleSelection(position: Int) {
        if (selectedPosition != position) {
            val previousSelectedPosition = selectedPosition
            selectedPosition = position
            notifyItemChanged(previousSelectedPosition)
            notifyItemChanged(selectedPosition)
        }
    }

}