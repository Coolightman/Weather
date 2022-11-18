package by.coolightman.weather.util

import android.annotation.SuppressLint
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

fun Long.toMonthDay(): String {
    val calendar = Calendar.getInstance().apply {
        timeInMillis = this@toMonthDay * 1000
    }
    val month = getMonthName(calendar)
    val day = getDayOfMonth(calendar)
    return "$month $day"
}

@SuppressLint("SimpleDateFormat")
fun Long.toDayOfWeek(): String {
    val calendar = Calendar.getInstance().apply {
        timeInMillis = this@toDayOfWeek * 1000
    }

    val format = SimpleDateFormat("EEEE")
    return format.format(calendar.time)
}

fun Long.toFormattedTime(): String {
    if (this == 0L) return ""
    val format =
        DateFormat.getTimeInstance(DateFormat.SHORT, Locale.getDefault())
    return format.format(Date(this * 1000))
}

private fun getDayOfMonth(calendar: Calendar): Int = calendar.get(Calendar.DAY_OF_MONTH)


@SuppressLint("SimpleDateFormat")
private fun getMonthName(calendar: Calendar): String {
    val format = SimpleDateFormat("MMMM")
    return format.format(calendar.time)
}
