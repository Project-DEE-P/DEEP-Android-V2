package com.dragonest.deep_v2.feature.storage

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import com.bumptech.glide.Glide
import com.dragonest.deep_v2.R
import com.dragonest.deep_v2.databinding.ItemStorageCardBinding
import com.dragonest.deep_v2.util.TAG
import com.dragonest.domain.model.CardResponseModel
import com.ggd.zendee.base.BaseListAdapter

class StorageRecyclerViewAdapter : BaseListAdapter<CardResponseModel,ItemStorageCardBinding>(R.layout.item_storage_card) {

    override fun action(data: CardResponseModel, binding: ItemStorageCardBinding) {
        binding.txtName.text = data.name
        binding.txtCompany.text = data.company
        binding.txtDate.text = data.createdDateTime
        Log.d(TAG,"StorageRecyclerViewAdapter - imgeUrl - ${data.image}")
        Glide.with(binding.root.context)
            .load("https://api.ddeep.store/v1/api/images/${data.image}")
            .into(binding.imgCard)

        binding.imgCard.setOnClickListener {
            itemClickListener.onClick(data)
        }
    }

    private lateinit var itemClickListener : OnItemClickListener

    interface OnItemClickListener {
        fun onClick(data : CardResponseModel)
    }

    fun setItemClickListener(itemClickListener: OnItemClickListener){
        this.itemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return BaseViewHolder(ItemStorageCardBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }
}