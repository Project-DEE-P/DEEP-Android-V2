package com.dragonest.deep_v2.feature.carddetail

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import androidx.core.content.res.ResourcesCompat
import com.bumptech.glide.Glide
import com.dragonest.deep_v2.R
import com.dragonest.deep_v2.databinding.ItemDetailInfoBinding
import com.dragonest.deep_v2.databinding.ItemStorageCardBinding
import com.dragonest.deep_v2.util.TAG
import com.dragonest.domain.model.CardInfoModel
import com.dragonest.domain.model.CardInfoType
import com.dragonest.domain.model.CardResponseModel
import com.ggd.zendee.base.BaseListAdapter

class CardInfoRecyclerViewAdapter : BaseListAdapter<CardInfoModel,ItemDetailInfoBinding>(R.layout.item_detail_info){

    override fun action(data: CardInfoModel, binding: ItemDetailInfoBinding) {

        binding.imgInfo.background = getIcon(binding.root.context,data.cardInfoType)

        binding.txtInfoTitle.text = data.cardInfoType.type

        if (data.cardInfoType.isCopible) binding.txtExtraAction.text = "복사하기"
        else if (data.cardInfoType.isAddress) binding.txtExtraAction.text = "연락처 저장하기"
        else if (data.cardInfoType.isWebsite) binding.txtExtraAction.text = "이동하기"
        else binding.txtExtraAction.visibility = View.INVISIBLE

        binding.txtInfoContent.text = data.content

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return BaseViewHolder(ItemDetailInfoBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

}

fun getIcon(context : Context, cardInfoType: CardInfoType) : Drawable? {

    var id = 0

    when(cardInfoType){

        CardInfoType.NAME -> id = R.drawable.ic_name
        CardInfoType.COMPANY -> id = R.drawable.ic_company
        CardInfoType.POSITION -> id = R.drawable.ic_position
        CardInfoType.DEPARTMENT -> id = R.drawable.ic_department
        CardInfoType.MOBILE -> id = R.drawable.ic_phone
        CardInfoType.TEL -> id = R.drawable.ic_tel
        CardInfoType.EMAIL -> id = R.drawable.ic_email
        CardInfoType.HOMEPAGE -> id = R.drawable.ic_homepage
        CardInfoType.ADDRESS -> id = R.drawable.ic_address
        CardInfoType.FAX -> id = R.drawable.ic_fax
    }

    return ResourcesCompat.getDrawable(context.resources,id,context.theme)

}