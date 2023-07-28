package com.team8.kiosk.options

import com.team8.kiosk.utils.Util.toPriceFormat

//소옵션 ex) 라지,스몰 등
data class Option(
    val name: String,//소옵션의 이름
    val price: Int,// 소옵션의 가격
) {
    override fun toString(): String {  //옵션 출력시 객체자체만 출력했을경우 어떻게 보여줄지 도와줌
        return "$name / ${price.toPriceFormat()}원"
    }
}