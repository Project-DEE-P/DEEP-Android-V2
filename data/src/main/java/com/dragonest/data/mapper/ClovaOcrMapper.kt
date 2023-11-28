package com.dragonest.data.mapper

import com.dragonest.data.network.request.ClovaOcrRequest
import com.dragonest.data.network.request.Image
import com.dragonest.data.network.response.BoundingPoly
import com.dragonest.data.network.response.ClovaOcrResponse
import com.dragonest.data.network.response.Meta
import com.dragonest.data.network.response.Name
import com.dragonest.data.network.response.NameCard
import com.dragonest.data.network.response.Result
import com.dragonest.data.network.response.ValidationResult
import com.dragonest.data.network.response.Vertice
import com.dragonest.domain.model.CardInfoModel
import com.dragonest.domain.model.CardInfoType
import com.dragonest.domain.model.ClovaOcrRequestModel
import com.dragonest.domain.model.ClovaOcrResponseModel
import com.dragonest.domain.model.ImageModel

fun ClovaOcrRequestModel.toDto() = ClovaOcrRequest(

    images = listOf(this.image.toDto()),
    timestamp = 1


)
fun ImageModel.toDto() = Image(
    format = this.format,
    name = this.name
)


fun ClovaOcrResponse.toModel() = ClovaOcrResponseModel(
    name = this.images[0].nameCard.result.name?.map { it.text },
    company = this.images[0].nameCard.result.company?.map { it.text },
    position = this.images[0].nameCard.result.position?.map { it.text },
    department = this.images[0].nameCard.result.department?.map { it.text },
    mobile = this.images[0].nameCard.result.mobile?.map { it.text },
    tel = this.images[0].nameCard.result.tel?.map { it.text },
    email = this.images[0].nameCard.result.email?.map { it.text },
    homepage = this.images[0].nameCard.result.homepage?.map { it.text },
    address = this.images[0].nameCard.result.address?.map { it.text },
    fax = this.images[0].nameCard.result.fax?.map { it.text },
    nameFurigana = null

)

