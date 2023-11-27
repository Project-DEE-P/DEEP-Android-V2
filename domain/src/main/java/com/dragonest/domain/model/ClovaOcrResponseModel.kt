package com.dragonest.domain.model

data class ClovaOcrDto(

    val version : String?,
    val requestId : String?,
    val timestamp : Long?,
    val images : List<ImageDto>

)

data class ImageDto(

    val nameCard : NameCardDto,
    val uid : String?,
    val name : String?,
    val inferResult : String?,
    val message : String?,
    val validationResult : ValidationResultDto?

)
data class ValidationResultDto(
    val result : String?
)

data class NameCardDto(
    val meta : MetaDto?,
    val result : ResultDto
)
data class MetaDto(
    val estimatedLanguage : String?
)

data class ResultDto(
    val name : List<NameDto>?,
    val company : List<NameDto>?,
    val address : List<NameDto>?,
    val position : List<NameDto>?,
    val mobile : List<NameDto>?,
    val tel : List<NameDto>?,
    val email : List<NameDto>?,
    val fax : List<NameDto>?,
    val homepage : List<NameDto>?,
    val nameFurigana : List<NameDto>?,
    val department : List<NameDto>?
)

data class NameDto(
    val text : String,
    val boundingPolys : List<BoundingPolyDto>?,
    val maskingPolys : List<Any>?
)

data class BoundingPolyDto(
    val vertices : List<VerticeDto>?
)

data class VerticeDto(
    val x : Float?,
    val y : Float?
)