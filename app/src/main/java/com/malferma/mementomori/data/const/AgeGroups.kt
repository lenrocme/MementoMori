package com.malferma.mementomori.data.const

data class AgeGroups(
    val noneAdult: Int = 0,
    val adult: Int = 20 * 12 + 1,
    val middleAgeAdult: Int = 30 * 12 + 1,
    val lateAdult: Int = 45 * 12 + 1,
    val senior: Int = 60 * 12 + 1,
    val passive: Int = 80 * 12 + 1,
)
