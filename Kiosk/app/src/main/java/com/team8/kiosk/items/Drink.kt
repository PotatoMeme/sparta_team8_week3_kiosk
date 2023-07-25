package com.team8.kiosk.items

import com.team8.kiosk.options.Option
import com.team8.kiosk.options.Options
import com.team8.kiosk.utils.Util.toPriceFormat

data class Drink(
    override val name: String,
    override val price: Int,
) : Item {
    override val optionsArr: Array<Options> = arrayOf(
        Options(
            "얼음",
            false,
            true,
            arrayOf(
                Option("기본", 0),
                Option("없음", 0)
            )
        ),
    )
    override val selectedArr: Array<IntArray> = Array(optionsArr.size) { IntArray(0) }

    override fun showOptionsArr() {
        println("옵션을 설정하시겠습니까? [y]")
        if (readLine() != "y") return
        for (i in selectedArr.indices) {
            selectedArr[i] = IntArray(0)
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
                if (count > 1) deleteCharAt(this.lastIndex)
                append(')')
            }
        }
    }

    override fun getInstance(): Item {
        return Drink(name, price)
    }

    override fun toString(): String { //메뉴 출력시 객체자체만 출력했을경우 어떻게 보여줄지 도와줌
        return "$name / ${price.toPriceFormat()}원"
    }
}
