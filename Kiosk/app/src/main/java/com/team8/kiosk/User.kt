package com.team8.kiosk
import com.team8.kiosk.utils.Util.toPriceFormat


class User(
    val name: String, // 사용자 이름
    private var remainMoney: Int, // 남은 돈
) {
    private val carts: MutableList<Cart> = mutableListOf() // 장바구니

    fun shopping() { // 쇼핑하기 로직
        println("▶ 맥도날드에 오신 것을 환영합니다:)")
        println("▶ 원하시는 주문방법을 선택해주세요.")
        println("  ")
        println("  [1] 포장  [2] 매장식사")
        var sel: Int? = null
        while (sel !in 1..2) {
            sel = readLine()?.toIntOrNull()

            if (sel !in 1..2) {
                println("※ 잘못된 선택입니다. ")
                println("  (숫자 1 또는 2를 입력해주세요.)")
                println(" ")
                println("▶ 원하시는 주문방법을 선택해주세요.")
                println("  [1] 포장  [2] 매장식사")
            }
        }

        // 올바른 선택 후 메시지 출력
        if (sel == 1) {
            println("  [1] 포장을 선택하셨습니다.")
            println(" ")
            val sel1 = "[포장]"
            // 포장 영수증 출력 로직 (sel1 변수 활용)
        } else if (sel == 2) {
            println("  [2] 매장식사를 선택하셨습니다.")
            println(" ")
            val sel2 = "[매장식사]"
            // 매장식사 영수증 출력 로직 (sel2 변수 활용)
        }
        //--------------여기까지 포장선택임----------------
//---------------------메뉴선택화면시작-----------------
        while (true) {
            println("────────────── 메  뉴 ──────────────")
            println(" [0] 햄버거   [1] 음 료  [2] 사이드 ")
            println("────────────── 옵  션 ──────────────")
            println(" [3] 장바구니 [4] 결 제   [5] 충 전 ")
            println("                         [6] 종 료 ")
            println("───────────────────────────────────")
//            println("이중 원하시는 기능을 선택해주세요!")
//            println("[0] 버거선택 / [1] 드링크 선택 / [2] 사이드 선택 / [3] 상태보기 및 수정 / [4] 결제 / [5] 충전 / [6] 취소 ")
            when (readLine()!!.toIntOrNull()) {
                MENU_BUGGER -> selectItem(MENU_BUGGER)
                MENU_DRINK -> selectItem(MENU_DRINK)
                MENU_SIDE -> selectItem(MENU_SIDE)
                MENU_STATE -> selectState()
                MENU_PURCHASE -> selectPurchase()
                MENU_FILL -> selectFill()
                MENU_EXIT -> break
                else -> {
                    println("입력이 양식에 맞지않습니다 값을 다시 확인해주세요!")
                    continue
                }
            }
        }
        println("방문해주셔서 감사합니다. 다음에도 좋은 서비스를제공하는 멕도날드가 되겠습니다.")
    }

    private fun selectItem(menuIndex: Int) { // 상품 선택시
        println(
            "─────────────── ${
                when (menuIndex) {
                    MENU_BUGGER -> "Burger"
                    MENU_DRINK -> "Drink"
                    MENU_SIDE -> "Side"
                    else -> ""
                }
            }─────────────── "
        )



        Menu.menuArr[menuIndex].forEachIndexed { index, item ->
            println("${index + 1}. $item")//변수로 잡고 길이를 딴다음에 최댓값구하기 문자열 확장함수중 스플릿 이라고 잇음. 그걸로 아이템을 나누게 되면 빅맥과 3천원이 떨어짐 그럼 그걸 가지고 빅맥까지는 믹백하고 3천원인거랑 (이름 최댓값 , 돈중에서 최댓값이랑 구한다음에 출력을 할때 이름에서 최댓값인 만큼 스페이스를 넣어준다거나 그럼 됨
        }//tostring ,아이템 수정 후 출력 ( 최댓값 //길이 )구하기
        println("0. 뒤로가기")
        println("─────원하시는 메뉴를 선택해주세요─────")
        while (true) {
            when (val num = readLine()!!.toIntOrNull()) {
                null -> println("입력이 양식에 맞지않습니다 값을 다시 확인해주세요!")//스마트캐스팅용
                ITEM_BACK -> return
                in 1..Menu.menuArr[menuIndex].size -> {
                    val itemIndex: Int = num - 1
                    val currentSide = Menu.menuArr[menuIndex][itemIndex].getInstance()
                    currentSide.showOptionsArr()
                    println(currentSide.getCurrentState())
                    println(
                        "해당 ${
                            when (menuIndex) {
                                MENU_BUGGER -> "버거"
                                MENU_DRINK -> "드링크"
                                MENU_SIDE -> "사이드"
                                else -> ""
                            }
                        }를 카트에 추가할까요? [y]"
                    )
                    val input = readLine()
                    if (input == "y") addCart(Cart(currentSide, 1))
                    return
                }

                else -> println("입력이 양식에 맞지않습니다 값을 다시 확인해주세요!")
            }
        }
    }

    private fun selectState() {
        while (true) {
            if (carts.size == 0) {
                println("현재 장바구니가 비어있습니다.")
                return
            }
            println("변경하려는 품목을 선택해주세요")
            showCurrentCart()
            println("0. 뒤로가기")
            when (val num = readLine()!!.toIntOrNull()) {
                null -> println("입력이 양식에 맞지않습니다 값을 다시 확인해주세요!")//스마트캐스팅용
                STATE_BACK -> return
                in 1..carts.size -> {
                    val cartIndex: Int = num - 1
                    while (true) {
                        println("현재 선택 항목 : $num ${carts[cartIndex].item}")
                        println("원하는 기능을 선택해주세요")
                        println("[0] 옵션 변경 / [1] 수량 변경 / [2] 장바구니 삭제 / [3] 취소")
                        when (readLine()!!.toIntOrNull()) {
                            null -> println("입력이 양식에 맞지않습니다 값을 다시 확인해주세요!")//스마트캐스팅용
                            MODIFY_OPTION -> {
                                carts[cartIndex].item.showOptionsArr()
                                showCurrentCart()
                            }

                            MODIFY_COUNT -> {
                                println("변경 수량을 입력해주세요")
                                while (true) {
                                    when (val count = readLine()!!.toIntOrNull()) {
                                        null -> println("입력이 양식에 맞지않습니다 값을 다시 확인해주세요!")
                                        else -> {
                                            println("값을 변경합니다")
                                            carts[cartIndex].setItemCount(count)
                                            break
                                        }
                                    }
                                }
                                showCurrentCart()
                            }

                            MODIFY_REMOVE -> {
                                println("해당 상품을 삭제합니다")
                                remoevCart(cartIndex)
                                break
                            }

                            MODIFY_BACK -> break
                            else -> println("입력이 양식에 맞지않습니다 값을 다시 확인해주세요!")
                        }
                    }
                }

                else -> println("입력이 양식에 맞지않습니다 값을 다시 확인해주세요!")
            }
        }
    }

    private fun showCurrentCart(): Int {
        println("[ 현재 장바구니 ]")
        var sum = 0
        carts.forEachIndexed { index, cart ->
            println(
                "${index + 1}. " +
                        "${cart.item.getCurrentState()} ${cart.getItemCount()}개 " +
                        "/ 1개당 가격 : ${cart.item.getCurrentPrice().toPriceFormat()}원  " +
                        "/ 총합 : ${cart.getCurrentPrice().toPriceFormat()}원"
            )
            sum += cart.getCurrentPrice()
        }
        println("총합 : ${sum.toPriceFormat()}원 입니다")
        return sum
    }

    private fun selectPurchase() {
        if (carts.size == 0) {
            println("현재 장바구니가 비어있습니다.")
            return
        }
        val price = showCurrentCart()
        println("결제하시겠습니까? [y]")
        if (readLine() == "y") {
            if (remainMoney < price) {
                println("돈이부족하여 결제를 진행할수 없습니다.")
                println("현재돈 : ${remainMoney.toPriceFormat()}원")
                println("${(price - remainMoney).toPriceFormat()}원만큼 돈이 더 필요합니다.")
            } else {
                println("결제를 진행합니다.")
                remainMoney -= price
                println("남은 돈 : ${remainMoney.toPriceFormat()}원")
            }
        }
    }

    private fun selectFill() {
        println("현재돈 : ${remainMoney.toPriceFormat()}원")
        println("충전할 돈을 입력해주세요")
        println("0. 뒤로가기")
        while (true) {
            when (val count = readLine()!!.toIntOrNull()) {
                null -> println("입력이 양식에 맞지않습니다 값을 다시 확인해주세요!")
                FILL_BACK -> return
                else -> {
                    if (count < 0) {
                        println("음수는 입력이 되지않습니다.")
                        continue
                    }
                    println("${count.toPriceFormat()}원을 충전합니다.")
                    remainMoney += count

                    println("현재돈 : ${remainMoney.toPriceFormat()}원")
                    return
                }
            }
        }
    }


    private fun addCart(cart: Cart) { // 카트 추가
        carts.add(cart)
    }

    private fun remoevCart(index: Int) { // 카트 삭제
        carts.removeAt(index)
    }

    companion object {
        private const val MENU_BUGGER = 0
        private const val MENU_DRINK = 1
        private const val MENU_SIDE = 2
        private const val MENU_STATE = 3
        private const val MENU_PURCHASE = 4
        private const val MENU_FILL = 5
        private const val MENU_EXIT = 6

        private const val ITEM_BACK = 0

        private const val STATE_BACK = 0

        private const val MODIFY_OPTION = 0
        private const val MODIFY_COUNT = 1
        private const val MODIFY_REMOVE = 2
        private const val MODIFY_BACK = 3

        private const val FILL_BACK = 0
    }
}