package com.team8.kiosk.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.text.DecimalFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object Util {
    fun Int.toPriceFormat(): String {
        return DecimalFormat("#,###").format(this)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getCurrentTime():String{
        val current = LocalDateTime.now()
        val fomatter = DateTimeFormatter.ofPattern("HH:mm")
        return current.format(fomatter)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getCurrentWithDate():String{
        val current = LocalDateTime.now()
        val fomatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
        return current.format(fomatter)
    }
}