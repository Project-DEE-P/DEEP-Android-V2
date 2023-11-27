package com.dragonest.data.network.request

data class PostCardRequest(

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
    var image : String = ""

)
