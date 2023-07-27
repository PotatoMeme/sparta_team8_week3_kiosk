package com.team8.kiosk.items

import com.team8.kiosk.options.Options

interface Item{//상품들의 틀
    val name : String // 상품의 이름
    val price : Int // 상품의 이름
    val optionsArr : Array<Options> // 해당 아이템의 옵션 묶음
    val selectedArr : Array<IntArray> // 선택결과

    fun showOptionsArr() // 옵션을 보여주는 함수
    fun getCurrentPrice() : Int // 현재 선택 결과에따른 가격을 변환
    fun getCurrentState() : String// 현재 선택 결과에따른 상태를 문자열로 변환

    fun getInstance() : Item // 대표객체들이 오브잭트에있어 복사가힘듬 해당값들을 복사할 함수
}
