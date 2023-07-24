package com.team8.kiosk

import com.team8.kiosk.items.Bugger
import com.team8.kiosk.items.Drink
import com.team8.kiosk.items.Item
import com.team8.kiosk.items.Side

object Menu {
    val menuArr: Array<Array<Item>> = arrayOf(
        arrayOf(
            Bugger("빅맥", 3_000),
            Bugger("1955버거", 3_500),
            Bugger("진도 대파 크림 크로켓 버거", 5_200),
            Bugger("상하이버거 런치", 3_000),
            Bugger("불고기버거", 1_000),
        ),//버거
        arrayOf(
            Drink("콜라", 2_500),
            Drink("제로 콜라", 2_700),
            Drink("스프라이트", 2_000),
            Drink("환타", 2_200),
            Drink("아메리카노", 2_500),
        ),//드링크
        arrayOf(
            Side("상하이 치킨 스낵랩", 2_000),
            Side("맥윙 2조각", 1_500),
            Side("맥너겟 4조각", 1_500),
            Side("맥너겟 6조각", 2_000),
            Side("맥너겟 10조각", 3_000),
        ),//사이드
    )
}