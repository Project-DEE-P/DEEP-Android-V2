package com.dragonest.data.network.response

data class TemplateCardResponse(

    val id: Long,
    val uid: String,
    val template: String,
    val name: String,
    val position: String,
    val department: String,
    val phone: String,
    val email: String,
    val github: String,
    val image : String,
    val createdDateTime: String,
    val modifiedDateTime: String
)
