package app.example.mvvm.transporte.common.views.utils.date

import java.util.*

class Duration(internal val unit: Int, internal val value: Int) {

    private val calendar: Calendar by lazy { Calendar.getInstance() }

    val ago = calculate(from = Date(), value = -value)

    val sinceNow = calculate(from = Date(), value = value)

    private fun calculate(from: Date, value: Int): Date {
        calendar.time = from
        calendar.add(unit, value)
        return calendar.time
    }

    override fun hashCode() = Objects.hashCode(unit) * Objects.hashCode(value)

    override fun equals(other: Any?): Boolean {
        if (other == null || other !is Duration) {
            return false
        }
        return unit == other.unit && value == other.value
    }
}