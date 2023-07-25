package com.team8.kiosk

class User(
    val name: String, // 사용자 이름
    var remainMoney: Int, // 남은 돈
) {
    private val carts: MutableList<Cart> = mutableListOf() // 장바구니

    fun shopping() { // 쇼핑하기 로직

    }

    fun purchase() { // 구매 로직

    }

    fun addCart(cart: Cart) { // 카트 추가
        carts.add(cart)
    }

    fun remoevCart(index: Int) { // 카트 삭제
        carts.removeAt(index)
    }

    fun updateCart(index: Int) { // 카드 수정

    }
    fun remainingMoney(): Int {  //추가함..ㅠㅠ..(진주)
        return remainMoney
    }
}