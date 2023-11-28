package com.dragonest.domain.mapper

import com.dragonest.domain.model.CardInfoModel
import com.dragonest.domain.model.CardInfoType
import com.dragonest.domain.model.CardRequestModel
import com.dragonest.domain.model.ClovaOcrResponseModel

fun ClovaOcrResponseModel.toCardInfoModel() : List<CardInfoModel> {

    val list = mutableListOf<CardInfoModel>()

    val result = this

    if(result.name != null && !result.name.isEmpty()){
        result.name.map {
            list.add(
                CardInfoModel(
                    CardInfoType.NAME,
                    it
                )
            )
        }
    }

    if(result.company != null && !result.company.isEmpty()){
        result.company.map {
            list.add(
                CardInfoModel(
                    CardInfoType.COMPANY,
                    it
                )
            )
        }
    }

    if(result.position != null && !result.position.isEmpty()){
        result.position.map {
            list.add(
                CardInfoModel(
                    CardInfoType.POSITION,
                    it
                )
            )
        }
    }

    if(result.department != null && !result.department.isEmpty()){
        result.department.map {
            list.add(
                CardInfoModel(
                    CardInfoType.DEPARTMENT,
                    it
                )
            )
        }
    }

    if(result.mobile != null && !result.mobile.isEmpty()){
        result.mobile.map {
            list.add(
                CardInfoModel(
                    CardInfoType.MOBILE,
                    it
                )
            )
        }
    }

    if(result.tel != null && !result.tel.isEmpty()){
        result.tel.map {
            list.add(
                CardInfoModel(
                    CardInfoType.TEL,
                    it
                )
            )
        }
    }

    if(result.email != null && !result.email.isEmpty()){
        result.email.map {
            list.add(
                CardInfoModel(
                    CardInfoType.EMAIL,
                    it
                )
            )
        }
    }

    if(result.homepage != null && !result.homepage.isEmpty()){
        result.homepage.map {
            list.add(
                CardInfoModel(
                    CardInfoType.HOMEPAGE,
                    it
                )
            )
        }
    }

    if(result.address != null && !result.address.isEmpty()){
        result.address.map {
            list.add(
                CardInfoModel(
                    CardInfoType.ADDRESS,
                    it
                )
            )
        }
    }

    if(result.fax != null && !result.fax.isEmpty()){
        result.fax.map {
            list.add(
                CardInfoModel(
                    CardInfoType.FAX,
                    it
                )
            )
        }
    }

    return list

}

fun ClovaOcrResponseModel.toCardRequestModel() : CardRequestModel {

    val cardRequestModel = CardRequestModel(
        name = name?.joinToString(",") ?: "",
        position = position?.joinToString(",") ?: "",
        department = department?.joinToString(",") ?: "",
        mobile = mobile?.joinToString(",") ?: "",
        company = company?.joinToString(",") ?: "",
        tel = tel?.joinToString(",") ?: "",
        address = address?.joinToString(",") ?: "",
        fax = fax?.joinToString(",") ?: "",
        homepage = homepage?.joinToString(",") ?: "",
        email = email?.joinToString(",") ?: "",
    )

    return cardRequestModel
}