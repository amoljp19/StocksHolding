package com.softaai.upstoxholding.holdings

import java.math.RoundingMode
import java.text.DecimalFormat

object Utils {
    fun roundOffDecimal(number: Double): Double {
        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.CEILING
        return df.format(number).toDouble()
    }
}