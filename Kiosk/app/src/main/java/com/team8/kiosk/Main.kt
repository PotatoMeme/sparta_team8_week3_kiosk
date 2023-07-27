package com.team8.kiosk

import android.os.Build
import androidx.annotation.RequiresApi
import com.team8.kiosk.utils.Util
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.Stack
import kotlin.concurrent.thread


@RequiresApi(Build.VERSION_CODES.O)
fun main() {
    println(Util.getCurrentTime())
    var count = 1
    val stack : Stack<User> = Stack()
    thread(true) {
        while (true){
            runBlocking {
                launch {
                    delay(8000)
                }
            }
            println("(현재 주문 대기수: ${stack.size})")
        }
    }
    while (true){
        val user: User = User("testUser${count++}", 1_000_000){ user ->
            stack.add(user)
        }
        user.shopping()

        runBlocking {
            GlobalScope.launch {
                delay(30000)
                stack.pop()
            }
            GlobalScope.launch {
                delay(3000)
            }.join()
        }

    }

}