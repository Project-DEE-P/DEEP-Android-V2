package com.dragonest.data.network.request

data class ClovaOcrRequest(
    val version : String = "V2",
    val requestId : String = "deep-card-ocr",
    val timestamp : Long,
    val images : List<Image>
)

data class Image(
    val format : String,
    val name : String
)