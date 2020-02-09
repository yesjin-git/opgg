package com.example.opgg.common

import kotlin.math.pow
import kotlin.math.roundToInt

fun Double.roundUp(pointer: Int = 1): Double {
    val param = 10.toDouble().pow(pointer.toDouble())
    return (this * param).roundToInt().toDouble() / param
}

