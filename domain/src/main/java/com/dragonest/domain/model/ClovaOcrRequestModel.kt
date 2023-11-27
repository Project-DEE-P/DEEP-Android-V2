package com.dragonest.domain.model

data class MessageModel(
    val version : String = "V2",
    val requestId : String = "deep-card-ocr",
    val timestamp : Long,
    val images : List<ImageModel>
)

data class ImageModel(
    val format : String,
    val name : String
)