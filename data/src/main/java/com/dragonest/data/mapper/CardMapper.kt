package com.dragonest.data.mapper

import com.dragonest.data.network.request.PostCardRequest
import com.dragonest.data.network.response.ImageCardResponse
import com.dragonest.data.network.response.PostImageResponse
import com.dragonest.data.network.response.TemplateCardResponse
import com.dragonest.domain.model.CardRequestModel
import com.dragonest.domain.model.CardResponseModel

fun TemplateCardResponse.toModel() : CardResponseModel {

    return CardResponseModel(
        isTemplete = true,
        id = this.id,
        uid = this.uid,
        name = this.name,
        company = "",
        address = "",
        position = this.position,
        mobile = this.phone,
        tel = "",
        email = this.email,
        fax = "",
        homepage = this.github,
        department = this.department,
        image = this.image,
        createdDateTime = this.createdDateTime,
        modifiedDateTime = this.modifiedDateTime
    )
}

fun ImageCardResponse.toModel() : CardResponseModel {

    return CardResponseModel(
        isTemplete = true,
        id = this.id,
        uid = this.uid,
        name = this.name,
        company = this.company,
        address = this.address,
        position = this.position,
        mobile = this.mobile,
        tel = this.tel,
        email = this.email,
        fax = this.fax,
        homepage = this.homepage,
        department = this.department,
        image = this.image,
        createdDateTime = this.createdDateTime,
        modifiedDateTime = this.modifiedDateTime
    )
}

fun CardRequestModel.toDto() : PostCardRequest {

    return PostCardRequest(
        name = this.name,
        company = this.company,
        address = this.address,
        position = this.position,
        mobile = this.mobile,
        tel = this.tel,
        email = this.email,
        fax = this.fax,
        homepage = this.homepage,
        department = this.department
    )

}

