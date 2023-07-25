package com.team8.kiosk.options

//옵션묶음
class Options(
    val name: String,//이름 ex) 사이즈선택
    val isMulti: Boolean, //다중선택이 가능한 옵션인지 확인용
    val isEssential: Boolean,//필수 선택인경우 확인
    val args: Array<Option>, //소옵션 묶음
) {
    fun showOption() { // 옵션들을 보여주는 함수 ex) 라지 1500원 ,  미디움 500원 , 스몰 0원
        println("[ $name ]")
        args.forEachIndexed { index, option ->
            println(
                "${index + 1} $option"
            )
        }
        if (!isEssential) println("0. 선택안함")
        if (isMulti) println("다중선택이 가능합니다 가능합니다.")
    }

    fun inputCheck(): IntArray {// 옵션선택결과를 입력받아 반환
        if (isMulti) {
            check@ while (true) {
                val input = readLine()!!.split(" ")
                if (!isEssential && input.size == 1 && input[0].toIntOrNull() == 0) return IntArray(0)
                for (i in input) {
                    when (i.toIntOrNull()) {
                        null, 0 -> {
                            println("입력이 양식에 맞지않습니다 값을 다시 확인해주세요!")
                            continue@check
                        }

                        in 1..args.size -> continue
                        else -> {
                            println("입력이 양식에 맞지않습니다 값을 다시 확인해주세요!")
                            continue@check
                        }
                    }
                }
                return input.map { it.toInt() - 1 }.toIntArray()
            }
        } else {
            while (true) {
                val input = readLine()!!.toIntOrNull()
                if (input == null) {
                    println("입력이 양식에 맞지않습니다 값을 다시 확인해주세요!")
                    continue
                }
                if (!isEssential && input == 0) return IntArray(0)
                if (input <= args.size && input > 0) {
                    return IntArray(1) { input - 1 }
                } else println("입력이 양식에 맞지않습니다 값을 다시 확인해주세요!")
            }
        }
    }
}