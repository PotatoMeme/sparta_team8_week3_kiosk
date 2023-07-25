package com.team8.kiosk

class User(
    val name: String, // 사용자 이름
    var remainMoney: Int // 남은 돈

) {
    private val carts: MutableList<Cart> = mutableListOf() // 장바구니

    fun shopping() { // 쇼핑하기 로직
        println("${name}님 ! 맥도날드에 ${remainMoney}원을 가지고 오신 것을 환영합니다. 주문을 선택해주세요.")
        println("[1]포장 , [2]매장식사")
    val sel = readLine()?.toIntOrNull()

    if (sel in 1..2) {
        if (sel == 1) {
            println("1.포장을 선택하셨습니다. 메뉴 선택화면으로 넘어갑니다.")
            val sel1 = "[포장]"
            // 포장 영수증 출력 로직 (sel1 변수 활용)
        } else if (sel == 2) {
            println("2.매장식사를 선택하셨습니다. 메뉴 선택화면으로 넘어갑니다.")
            val sel2 = "[매장식사]"
            // 매장식사 영수증 출력 로직 (sel2 변수 활용)
        }


    } else {
        println("잘못된 선택입니다. 1 또는 2를 입력해주세요. (1.포장 2.매장식사)")
    }
        //메뉴 선택화면
        println("메뉴 선택 : [1]버거 [2]음료 [3]사이드")
        var menuList = readLine()?.toIntOrNull()
        if (menuList in 1..3) {
            when(menuList) {
                1 -> {
                    println("버거를 선택하셨습니다.")

                    //버거정보 출력
                    val burgers= Menu.menuArr[0]
                    for ((index, burger) in burgers.withIndex()) {
                        println("${index + 1}. ${burger.name} - ${burger.price}원")
                    }
                }
            }
        }
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
}