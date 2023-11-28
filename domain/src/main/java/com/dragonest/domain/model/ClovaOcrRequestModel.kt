package com.dragonest.domain.model

data class ClovaOcrRequestModel(
    val image : ImageModel
)

data class ImageModel(
    val format : String,
    val name : String
)