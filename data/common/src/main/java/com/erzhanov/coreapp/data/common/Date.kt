package com.erzhanov.coreapp.data.common

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatterBuilder
import java.time.temporal.ChronoField
import java.time.temporal.TemporalAdjuster
import java.time.temporal.TemporalAdjusters
import java.time.temporal.WeekFields
import java.util.Locale

val utcTimeZone: ZoneId = ZoneId.of("UTC")

private val defDateFormatter = DateTimeFormatter.ofPattern("MMM dd, yyyy")
private val serverDateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
private val serverDateTimeFormatter = DateTimeFormatterBuilder()
    .appendPattern("yyyy-MM-dd'T'HH:mm:ss")
    .appendFraction(ChronoField.MILLI_OF_SECOND, 3, 6, true)
    .toFormatter()
private val serverDateTimeFormatterIncome = DateTimeFormatterBuilder()
    .appendPattern("yyyy-MM-dd'T'HH:mm:ss")
    .appendFraction(ChronoField.MILLI_OF_SECOND, 0, 6, true)
    .toFormatter()

object Temporal {
    val firstDayOfWeek: TemporalAdjuster
        get() = TemporalAdjusters.previousOrSame(WeekFields.of(Locale.getDefault()).firstDayOfWeek)
}

val LocalDateTime.asUtcTimestamp: Long
    get() = atZone(ZoneId.systemDefault())
        .withZoneSameInstant(utcTimeZone)
        .toInstant()
        .toEpochMilli()

val Long.asLocalDateTime: LocalDateTime
    get() {
        return Instant.ofEpochMilli(this)
            .atZone(utcTimeZone)
            .withZoneSameInstant(ZoneId.systemDefault())
            .toLocalDateTime()
    }
val Long.asTimeDefString: String
    get() {
        return Instant.ofEpochMilli(this)
            .atZone(utcTimeZone)
            .withZoneSameInstant(ZoneId.systemDefault())
            .format(defDateFormatter)
    }

val LocalDateTime.asUtcString: String
    get() =  atZone(ZoneId.systemDefault())
        .withZoneSameInstant(utcTimeZone)
        .format(serverDateTimeFormatter)

val LocalDateTime.asDefString: String
    get() = atZone(ZoneId.systemDefault())
        .format(defDateFormatter)

/**
 * @param dateTimeString String in format "yyyy-MM-ddTHH:mm:ss" to parse
 */
fun localDateTimeFromString(dateTimeString: String): LocalDateTime {
    return LocalDateTime.parse(dateTimeString, serverDateTimeFormatterIncome)
        .atZone(utcTimeZone)
        .withZoneSameInstant(ZoneId.systemDefault())
        .toLocalDateTime()
}