package com.dragonest.domain.mapper

import com.dragonest.domain.model.CardInfoModel
import com.dragonest.domain.model.CardInfoType
import com.dragonest.domain.model.CardResponseModel

fun CardResponseModel.toModel() : List<CardInfoModel> {

    val list = mutableListOf<CardInfoModel>()

    this.let {

        val names = it.name.split(",")
        if(!names.isEmpty()){
            names.map {
                list.add(CardInfoModel(CardInfoType.NAME, it))
            }
        }

        val companies = it.company.split(",")
        if(!companies.isEmpty()){
            companies.map {
                list.add(CardInfoModel(CardInfoType.COMPANY, it))
            }
        }

        val positions = it.position.split(",")
        if(!positions.isEmpty()){
            positions.map {
                list.add(CardInfoModel(CardInfoType.POSITION, it))
            }
        }
        val departments = it.department.split(",")
        if(!departments.isEmpty()){
            departments.map {
                list.add(CardInfoModel(CardInfoType.DEPARTMENT, it))
            }
        }

        val mobiles = it.mobile.split(",")
        if(!mobiles.isEmpty()){
            mobiles.map {
                list.add(CardInfoModel(CardInfoType.MOBILE, it))
            }
        }

        val tels = it.tel.split(",")
        if(!tels.isEmpty()){
            tels.map {
                list.add(CardInfoModel(CardInfoType.TEL, it))
            }
        }

        val emails = it.email.split(",")
        if(!emails.isEmpty()){
            emails.map {
                list.add(CardInfoModel(CardInfoType.EMAIL, it))
            }
        }

        val homepages = it.homepage.split(",")
        if(!homepages.isEmpty()){
            homepages.map {
                list.add(CardInfoModel(CardInfoType.HOMEPAGE, it))
            }
        }

        val addresses = it.address.split(",")
        if(!addresses.isEmpty()){
            addresses.map {
                list.add(CardInfoModel(CardInfoType.ADDRESS, it))
            }
        }

        val faxes = it.fax.split(",")
        if(!faxes.isEmpty()){
            faxes.map {
                list.add(CardInfoModel(CardInfoType.FAX, it))
            }
        }
    }

    return list
}