package app.example.mvvm.transporte.common.views.utils.date

import java.util.*

class DateRange(
    override val start: Date,
    override val endInclusive: Date,
    private val stepDays: Int = 1
) : Iterable<Date>, ClosedRange<Date> {

    override fun iterator(): Iterator<Date> = DateIterator(start, endInclusive, stepDays)

    infix fun step(days: Int) = DateRange(start, endInclusive, days)

    companion object {
        val EMPTY: DateRange = DateRange(Date(1), Date(0))
    }
}