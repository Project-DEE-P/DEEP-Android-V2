package com.dragonest.data.network.response

data class ClovaOcrResponse(

    val version : String?,
    val requestId : String?,
    val timestamp : Long?,
    val images : List<Image>
)

data class Image(

    val nameCard : NameCard,
    val uid : String?,
    val name : String?,
    val inferResult : String?,
    val message : String?,
    val validationResult : ValidationResult?
)
data class ValidationResult(
    val result : String?
)

data class NameCard(
    val meta : Meta?,
    val result : Result
)
data class Meta(
    val estimatedLanguage : String?
)

data class Result(
    val name : List<Name>?,
    val company : List<Name>?,
    val address : List<Name>?,
    val position : List<Name>?,
    val mobile : List<Name>?,
    val tel : List<Name>?,
    val email : List<Name>?,
    val fax : List<Name>?,
    val homepage : List<Name>?,
    val nameFurigana : List<Name>?,
    val department : List<Name>?
)

data class Name(
    val text : String,
    val boundingPolys : List<BoundingPoly>?,
    val maskingPolys : List<Any>?
)

data class BoundingPoly(
    val vertices : List<Vertice>?
)

data class Vertice(
    val x : Float?,
    val y : Float?
)