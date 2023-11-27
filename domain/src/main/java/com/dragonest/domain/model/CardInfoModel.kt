package com.dragonest.domain.model

data class CardInfoModel(

    val cardInfoType: CardInfoType,
    val content : String

)

enum class CardInfoType(
    val type : String,
    val isCopible : Boolean,
    val isAddress : Boolean,
    val isWebsite : Boolean
){

    NAME("이름",false,false,false),
    COMPANY("회사",false,false,false),
    POSITION("직책",false,false,false),
    DEPARTMENT("부서",false,false,false),
    MOBILE("모바일",false,true,false),
    TEL("전화",false,true,false),
    EMAIL("이메일",true,false,false),
    HOMEPAGE("홈페이지",false,false,true),
    ADDRESS("주소",true,false,false),
    FAX("팩스",true,false,false),

}
