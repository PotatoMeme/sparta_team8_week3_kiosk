package com.team8.kiosk


fun main() {
    println("고객님 이름을 입력해주세요.")
    val userName = readLine()?: ""
    println("고객님의 소지금을 입력해주세요.(숫자만 입력해주세요.)")
    var remainMoney = readLine()?.toIntOrNull() ?: 0

    if (remainMoney<1000){
    println("죄송합니다 고객님, 소지금으로 살 수 있는 상품이 없습니다.충전하실 금액을 입력해주세요.")
    var addMoney =  readLine()?.toIntOrNull() ?: 0
        remainMoney += addMoney

}
    val user: User = User(userName, remainMoney)
    user.shopping()

}