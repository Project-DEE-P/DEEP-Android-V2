package com.dragonest.domain.model

data class CardResponseModel(
    var isTemplete : Boolean,
    var id: Long,
    var uid: String,
    var name : String,
    var company : String,
    var address : String,
    var position : String,
    var mobile : String,
    var tel : String,
    var email : String,
    var fax : String,
    var homepage : String,
    var department : String,
    var image : String?,
    var createdDateTime: String,
    var modifiedDateTime: String
)
