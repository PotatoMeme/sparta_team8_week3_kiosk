package com.team8.kiosk.utils

import java.text.DecimalFormat

object Util {
    fun Int.toPriceFormat(): String {
        return DecimalFormat("#,###").format(this)
    }
}