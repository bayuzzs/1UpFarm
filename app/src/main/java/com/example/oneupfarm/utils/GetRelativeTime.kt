package com.example.oneupfarm.utils

import java.time.LocalDate
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.time.temporal.ChronoUnit

fun getRelativeTime(dateString: String): String {
    return try {
        // Parse ISO 8601 format to LocalDate
        val inputDate = ZonedDateTime.parse(dateString).toLocalDate()

        val currentDate = LocalDate.now()
        val daysBetween = ChronoUnit.DAYS.between(inputDate, currentDate)
        val weeksBetween = ChronoUnit.WEEKS.between(inputDate, currentDate)
        val monthsBetween = ChronoUnit.MONTHS.between(inputDate, currentDate)
        val yearsBetween = ChronoUnit.YEARS.between(inputDate, currentDate)

        when {
            yearsBetween > 0 -> "$yearsBetween Tahun"
            monthsBetween > 0 -> "$monthsBetween Bulan"
            weeksBetween > 0 -> "$weeksBetween Minggu"
            daysBetween > 0 -> "$daysBetween Hari"
            daysBetween == 0L -> "Hari ini"
            else -> "Waktu tidak valid"
        }
    } catch (e: DateTimeParseException) {
        "Format tanggal tidak valid"
    }
}
