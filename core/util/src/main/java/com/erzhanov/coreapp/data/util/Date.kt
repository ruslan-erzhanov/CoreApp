package com.erzhanov.coreapp.data.util

import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.chrono.IsoChronology
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatterBuilder
import java.time.format.FormatStyle
import java.time.temporal.ChronoField
import java.time.temporal.TemporalAdjuster
import java.time.temporal.TemporalAdjusters
import java.time.temporal.WeekFields
import java.util.*

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

private val analyticsDateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

object Temporal {
    val firstDayOfWeek: TemporalAdjuster
        get() = TemporalAdjusters.previousOrSame(WeekFields.of(Locale.getDefault()).firstDayOfWeek)
}

fun localDateFromUtcTimestamp(timestamp: Long): LocalDate {
    return Instant.ofEpochMilli(timestamp).atZone(utcTimeZone).toLocalDate()
}

fun LocalDate.toUtcTimestamp(): Long {
    return atStartOfDay(utcTimeZone).toInstant().toEpochMilli()
}

fun LocalDate.toServerString(): String {
    return format(serverDateFormatter)
}

fun LocalDate.toAnalyticsString(): String {
    return format(analyticsDateFormatter)
}

fun LocalDateTime.toUtcTimestamp(): Long {
    return atZone(ZoneId.systemDefault())
        .withZoneSameInstant(utcTimeZone)
        .toInstant()
        .toEpochMilli()
}

fun localDateTimeFromUtcTimestamp(timestamp: Long): LocalDateTime {
    return Instant.ofEpochMilli(timestamp)
        .atZone(utcTimeZone)
        .withZoneSameInstant(ZoneId.systemDefault())
        .toLocalDateTime()
}

fun LocalDateTime.toUtcServerString(): String {
    return atZone(ZoneId.systemDefault())
        .withZoneSameInstant(utcTimeZone)
        .format(serverDateTimeFormatter)
}

fun LocalDateTime.toDefString(): String {
    return atZone(ZoneId.systemDefault())
        .format(defDateFormatter)
}

/**
 * @param dateTimeString String in format "yyyy-MM-ddTHH:mm:ss" to parse
 */
fun localDateTimeFromServerUtcDateTimeString(dateTimeString: String): LocalDateTime {
    return LocalDateTime.parse(dateTimeString, serverDateTimeFormatterIncome)
        .atZone(utcTimeZone)
        .withZoneSameInstant(ZoneId.systemDefault())
        .toLocalDateTime()
}

@Suppress("FunctionName")
object DayMonthDateFormatter {

    operator fun invoke(locale: Locale, style: FormatStyle = FormatStyle.LONG): DateTimeFormatter {
        // create LONG format without year
        val pattern: String = DateTimeFormatterBuilder.getLocalizedDateTimePattern(
            style,
            null,
            IsoChronology.INSTANCE,
            locale
        ).replace("((\\s?'\\s?de\\s?'\\s?)|[^dM]*)y[^dM]*".toRegex(), "")
        return DateTimeFormatter.ofPattern(pattern, locale)
    }
}
