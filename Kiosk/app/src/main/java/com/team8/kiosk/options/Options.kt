package com.team8.kiosk.options

//옵션묶음
class Options(
    val name: String,//이름 ex) 사이즈선택
    val isMulti: Boolean, //다중선택이 가능한 옵션인지 확인용
    val args: Array<Option>, //소옵션 묶음
) {
    fun showOption() { // 옵션들을 보여주는 함수 ex) 라지 1500원 ,  미디움 500원 , 스몰 0원

    }

    fun inputCheck(): IntArray {// 옵션선택결과를 입력받아 반환
        return IntArray(0)
    }
}