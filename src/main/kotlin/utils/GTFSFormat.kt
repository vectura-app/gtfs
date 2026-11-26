package utils

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime

val GTFSFormat = LocalDate.Format {
    year()
    monthNumber()
    dayOfMonth()
}

fun parseGtfsTime(raw: String): LocalTime {
    val parts = raw.split(":")
    return LocalTime(parts[0].toInt() % 24, parts[1].toInt(), parts[2].toInt())
}