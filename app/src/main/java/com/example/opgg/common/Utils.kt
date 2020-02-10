package com.example.opgg.common

import kotlin.math.pow
import kotlin.math.roundToInt

fun Double.roundUp(pointer: Int = 1): Double {
    val param = 10.toDouble().pow(pointer.toDouble())
    return (this * param).roundToInt().toDouble() / param
}

fun timePassed(timestamp: Long): String {
    val passedTime = System.currentTimeMillis() / 1000 - timestamp
    return when {
        passedTime < 60 -> {
            "1분 전"
        }
        passedTime < 60 * 60 -> {
            "${passedTime / 60}분 전"
        }
        passedTime < 60 * 60 * 24 -> {
            "${passedTime / (60 * 60)}시간 전"
        }
        else -> {
            "${passedTime / (60 * 60 * 24)}일 전"
        }
    }
}

fun calcWinRate(win: Int, lose: Int): Int {
    return if (win == 0 && lose == 0) {
        0
    } else {
        win * 100 / (win + lose)
    }

}

