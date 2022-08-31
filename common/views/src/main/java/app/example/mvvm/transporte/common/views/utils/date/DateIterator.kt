package app.example.mvvm.transporte.common.views.utils.date

import java.util.*

class DateIterator(
    startDate: Date,
    private val endDate: Date,
    private val stepDays: Int
) : Iterator<Date> {

    private val calendar = Calendar.getInstance(Locale.getDefault())

    private var currentDate = startDate

    override fun hasNext() = currentDate <= endDate

    override fun next(): Date {
        val next = currentDate

        calendar.time = currentDate
        calendar.add(Calendar.DATE, stepDays)
        currentDate = calendar.time

        return next
    }
}