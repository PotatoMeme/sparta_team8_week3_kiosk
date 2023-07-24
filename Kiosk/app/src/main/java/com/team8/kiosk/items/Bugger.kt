package com.team8.kiosk.items

import com.team8.kiosk.options.Options

data class Bugger(
    override val name: String,
    override val price: Int,
) : Item {
    override val optionsArr: Array<Options> = arrayOf()
    override val selectedArr: Array<IntArray> = Array(optionsArr.size) { IntArray(0) }

    override fun showOptionsArr() {
        TODO("Not yet implemented")
    }

    override fun getCurrentPrice(): Int {
        TODO("Not yet implemented")
    }

    override fun getCurrentState(): String {
        TODO("Not yet implemented")
    }

    override fun toString(): String { //메뉴 출력시 객체자체만 출력했을경우 어떻게 보여줄지 도와줌
        return super.toString()
    }
}
