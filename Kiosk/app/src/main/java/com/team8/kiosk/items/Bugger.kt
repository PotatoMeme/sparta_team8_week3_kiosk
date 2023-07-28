package com.team8.kiosk.items

import com.team8.kiosk.options.Option
import com.team8.kiosk.options.Options
import com.team8.kiosk.utils.Util.toPriceFormat
//데이터 클래스는 출력을 할 경우, 얘 양식대로 출력하게 됨. 빗금(/)은 여기서 나온거임.
// tosh
data class Bugger(
    override val name: String,
    override val price: Int,
) : Item {
    override val optionsArr: Array<Options> = arrayOf(
        Options(
            "세트메뉴",
            false,
            false,
            arrayOf(
                Option("일반세트(음료M,감자튀김M)", 1000),
                Option("라지세트(음료L,감자튀김L)", 1500)
            )
        ),
        Options(//일반세트 선택시
            "사이드 변경",
            false,
            false,
            arrayOf(
                Option("포테이토L", 300),
                Option("웨지 포테이토", 300),
                Option("모짜 크림 치즈볼", 500),
                Option("콘 샐러드", 100),
                Option("치즈 스틱", 600),
                Option("통 오징어 링", 800),
                Option("맥너겟", 400),
            )
        ),
        Options(//라지세트 선택시
            "사이드 변경",
            false,
            false,
            arrayOf(
                Option("웨지 포테이토", 0),
                Option("모짜 크림 치즈볼", 300),
                Option("콘 샐러드", 0),
                Option("치즈 스틱", 400),
                Option("통 오징어 링", 600),
                Option("맥너겟", 200),
            )
        ),
        Options(//일반세트 선택시
            "음료 선택",
            false,
            true,
            arrayOf(
                Option("콜라 M", 0),
                Option("콜라 L", 300),
                Option("사이다 M", 0),
                Option("사이다 L", 300),
                Option("제로 콜라 M", 0),
                Option("제로 콜라 L", 300),
                Option("아메리카노 M", 200),
                Option("아메리카노 L", 500),
            )
        ),
        Options(//라지세트 선택시
            "음료 선택",
            false,
            true,
            arrayOf(
                Option("콜라 L", 0),
                Option("사이다 L", 0),
                Option("제로 콜라 L", 0),
                Option("아메리카노 L", 200),
            )
        ),
        Options(
            "토핑 추가,제거",
            true,
            false,
            arrayOf(
                Option("야채 빼기", 0),
                Option("고기 뺴기", 0),
                Option("치즈 추가", 600),
                Option("토마토 추가", 500),
                Option("베이컨 추가", 700),
            )
        )
    )
    override val selectedArr: Array<IntArray> = Array(optionsArr.size) { IntArray(0) }

    override fun showOptionsArr() {
        println("───────────── Upgrade ─────────────")
        println("   [1] 세트          [아무키] 단품")
        println("───────────────────────────────────")
        println("  세트를 원하시면 1번 ")
        println("  ※단품을 원하시면 [아무키]나 눌러주세요.")
        if (readLine() != "1") return //나가고싶으면 아무버튼이나 누르면됨
        for (i in selectedArr.indices) {
            selectedArr[i] = IntArray(0)
            if (i == 1 && !(selectedArr[0].isNotEmpty() && selectedArr[0][0] == 0)) continue//일반 세트 선택시만
            if (i == 2 && !(selectedArr[0].isNotEmpty() && selectedArr[0][0] == 1)) continue//라지 세트 선택시만
            if (i == 3 && !(selectedArr[0].isNotEmpty() && selectedArr[0][0] == 0)) continue//일반 세트 선택시만
            if (i == 4 && !(selectedArr[0].isNotEmpty() && selectedArr[0][0] == 1)) continue//라지 세트 선택시만
            optionsArr[i].showOption()
            selectedArr[i] = optionsArr[i].inputCheck()
        }
    }

    override fun getCurrentPrice(): Int {
        var sum = price
        selectedArr.forEachIndexed { index1, ints ->
            ints.forEach { selected ->
                sum += optionsArr[index1].args[selected].price
            }
        }
        return sum
    }

    override fun getCurrentState(): String {
        return buildString {
            append(name)
            if (selectedArr.sumOf { it.size } != 0) {
                append("(")
                var count = 0
                selectedArr.forEachIndexed { index1, ints ->
                    ints.forEach { selected ->
                        count++
                        append(" ")
                        append(optionsArr[index1].name).append(" : ")
                            .append(optionsArr[index1].args[selected].name)
                            .append(" [+")
                            .append(optionsArr[index1].args[selected].price.toPriceFormat())
                            .append("] /")
                    }
                }
                if (count > 0) deleteCharAt(this.lastIndex)
                append(')')
            }
        }
    }

    override fun getInstance(): Item {
        return Bugger(name, price)
    }

    override fun toString(): String { //메뉴 출력시 객체자체만 출력했을경우 어떻게 보여줄지 도와줌
        return "$name / ${price.toPriceFormat()}원"
    } //이놈이 범인임
}