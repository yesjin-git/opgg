package com.example.opgg.common

import android.os.Build
import android.text.Html
import android.text.SpannableString
import android.text.Spanned
import android.util.DisplayMetrics
import kotlin.math.pow
import kotlin.math.roundToInt


fun Double.roundUp(pointer: Int = 1): Double {
    val param = 10.toDouble().pow(pointer.toDouble())
    return (this * param).roundToInt().toDouble() / param
}

fun calcWinRate(win: Int, lose: Int): Int {
    return if (win == 0 && lose == 0) {
        0
    } else {
        win * 100 / (win + lose)
    }
}

fun convertDpToPixel(dp: Float, metrics: DisplayMetrics): Float {
    return dp * (metrics.densityDpi / 160f)

}
fun fromHtml(html: String?): Spanned? {
    return when {
        html == null -> { // return an empty spannable if the html is null
            SpannableString("")
        }
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.N -> { // FROM_HTML_MODE_LEGACY is the behaviour that was used for versions below android N
            Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY)
        }
        else -> {
            Html.fromHtml(html)
        }
    }
}
