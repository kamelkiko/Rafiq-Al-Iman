package com.kamel.rafiq_al_iman.util

import java.time.LocalTime
import java.time.format.DateTimeFormatter

private const val PATTERN_12_HOUR_CLOCK = "hh:mm a"

fun LocalTime.formatWith12HourClock(): String {
    return this.format(DateTimeFormatter.ofPattern(PATTERN_12_HOUR_CLOCK))
}