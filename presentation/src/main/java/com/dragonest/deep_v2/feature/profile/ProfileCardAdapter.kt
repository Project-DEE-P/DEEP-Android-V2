package com.dragonest.deep_v2.feature.profile

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dragonest.deep_v2.R

import com.dragonest.deep_v2.databinding.ItemProfileCardBinding
import com.dragonest.deep_v2.databinding.ItemStorageCardBinding
import com.dragonest.deep_v2.util.TAG
import com.dragonest.domain.model.CardResponseModel
import com.ggd.zendee.base.BaseListAdapter

class ProfileCardAdapter : BaseListAdapter<CardResponseModel, ItemProfileCardBinding>(R.layout.item_profile_card) {

    private var selectedPosition: Int = RecyclerView.NO_POSITION

    override fun action(data: CardResponseModel, binding: ItemProfileCardBinding) {
        val currentPosition = currentList.indexOf(data)

        binding.checkboxCard.isChecked = currentPosition == selectedPosition

        binding.root.setOnClickListener {
            if (selectedPosition != currentPosition) {
                val previousSelectedPosition = selectedPosition
                selectedPosition = currentPosition

                // 이전 선택된 항목의 체크를 해제하고 현재 선택된 항목만 체크합니다.
                notifyItemChanged(previousSelectedPosition, PAYLOAD_UNCHECK)
                notifyItemChanged(selectedPosition, PAYLOAD_CHECK)
            }
        }

        Glide.with(binding.root.context)
            .load("https://api.ddeep.store/v1/api/images/${data.image}")
            .into(binding.imgCard)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int, payloads: MutableList<Any>) {
        if (payloads.contains(PAYLOAD_CHECK)) {
            (holder.binding as? ItemProfileCardBinding)?.checkboxCard?.isChecked = true
        } else if (payloads.contains(PAYLOAD_UNCHECK)) {
            (holder.binding as? ItemProfileCardBinding)?.checkboxCard?.isChecked = false
        } else {
            super.onBindViewHolder(holder, position, payloads)
        }
    }

    companion object {
        const val PAYLOAD_CHECK = "payload_check"
        const val PAYLOAD_UNCHECK = "payload_uncheck"
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

        return BaseViewHolder(ItemProfileCardBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    private lateinit var itemClickListener : OnItemClickListener

    interface OnItemClickListener {
        fun onClick(data : CardResponseModel){

        }
    }

    fun setItemClickListener(itemClickListener: OnItemClickListener){
        this.itemClickListener = itemClickListener
    }

}