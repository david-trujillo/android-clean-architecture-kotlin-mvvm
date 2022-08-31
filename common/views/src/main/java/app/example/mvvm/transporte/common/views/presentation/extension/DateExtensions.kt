package app.example.mvvm.transporte.common.views.presentation.extension

import app.example.mvvm.transporte.common.views.utils.date.DateRange
import app.example.mvvm.transporte.common.views.utils.date.Duration
import java.text.SimpleDateFormat
import java.util.*

private val calendar: Calendar by lazy { Calendar.getInstance() }

fun Date.getMonthRange() = DateRange(startOfMonth, endOfMonth)

fun Date.getYearRange() = DateRange(startOfYear, endOfYear)

val Date.startOfMonth: Date
    get() = adjust(day = 1, hour = 0, minute = 0, second = 0, millisecond = 0)

val Date.endOfMonth: Date
    get() {
        calendar.time = this
        val lastDay = calendar.getActualMaximum(Calendar.DATE)
        return adjust(day = lastDay, hour = 23, minute = 59, second = 59, millisecond = 999)
    }

val Date.startOfYear: Date
    get() = adjust(month = 1, day = 1, hour = 0, minute = 0, second = 0, millisecond = 0)

val Date.endOfYear: Date
    get() = adjust(month = 12, day = 31, hour = 23, minute = 59, second = 59, millisecond = 999)

operator fun Date.plus(duration: Duration): Date {
    calendar.time = this
    calendar.add(duration.unit, duration.value)
    return calendar.time
}

operator fun Date.minus(duration: Duration): Date {
    calendar.time = this
    calendar.add(duration.unit, -duration.value)
    return calendar.time
}

fun Date.adjust(
    year: Int? = null,
    month: Int? = null,
    day: Int? = null,
    hour: Int? = null,
    minute: Int? = null,
    second: Int? = null,
    millisecond: Int? = null
): Date {
    calendar.time = this
    if (year != null) calendar.set(Calendar.YEAR, year)
    if (month != null) calendar.set(Calendar.MONTH, month - 1)
    if (day != null) calendar.set(Calendar.DATE, day)
    if (hour != null) calendar.set(Calendar.HOUR_OF_DAY, hour)
    if (minute != null) calendar.set(Calendar.MINUTE, minute)
    if (second != null) calendar.set(Calendar.SECOND, second)
    if (millisecond != null) calendar.set(Calendar.MILLISECOND, millisecond)
    return calendar.time
}

infix fun Date.isTheSameDayAs(other: Date): Boolean {
    calendar.time = this
    val otherCalendar = Calendar.getInstance().apply { time = other }
    return calendar.get(Calendar.YEAR) == otherCalendar.get(Calendar.YEAR) &&
            calendar.get(Calendar.MONTH) == otherCalendar.get(Calendar.MONTH) &&
            calendar.get(Calendar.DAY_OF_MONTH) == otherCalendar.get(Calendar.DAY_OF_MONTH)
}

fun Date.format(pattern: String, locale: Locale = Locale.getDefault()): String =
    SimpleDateFormat(pattern, locale).format(this)

fun String.toDate(pattern: String, locale: Locale = Locale.getDefault()): Date? =
    SimpleDateFormat(pattern, locale).parse(this)

fun Int.toYear() = Duration(unit = Calendar.YEAR, value = this)

fun Int.toMonth() = Duration(unit = Calendar.MONTH, value = this)

fun Int.toWeek() = Duration(unit = Calendar.WEEK_OF_MONTH, value = this)

fun Int.toDay() = Duration(unit = Calendar.DATE, value = this)

fun Int.toHour() = Duration(unit = Calendar.HOUR_OF_DAY, value = this)

fun Int.toMinute() = Duration(unit = Calendar.MINUTE, value = this)

fun Int.toSecond() = Duration(unit = Calendar.SECOND, value = this)

fun Int.toMillisecond() = Duration(unit = Calendar.MILLISECOND, value = this)

fun now() = Date()

fun today() = Date()

fun yesterday() = today() - 1.toDay()