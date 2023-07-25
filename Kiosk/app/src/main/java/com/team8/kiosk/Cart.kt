package com.team8.kiosk

import com.team8.kiosk.items.Item

// 단일 카트
data class Cart(
    val item: Item, //상품(버거,사이드,음료)
    private var count: Int, //상품의 개수
) {

    fun setItemCount(value: Int) { //상품의 개수를 수정할때 사용
        count = value
    }

    fun getItemCount() : Int { //상품의 개수를 수정할때 사용
        return count
    }

    fun getCurrentPrice(): Int { // 현재 단일카트의 총가격을 반환
        return 0
    }

}
