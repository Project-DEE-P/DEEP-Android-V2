package com.dragonest.data.remote.util

data class BaseResponse<T>(

    val code : Int,
    val message : String,
    val data : T?

)